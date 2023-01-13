import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
}

val apikeyPropertiesFile = rootProject.file("./app/apikey.properties")
val apikeyProperties = Properties()
apikeyProperties.load(FileInputStream(apikeyPropertiesFile))

android {
    namespace = "com.chat2desk.demo.chat2desk_sdk"
    compileSdk = (findProperty("android.compileSdk") as String).toInt()

    defaultConfig {
        applicationId = "com.chat2desk.sdk.demo.chat2desk_sdk"
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "WIDGET_TOKEN", apikeyProperties["WIDGET_TOKEN"] as String)
        buildConfigField("String", "BASE_HOST", apikeyProperties["BASE_HOST"] as String)
        buildConfigField("String", "WS_HOST", apikeyProperties["WS_HOST"] as String)
        buildConfigField("String", "STORAGE_HOST", apikeyProperties["STORAGE_HOST"] as String)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Compose
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.icon.extended)
    implementation(libs.androidx.compose.coil)

    //Compose Utils
    implementation(libs.activity.ktx)
    implementation(libs.activity.compose)
    //Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    //DI
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    implementation(libs.kotlinx.datetime)

    implementation(libs.chat2desk.sdk)
}