import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinComposeCompiler)
    alias(libs.plugins.detekt)
    id("kotlin-parcelize")
}

val appVersionName: String by project
val appVersionCode: String by project

kotlin {

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    sourceSets {
        androidMain.dependencies {
            // Android
            implementation(libs.androidMaterial3)

            // UI Elements
            implementation(libs.coilCompose)
            implementation(libs.coilKtor)
        }

        commonMain.dependencies {
            // Library
            implementation(project(":common"))
            implementation(project(":present"))
            implementation(project(":domain"))
            implementation(project(":data"))
            implementation(project(":design-system"))
            implementation(project(":design-system-property"))

            // Compose
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.uiTooling)
            implementation(compose.preview)
            implementation(libs.jetbrainsComposeNavigation)

            // Kotlin
            implementation(libs.kotlinCoroutinesCore)
            implementation(libs.kotlinSerializationJson)

            // Koin
            implementation(libs.koinCore)
            implementation(libs.koinAndroid)
            implementation(libs.koinCompose)
        }

        commonTest.dependencies {
            implementation(libs.testsJunit)
            implementation(libs.testsMockk)
            implementation(libs.testsCoroutines)
            implementation(libs.testsCore)
            implementation(libs.testsRules)
            implementation(libs.testsCoreTesting)
        }
    }

}

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    namespace = "eu.ciambella.dailytest"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "eu.ciambella.dailytest"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = appVersionCode.toInt()
        versionName = appVersionName

        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            isDebuggable = false
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName(BuildType.DEBUG) {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompilerVersion.get()
    }

    testOptions {
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }

    lint {
        abortOnError = false
    }

}
