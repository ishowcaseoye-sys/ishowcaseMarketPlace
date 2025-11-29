import com.varabyte.kobweb.gradle.library.util.configAsKobwebLibrary

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.serialization.plugin)
    alias(libs.plugins.kobweb.library)
}

group = "chat.auth"
version = "1.0-SNAPSHOT"

kotlin {
    configAsKobwebLibrary(includeServer = true)

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation("com.squareup.okio:okio:3.3.0")

            implementation(project(":core"))
        }
        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.silk.icons.fa)
        }
        jvmMain.dependencies {

            compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
            implementation("io.ktor:ktor-client-okhttp:3.1.3")
        }
    }
}
