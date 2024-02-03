import java.util.Properties

plugins {
    with(libs.plugins) {
        alias(android.application)
        alias(kotlin.android)
        alias(hilt)
        alias(kapt)
        alias(kotlin.serialization)
    }
}

android {
    namespace = "com.example.git_users"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.git_users"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val property = Properties()
        property.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "ACCEPT_FIELD", "\"${property.getProperty("ACCEPT_FIELD")}\"")
        buildConfigField("String", "API_VERSION_FIELD", "\"${property.getProperty("API_VERSION_FIELD")}\"")
        buildConfigField("String", "API_AUTHORIZATION_FIELD", "\"${property.getProperty("API_AUTHORIZATION_FIELD")}\"")
        buildConfigField("String", "ACCEPT_PARAMETER", "\"${property.getProperty("ACCEPT_PARAMETER")}\"")
        buildConfigField("String", "API_VERSION_PARAMETER", "\"${property.getProperty("API_VERSION_PARAMETER")}\"")
        buildConfigField("String", "API_AUTHORIZATION_PARAMETER", "\"${property.getProperty("API_AUTHORIZATION_PARAMETER")}\"")
        buildConfigField("String", "API_BASE_URL", "\"${property.getProperty("API_BASE_URL")}\"")

        buildFeatures.buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //Desugaring
    coreLibraryDesugaring(libs.core.jdk.desugaring)

    //Coroutines
    implementation(libs.kotlinx.coroutines.android)

    //DI
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    //Coil
    implementation(libs.coil.kt.compose)

    //Navigation
    implementation(libs.androidx.navigation.compose)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization)

    //kotlinx.serialization
    implementation(libs.kotlinx.serialization.json)

    with(libs.androidx) {
        implementation(core.ktx)
        implementation(lifecycle.runtime)
        implementation(lifecycle.runtime.compose)
        implementation(lifecycle.viewModelCompose)

        //Compose
        val composeBom = platform(compose.bom)
        implementation(activity.compose)
        implementation(composeBom)
        implementation(compose.ui)
        implementation(compose.ui.graphics)
        implementation(compose.ui.tooling.preview)
        implementation(compose.material3)

        androidTestImplementation(test.ext.junit)
        androidTestImplementation(test.espresso.core)
        androidTestImplementation(composeBom)
        androidTestImplementation(compose.ui.test.junit4)
        debugImplementation(compose.ui.tooling)
        debugImplementation(compose.ui.test.manifest)
    }
    testImplementation(libs.junit)

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}