import java.util.Properties

plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
}

val apikeyPropertiesFile = rootProject.file("./app/apikey.properties")
val apikeyProperties = Properties()
apikeyProperties.load(apikeyPropertiesFile.inputStream())

android {
    namespace = "com.chat2desk.demo.chat2desk_sdk"
    compileSdk = Versions.Android.compileSdkVersion

    signingConfigs {
        create("release") {
            keyAlias = "c2d_demo"
            keyPassword = apikeyProperties["ALIAS_PASS"] as String
            storeFile = file("c2d_demo.keystore")
            storePassword = apikeyProperties["KEYSTORE_PASS"] as String
        }
    }

    defaultConfig {
        applicationId = "com.chat2desk.demo.chat2desk_sdk"
        minSdk = Versions.Android.minSdk
        targetSdk = Versions.Android.targetSdk
        versionCode = 7
        versionName = "1.0"

        buildConfigField("String", "WIDGET_TOKEN", apikeyProperties["WIDGET_TOKEN"] as String)
        buildConfigField("String", "BASE_HOST", apikeyProperties["BASE_HOST"] as String)
        buildConfigField("String", "WS_HOST", apikeyProperties["WS_HOST"] as String)
        buildConfigField("String", "STORAGE_HOST", apikeyProperties["STORAGE_HOST"] as String)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.androidxCompose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    //Compose
    implementation(Dependencies.androidxComposeUi)
    implementation(Dependencies.androidxComposeUiTooling)
    implementation(Dependencies.androidxComposeUiToolingPreview)
    implementation(Dependencies.androidxComposeFoundation)
    implementation(Dependencies.androidxComposeMaterial)
    implementation(Dependencies.androidxComposeMaterialIconExtended)
    implementation(Dependencies.androidxComposeCoil)

    //Compose Utils
    implementation(Dependencies.activityKtx)
    implementation(Dependencies.activityCompose)
    //Coroutines
    implementation(Dependencies.kotlinxCoroutinesCore)
    implementation(Dependencies.kotlinxCoroutinesAndroid)
    //DI
    implementation(Dependencies.koinCore)
    implementation(Dependencies.koinAndroid)

    implementation(Dependencies.kotlinxDatetime)

    implementation(Dependencies.chat2deskSdk)
}