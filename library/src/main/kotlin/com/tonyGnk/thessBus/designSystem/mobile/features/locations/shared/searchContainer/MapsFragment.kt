package com.tonyGnk.thessBus.designSystem.mobile.features.locations.shared.searchContainer

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tonyGnk.thessBus.designSystem.mobile.R
import org.maplibre.android.MapLibre
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style
import org.maplibre.android.style.layers.LineLayer
import org.maplibre.android.style.layers.Property
import org.maplibre.android.style.layers.PropertyFactory
import org.maplibre.android.style.sources.GeoJsonSource
import org.maplibre.geojson.Feature
import org.maplibre.geojson.Geometry
import org.maplibre.geojson.LineString
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.InputStream
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import org.maplibre.geojson.Point


class MapsFragment : Fragment() {

    private lateinit var mapView: MapView
    private lateinit var maplibreMap: MapLibreMap
    private val TAG = "MapsFragment"
    private var routeCoordinates = mutableListOf<Point>()
    private var isNavigating = false

    // Add source and layer IDs as constants
    companion object {
        private const val ROUTE_SOURCE_ID = "route-source-id"
        private const val ROUTE_LAYER_ID = "route-layer-id"
        private const val ROUTE_COLOR = "#4287f5"
        private const val ROUTE_WIDTH = 5f
    }

    private fun copyStreamToFile(inputStream: InputStream, file: File) {
        try {
            Log.d(TAG, "Starting to copy stream to file: ${file.absolutePath}")
            inputStream.use { input ->
                file.outputStream().use { output ->
                    val bytesCount = input.copyTo(output)
                    Log.d(
                        TAG,
                        "Successfully copied $bytesCount bytes to file: ${file.absolutePath}"
                    )
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error copying stream to file: ${e.message}", e)
            throw e
        }
    }

    private fun getFileFromAssets(fragment: Fragment, fileName: String): File {
        try {
            Log.d(TAG, "Attempting to get file from assets: $fileName")
            val assetManager = fragment.requireContext().assets
            val inputStream = assetManager.open(fileName)
            val file = File(fragment.requireContext().filesDir, fileName)
            copyStreamToFile(inputStream, file)
            Log.d(
                TAG,
                "File details - Path: ${file.absolutePath}, Exists: ${file.exists()}, Size: ${file.length()}, Readable: ${file.canRead()}"
            )
            return file
        } catch (e: Exception) {
            Log.e(TAG, "Error getting file from assets: ${e.message}", e)
            throw e
        }
    }

    private fun printMBTilesMetadata(file: File) {
        try {
            Log.d(TAG, "Attempting to read MBTiles metadata from: ${file.absolutePath}")
            val db =
                SQLiteDatabase.openDatabase(file.absolutePath, null, SQLiteDatabase.OPEN_READONLY)
            val cursor = db.query("metadata", null, null, null, null, null, null)

            cursor.use { c ->
                Log.d(TAG, "Found ${c.count} metadata entries")
                while (c.moveToNext()) {
                    val name = c.getString(c.getColumnIndexOrThrow("name"))
                    val value = c.getString(c.getColumnIndexOrThrow("value"))
                    Log.d(TAG, "MBTiles metadata: $name = $value")
                }
            }

            db.close()
            Log.d(TAG, "Successfully closed MBTiles database")
        } catch (e: Exception) {
            Log.e(TAG, "Error reading MBTiles metadata: ${e.message}", e)
        }
    }

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forge_maps, container, false)

        try {
            MapLibre.getInstance(requireContext())
            Log.d(TAG, "MapLibre initialized successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing MapLibre: ${e.message}", e)
        }

        mapView = view.findViewById(R.id.mapView)


        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync { map ->
            maplibreMap = map
            try {
                val styleJsonInputStream = requireContext().assets.open("goodStyle.json")
                Log.d(TAG, "Opened style JSON stream")

                val dir = File(requireContext().filesDir.absolutePath)
                val styleFile = File(dir, "goodStyle.json")
                copyStreamToFile(styleJsonInputStream, styleFile)

                val mbtilesFile: File = getFileFromAssets(this, "gr.mbtiles")
                Log.d(TAG, "MBTiles file acquired: ${mbtilesFile.absolutePath}")
                printMBTilesMetadata(mbtilesFile)

                // Read and log the style JSON content
                val styleContent = styleFile.inputStream().bufferedReader().use { it.readText() }
                //Log.d(TAG, "Original style content: $styleContent")

                val newFileStr = styleContent.replace(
                    "___FILE_URI___",
                    "mbtiles:///${mbtilesFile.absolutePath}"
                )
                // Log.d(TAG, "Modified style content: $newFileStr")

                val gpxWriter = FileWriter(styleFile)
                BufferedWriter(gpxWriter).use { out ->
                    out.write(newFileStr)
                }

                try {
                    val database =
                        SQLiteDatabase.openDatabase(
                            mbtilesFile.absolutePath, null, SQLiteDatabase.OPEN_READONLY
                        )


                    // Query tiles table structure
                    val cursor = database.rawQuery("SELECT * FROM tiles LIMIT 1", null)
                    val columnNames = cursor.columnNames
                    Log.d("MBTiles", "Tiles table columns: ${columnNames.joinToString()}")
                    cursor.close()

                    database.close()
                } catch (e: Exception) {
                    Log.e("MBTiles", "Error inspecting MBTiles: ${e.message}")
                }

                // Add style loading callback
                map.setStyle(
                    Style.Builder().fromUri(Uri.fromFile(styleFile).toString())
                ) { style ->
                    Log.d(TAG, "Style loaded successfully")
                    Log.d(TAG, "Style sources: ${style.sources}")
                    Log.d(TAG, "Style layers: ${style.layers}")
                    Log.d(TAG, "Style loaded successfully")

                    initializeNavigationComponents(style)
                    map.addOnMapClickListener { clickedPoint ->
                        handleMapClick(clickedPoint)
                        true
                    }
                }


                map.cameraPosition = CameraPosition.Builder()
                    .target(LatLng(40.631619, 22.953482))
                    .zoom(12.0)
                    .build()

            } catch (e: Exception) {
                Log.e(TAG, "Error loading map style: ${e.message}", e)
            }
        }

        return view
    }

    private fun initializeNavigationComponents(style: Style) {
        // Add the route line source
        style.addSource(GeoJsonSource(ROUTE_SOURCE_ID))

        // Add the route line layer
        style.addLayer(
            LineLayer(ROUTE_LAYER_ID, ROUTE_SOURCE_ID).withProperties(
                PropertyFactory.lineColor(ROUTE_COLOR),
                PropertyFactory.lineWidth(ROUTE_WIDTH),
                PropertyFactory.lineCap(Property.LINE_CAP_ROUND),
                PropertyFactory.lineJoin(Property.LINE_JOIN_ROUND)
            )
        )
    }

    private fun handleMapClick(point: LatLng) {
        if (!isNavigating) {
            // Start new route
            routeCoordinates.clear()
            routeCoordinates.add(Point.fromLngLat(point.longitude, point.latitude))
        } else {
            // Add waypoint to route
            routeCoordinates.add(Point.fromLngLat(point.longitude, point.latitude))
            calculateWalkingRoute()
        }

        isNavigating = true
    }

    private fun calculateWalkingRoute() {
        if (routeCoordinates.size < 2) return

        // Create a walking route using simple straight lines between points
        val lineString: Geometry = LineString.fromLngLats(routeCoordinates)
        val routeFeature = Feature.fromGeometry(lineString)

        maplibreMap.getStyle()?.getSourceAs<GeoJsonSource>(ROUTE_SOURCE_ID)
            ?.setGeoJson(routeFeature)

        // Calculate and display walking instructions
        displayWalkingInstructions()
    }

    private fun displayWalkingInstructions() {
        val instructions = mutableListOf<String>()

        for (i in 0 until routeCoordinates.size - 1) {
            val start = routeCoordinates[i]
            val end = routeCoordinates[i + 1]

            val distance = calculateDistance(start, end)
            val bearing = calculateBearing(start, end)
            val direction = getBearingDirection(bearing)

            instructions.add("Walk $direction for ${String.format("%.0f", distance)} meters")
        }

        // Log instructions (replace with your UI implementation)
        instructions.forEach { Log.d(TAG, it) }
    }

    private fun calculateDistance(start: Point, end: Point): Double {
        val R = 6371000.0 // Earth's radius in meters
        val lat1 = Math.toRadians(start.latitude())
        val lat2 = Math.toRadians(end.latitude())
        val dLat = Math.toRadians(end.latitude() - start.latitude())
        val dLon = Math.toRadians(end.longitude() - start.longitude())

        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(lat1) * cos(lat2) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return R * c
    }

    private fun calculateBearing(start: Point, end: Point): Double {
        val startLat = Math.toRadians(start.latitude())
        val startLng = Math.toRadians(start.longitude())
        val endLat = Math.toRadians(end.latitude())
        val endLng = Math.toRadians(end.longitude())

        val dLng = endLng - startLng

        val y = sin(dLng) * cos(endLat)
        val x = cos(startLat) * sin(endLat) -
                sin(startLat) * cos(endLat) * cos(dLng)

        var bearing = atan2(y, x)
        bearing = Math.toDegrees(bearing)
        bearing = (bearing + 360) % 360

        return bearing
    }

    private fun getBearingDirection(bearing: Double): String {
        return when {
            bearing < 22.5 -> "north"
            bearing < 67.5 -> "northeast"
            bearing < 112.5 -> "east"
            bearing < 157.5 -> "southeast"
            bearing < 202.5 -> "south"
            bearing < 247.5 -> "southwest"
            bearing < 292.5 -> "west"
            bearing < 337.5 -> "northwest"
            else -> "north"
        }
    }


    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }
}
