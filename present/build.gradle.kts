plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.detekt)
}

kotlin {
    jvm()
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":design-system-property"))
                implementation(project(":domain"))
                implementation(libs.kotlinCoroutinesCore)
                implementation(libs.koinCore)
            }
        }
        commonTest {
            dependencies {
                implementation(libs.testsJunit)
                implementation(libs.testsMockk)
                implementation(libs.testsCoroutines)
            }
        }
    }
}
