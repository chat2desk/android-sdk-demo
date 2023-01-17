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
        mavenLocal()
        maven {
            url = uri("https://maven.pkg.github.com/chat2desk/kotlin-sdk")
            credentials {
                username = project.findProperty("gprUser") as String
                password = project.findProperty("gprKey") as String
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}