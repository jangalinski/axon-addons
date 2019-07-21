import org.gradle.kotlin.dsl.embeddedKotlinVersion

object Versions {

  val kotlin = embeddedKotlinVersion
  val java = "1.8"
  val axon = "4.1.2"

  val jgivenAddons = "0.5.1"

  object Plugins {
    val githubRelease = "2.2.9"
    val dokka = "0.9.17"
    val bintray = "1.8.4"
  }


  val mapdb = "3.0.7"
  val klogging = "1.6.26"

  val jgiven = "0.17.1"
  val junit5 = "5.4.2"
}
