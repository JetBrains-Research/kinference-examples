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
                // KInference core backend implementation
                implementation("io.kinference:inference-core:0.1.13")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
            }
        }
    }
}