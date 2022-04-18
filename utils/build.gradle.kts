group = rootProject.group
version = rootProject.version

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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
            }
        }
    }
}