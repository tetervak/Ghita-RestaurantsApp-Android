plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false

    // needed for the dependency injection
    id("com.google.dagger.hilt.android") version "2.44.2" apply false

    // needed by the local database
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}