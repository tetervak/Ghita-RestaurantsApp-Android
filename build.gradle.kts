plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false

    // needed for the dependency injection
    id("com.google.dagger.hilt.android") version "2.48.1" apply false

    // needed by the local database
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}