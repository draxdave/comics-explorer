buildscript {

    val appVersionName :String by extra("dev0.1.")
    var appReleaseFileName :String by extra("")
    val kotlin :String by extra( "1.5.10")
    val dagger :String by extra( "2.35")
    val lifecycleVersion :String by extra( "2.2.0")


    repositories {
        mavenCentral()
        mavenLocal()
        google()
    }

    dependencies {
        "classpath"(group = "com.android.tools.build",name = "gradle", version = "4.2.0")
        "classpath"(group = "org.jetbrains.kotlin",name = "kotlin-gradle-plugin", version = kotlin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
