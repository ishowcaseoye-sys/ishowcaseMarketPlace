import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    id(libs.plugins.kobweb.application.get().pluginId)
}

group = "chat.site"
version = "1.0-SNAPSHOT"

kotlin {
    configAsKobwebApplication(includeServer = true)

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            implementation(project(":auth"))
            implementation(project(":chat"))
            implementation(libs.kotlinx.serialization.json)
            implementation("com.squareup.okio:okio:3.3.0")

        }
        jsMain.dependencies {
            implementation(libs.compose.animation)
            implementation(libs.compose.runtime)
            implementation(libs.compose.ui)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)

        }
        jvmMain.dependencies {

            compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
            implementation("io.ktor:ktor-client-okhttp:3.1.3")

        }
    }
}

