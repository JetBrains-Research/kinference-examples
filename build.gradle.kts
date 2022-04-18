import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

group = "io.kinference.examples"
version = "0.0.1"

plugins {
    kotlin("multiplatform") version "1.6.10" apply false
}

allprojects {
    repositories {
        mavenCentral()
        maven(url = "https://packages.jetbrains.team/maven/p/ki/maven")
    }
}

subprojects {
    if (this.subprojects.isNotEmpty()) return@subprojects

    apply {
        plugin("org.jetbrains.kotlin.multiplatform")
    }

    extensions.getByType(KotlinMultiplatformExtension::class.java).apply {
        sourceSets.all {
            languageSettings {
                apiVersion = "1.6"
                languageVersion = "1.6"
            }
        }

        tasks.withType<KotlinJvmCompile> {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
}