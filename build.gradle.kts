plugins {
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinComposeCompiler) apply false
    alias(libs.plugins.detekt)
}

subprojects {
    plugins.withType(io.gitlab.arturbosch.detekt.DetektPlugin::class) {
        dependencies {
            detektPlugins(libs.detektFormatting)
        }
        detekt {
            parallel = true
            config = files("$rootDir/config/detekt/detekt-ruleset.yml")
            input = files(
                "src/main/java",
                "src/commonMain/kotlin",
                "src/jvmMain/kotlin",
                "src/androidMain/kotlin"
            )
        }
    }
}
