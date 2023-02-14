buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Plugins.pluginAndroid)
        classpath(Plugins.pluginKotlin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
//        mavenLocal()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}