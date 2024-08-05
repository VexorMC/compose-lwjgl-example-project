import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.jetbrainsCompose

plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.compose")
}

group = "dev.vexor"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    jetbrainsCompose()

    maven("https://maven.vexor.dev/releases")
}

dependencies {
    implementation("org.jetbrains.compose.desktop:desktop-jvm-windows-x64:1.0.0-080524")

    implementation(platform("org.lwjgl:lwjgl-bom:3.3.4"))

    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-opengl")

    runtimeOnly("org.lwjgl:lwjgl::natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-glfw::natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-opengl::natives-windows")
}

compose.desktop {
    application {
        mainClass = "dev.vexor.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ComposeLwjglExampleProject"
            packageVersion = "1.0.0"
        }
    }
}