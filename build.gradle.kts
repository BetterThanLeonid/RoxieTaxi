// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply{
        set("kotlinVersion", "1.7.10")
        set("navVersion", "2.5.3")
        set("lifecycleVersion", "2.4.1")
        set("hiltVersion", "2.43.2")
        set("retrofitVersion", "2.9.0")
        set("workManagerVersion", "2.7.1")
        set("hiltWorkVersion", "1.0.0")
    }

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.2.2")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:${rootProject.extra.get("navVersion") as String}")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra.get("kotlinVersion") as String}")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:${rootProject.extra.get("hiltVersion") as String}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}