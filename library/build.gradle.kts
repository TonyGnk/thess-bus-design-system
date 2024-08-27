plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("maven-publish")
}

android {
    namespace = "com.tonyGnk.thessBus.designSystem.mobile"
    compileSdk = 34

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

composeCompiler {
    enableStrongSkippingMode = true
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)

    //implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.material3)
    implementation(libs.lottie.compose)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)


//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.compose.ui.test)
//
//    debugImplementation(libs.androidx.ui.tooling)
//    debugImplementation(libs.androidx.ui.test.manifest)
//
//    testImplementation(libs.junit)
//    testImplementation(libs.androidx.compose.ui.test)
//    testImplementation(libs.androidx.compose.ui.testManifest)

    //To be removed
//    api(libs.androidx.compose.runtime)
//    api(libs.androidx.compose.ui.util)
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.ui)
//    implementation(libs.androidx.ui.graphics)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.github.tonyGnk"
            artifactId = "thessBus-designSystem"
            version = "0.0.9"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
