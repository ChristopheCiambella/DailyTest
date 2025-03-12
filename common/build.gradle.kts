import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinComposeCompiler)
    alias(libs.plugins.detekt)
    id("kotlin-parcelize")
}

kotlin {

    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }
    }

    sourceSets {
        commonMain.dependencies {

            implementation(project(":data"))
            implementation(project(":domain"))
            implementation(project(":present"))
            implementation(project(":design-system"))
            implementation(project(":design-system-property"))

            // Kotlin
            implementation(libs.kotlinSerializationJson)

            // Compose
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.uiTooling)
            implementation(compose.preview)
            implementation(libs.jetbrainsComposeNavigation)

            // Dependency injection
            implementation(libs.koinCore)
            implementation(libs.koinCompose)

            // Compose
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.uiTooling)
            implementation(compose.preview)
            implementation(libs.coilCompose)
        }
    }
}

android {
    namespace = "eu.ciambella.dailytest.common"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName(BuildType.DEBUG) {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
