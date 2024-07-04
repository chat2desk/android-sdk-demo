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
        versionCode = 15
        versionName = "1.4.0"

        buildConfigField(
            "String",
            "WIDGET_TOKEN",
            apikeyProperties["WIDGET_TOKEN"] as String? ?: "null"
        )
        buildConfigField("String", "BASE_HOST", apikeyProperties["BASE_HOST"] as String? ?: "null")
        buildConfigField("String", "WS_HOST", apikeyProperties["WS_HOST"] as String? ?: "null")
        buildConfigField(
            "String",
            "STORAGE_HOST",
            apikeyProperties["STORAGE_HOST"] as String? ?: "null"
        )
        buildConfigField(
            "String",
            "CLIENT_TOKEN",
            apikeyProperties["CLIENT_TOKEN"] as String? ?: "null"
        )
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.androidxComposeExtension
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    //Compose
    implementation(Dependencies.androidxComposeUi)
    implementation(Dependencies.androidxComposeUiTooling)
    implementation(Dependencies.androidxComposeUiToolingPreview)
    implementation(Dependencies.androidxComposeFoundation)
    implementation(Dependencies.androidxComposeMaterial3)
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

    coreLibraryDesugaring(Dependencies.desugarJdkLibs)
}