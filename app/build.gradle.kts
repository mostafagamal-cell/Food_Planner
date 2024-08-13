plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs")

}

android {
    namespace = "com.example.foodplanner"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodplanner"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {

        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    val nav_version = "2.7.7"
    val room_version = "2.6.1"

    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")
    implementation ("io.getstream:avatarview-coil:1.0.7")
    implementation ("com.google.android.gms:play-services-auth:21.2.0")
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.google.code.gson:gson:2.11.0")

    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")


    implementation(libs.firebase.auth)
    implementation (libs.core)

    implementation (libs.lottie)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}