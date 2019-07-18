plugins {
  base
  idea
  `maven-publish`

  kotlin("jvm") version Versions.kotlin apply false

  id("com.github.breadmoirai.github-release") version Versions.Plugins.githubRelease

  `build-scan`
}

allprojects {
  group = "io.toolisticon.addons.axon"

  apply {
    from("${rootProject.rootDir}/gradle/repositories.gradle.kts")
  }
}

//
//dependencies {
//    // Use the Kotlin JDK 8 standard library.
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//
//    // Use the Kotlin test library.
//    testImplementation("org.jetbrains.kotlin:kotlin-test")
//
//    // Use the Kotlin JUnit integration.
//    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
//}


dependencies {
  // Make the root project archives configuration depend on every sub-project
  subprojects.forEach {
    archives(it)
  }
}

idea {
  project {
    jdkName = Versions.java
    vcs = "Git"
  }
}

githubRelease {
  setToken(properties["github.token"] as String)
  setPrerelease(true)
  setOverwrite(true)
  setPrerelease((project.version as String).endsWith("-SNAPSHOT"))
}


buildScan {
  termsOfServiceUrl = "https://gradle.com/terms-of-service"
  termsOfServiceAgree = "yes"
  publishAlways()
}
