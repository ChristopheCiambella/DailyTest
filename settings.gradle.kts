pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        // Needed for Detekt
        maven {
            setUrl("https://plugins.gradle.org/m2/")
        }
    }
}

include(
    ":app",
    ":common",
    ":present",
    ":domain",
    ":data",
    ":design-system",
    ":design-system-property",
)
