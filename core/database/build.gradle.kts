plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.hilt)
    alias(libs.plugins.androidx.room3)
}

android {
    namespace = "dev.blakeharris.database"

    testFixtures {
        enable = true
    }
}

room3 {
    // Exported schemas are committed so migrations can be validated and auto-generated.
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.androidx.room3.runtime)
    implementation(libs.androidx.sqlite.bundled)
    ksp(libs.androidx.room3.compiler)

    // Test fixtures
    testFixturesApi(libs.junit)
    testFixturesApi(libs.androidx.room3.runtime)
    testFixturesImplementation(libs.androidx.sqlite.bundled)
    testFixturesImplementation(libs.androidx.test.core)

    // JVM tests (Robolectric)
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.test.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)

    androidTestImplementation(libs.kotlinx.coroutines.test)
}
