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
  version = "0.1.1"

  apply {
    from("${rootProject.rootDir}/gradle/repositories.gradle.kts")
  }
}

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
  setOwner("toolisticon")
  setToken(properties["github.token"] as String)
  setOverwrite(true)
  setPrerelease((project.version as String).endsWith("-SNAPSHOT"))
  setVersion(project.version)
}


buildScan {
  termsOfServiceUrl = "https://gradle.com/terms-of-service"
  termsOfServiceAgree = "yes"
  publishAlways()
}
