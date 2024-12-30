// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false

    id("com.google.gms.google-services") version "4.4.2" apply false

    id("org.sonarqube") version "6.0.1.5171"
}

sonar {
    properties {
        property("sonar.projectKey", "Application-android-MeFormer")
        property("sonar.projectName", "Application android MeFormer")
    }
}