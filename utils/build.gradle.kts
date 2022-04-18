kotlin {
    jvm() {
        withJava()
    }

    js(IR) {
        binaries.executable()
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                api("io.kinference:inference-api:0.1.13")
               // implementation("io.kinference:inference-core:0.1.13")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
            }
        }
    }
}