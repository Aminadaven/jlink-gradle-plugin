plugins {
  `kotlin-dsl`
}

repositories {
  gradlePluginPortal()
}


dependencies {
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
  implementation("com.gradle.publish:plugin-publish-plugin:0.20.0")
}
