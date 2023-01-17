object Versions {
    object Android {
        const val minSdk = 24
        const val targetSdk = 33
        const val compileSdkVersion = 33
    }

    const val pluginAndroid = "7.3.1"
    const val pluginKotlin = "1.7.20"
    const val androidxCompose = "1.3.2"
    const val composeFoundation = "1.2.1"
    const val material = "1.3.1"
    const val coil = "2.2.2"
    const val activityCompose = "1.6.1"
    const val kotlinxCoroutines = "1.6.4"
    const val koin = "3.3.2"
    const val kotlinxDatetime = "0.4.0"

    const val chat2deskSdk = "0.1.2"

}

object Plugins {
    const val pluginAndroid = "com.android.tools.build:gradle:${Versions.pluginAndroid}"
    const val pluginKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.pluginKotlin}"

    const val androidApplication = "com.android.application"
    const val android = "android"
}

object Dependencies {
    const val androidxComposeUi = "androidx.compose.ui:ui:${Versions.androidxCompose}"
    const val androidxComposeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.androidxCompose}"
    const val androidxComposeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.androidxCompose}"
    const val androidxComposeFoundation = "androidx.compose.foundation:foundation:${Versions.composeFoundation}"
    const val androidxComposeMaterial = "androidx.compose.material:material:${Versions.material}"
    const val androidxComposeMaterialIconExtended = "androidx.compose.material:material-icons-extended:${Versions.material}"
    const val androidxComposeCoil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityCompose}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}"
    const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutines}"
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val kotlinxDatetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDatetime}"
    const val chat2deskSdk = "com.chat2desk:chat2desk_sdk:${Versions.chat2deskSdk}"
}
