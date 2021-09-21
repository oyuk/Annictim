plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdkVersion(30)
    
    defaultConfig {
        minSdkVersion(22)
        targetSdkVersion(Dependencies.Version.targetSdkVersion)

        testInstrumentationRunner =  "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.31")

    testImplementation("junit:junit:4.13.2")

    implementation(Dependencies.gson)

    compileOnly(Dependencies.Dagger.annotation)

    implementation(project(":common"))
}
