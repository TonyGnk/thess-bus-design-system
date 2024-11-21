plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("maven-publish")
    id("androidx.baselineprofile")
}

android {
    namespace = "com.tonyGnk.thessBus.designSystem.mobile"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("nonMinifiedRelease") {
        }
        create("benchmarkRelease") {
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.animation)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.core.splashscreen)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)


    implementation(libs.lottie.compose)
    //api(libs.android.sdk)
    api(libs.ramani.maplibre)


    api(libs.androidx.fragment)
    api(libs.androidx.fragment.ktx)
    api(libs.androidx.fragment.compose)

    baselineProfile(project(":baselineprofile"))
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.github.tonyGnk"
            artifactId = "thessBus-designSystem"
            version = "0.0.22"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
