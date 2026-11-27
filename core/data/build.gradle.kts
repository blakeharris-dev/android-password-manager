plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.hilt)
}

android {
    namespace = "dev.blakeharris.data"
}

dependencies {
    // `api` because repository interfaces expose model types to their callers.
    api(project(":core:model"))

    implementation(project(":core:database"))

    testImplementation(testFixtures(project(":core:database")))
    testImplementation(testFixtures(project(":core:model")))
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}
