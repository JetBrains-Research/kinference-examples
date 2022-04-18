group = rootProject.group
version = rootProject.version

plugins {
    application
}

application {
    mainClass.set("io.kinference.examples.inference.RunInferenceKt")
}

kotlin {
    jvm() {
        withJava()
    }

    js(IR) { // Here you can choose the compiler (I chose IR)
        binaries.executable()
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(project(":utils"))
                implementation("io.kinference:inference-core:0.1.13") // KInference core backend implementation
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
            }
        }
    }
}