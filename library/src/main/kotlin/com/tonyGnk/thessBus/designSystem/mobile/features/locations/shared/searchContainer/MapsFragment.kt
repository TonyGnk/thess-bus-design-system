package com.tonyGnk.thessBus.designSystem.mobile.features.locations.shared.searchContainer

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tonyGnk.thessBus.designSystem.mobile.R
import org.maplibre.android.MapLibre
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.InputStream


class MapsFragment : Fragment() {
    private lateinit var mapView: MapView
    private lateinit var text: TextView
    private lateinit var maplibreMap: MapLibreMap
    private val TAG = "MapsFragment"


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
            val assetManager = fragment.requireContext().assets
            val inputStream = assetManager.open(fileName)
            val file = File(fragment.requireContext().filesDir, fileName)
            copyStreamToFile(inputStream, file)
            return file
        } catch (e: Exception) {
            Log.e(TAG, "Error getting file from assets: ${e.message}", e)
            throw e
        }
    }

    private fun setupAndGetStyle(): File {
        val styleJsonInputStream = requireContext().assets.open("goodStyle.json")
        val dir = File(requireContext().filesDir.absolutePath)
        val styleFile = File(dir, "goodStyle.json")
        copyStreamToFile(styleJsonInputStream, styleFile)

        val mbtilesFile: File = getFileFromAssets(this, "gr.mbtiles")

        val styleContent = styleFile.inputStream().bufferedReader().use { it.readText() }

        val newFileStr = styleContent.replace(
            "___FILE_URI___",
            "mbtiles:///${mbtilesFile.absolutePath}"
        )

        val gpxWriter = FileWriter(styleFile)
        BufferedWriter(gpxWriter).use { out ->
            out.write(newFileStr)
        }
        return styleFile
    }

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
                val styleFile = setupAndGetStyle()

                // Add style loading callback
                map.setStyle(
                    Style.Builder().fromUri(Uri.fromFile(styleFile).toString())
                ) { style ->
                    Log.d(TAG, "Style loaded successfully")

                }


                map.cameraPosition = CameraPosition.Builder()
                    .target(LatLng(40.631619, 22.953482))
                    .zoom(14.0)//12
                    .build()

            } catch (e: Exception) {
                Log.e(TAG, "Error loading map: ${e.message}", e)
            }

//            maplibreMap.setLatLngBoundsForCameraTarget(
//                LatLngBounds.Builder()
//                    .include(LatLng(40.686, 22.912))
//                    .include(LatLng(40.692, 22.907))
//                    .build()
//            )
//            maplibreMap.addOnMapClickListener { point ->
//                // Get the screen point
//                val screenPoint = map.projection.toScreenLocation(point)
//
//                // Query rendered features at the clicked point
//                val poiFeatures = map.queryRenderedFeatures(screenPoint, "poi-label")
//                Log.d("POI Click", "Name")
//                if (poiFeatures.isNotEmpty()) {
//                    // Get the first POI feature
//                    val poiFeature = poiFeatures[0]
//
//                    // Extract POI information from the feature properties
//                    val properties = poiFeature.properties()
//
//                    // Example properties you might find (depends on your map style)
//                    val name = properties?.get("name")?.asString
//                    val type = properties?.get("type")?.asString
//                    val category = properties?.get("category")?.asString
//
//                    // Do something with the POI information
//                    Log.d("POI Click", "Name: $name, Type: $type, Category: $category")
//
//                    // Example: Show a toast with POI name
//                    context?.let {
//                        Toast.makeText(it, "Clicked POI: $name", Toast.LENGTH_SHORT).show()
//                    }
//
//                    true // Consume the event
//                } else {
//                    false // Don't consume the event
//                }
//            }
        }

        return view
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
