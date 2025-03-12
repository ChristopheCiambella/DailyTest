import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.detekt)
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
            // Clean architecture
            implementation(project(":domain"))

            // Kotlin
            implementation(libs.kotlinCoroutinesCore)
            implementation(libs.kotlinSerializationJson)

            // Dependency injection
            implementation(libs.koinCore)

            // Ktor
            implementation(libs.ktorClientCore)
            implementation(libs.ktorClientJson)
            implementation(libs.ktorClientSerialization)
            implementation(libs.ktorClientLogging)
            implementation(libs.ktorClientContentNegotiation)
            implementation(libs.ktorSerializationKotlinxJson)
        }

        androidMain {
            dependencies {
                // Ktor
                implementation(libs.ktorClientAndroid)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.testsJunit)
                implementation(libs.testsMockk)
                implementation(libs.testsCoroutines)
                implementation(libs.testsCore)
            }
        }
    }
}

android {
    namespace = "eu.ciambella.dailytest.data"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    buildFeatures {
        buildConfig = true
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

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    sourceSets {
        getByName("test").resources.srcDirs("src/main/assets")
    }

    lint {
        abortOnError = false
    }
}

