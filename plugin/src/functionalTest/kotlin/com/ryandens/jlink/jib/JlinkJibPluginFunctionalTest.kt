/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.ryandens.jlink.jib

import java.io.File
import java.nio.file.Files
import kotlin.test.assertTrue
import kotlin.test.Test
import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder

/**
 * A simple functional test for the 'com.ryandens.jlink.jib.greeting' plugin.
 */
class JlinkJibPluginFunctionalTest {
    @get:Rule val tempFolder = TemporaryFolder()

    private fun getProjectDir() = tempFolder.root
    private fun getBuildFile() = getProjectDir().resolve("build.gradle")
    private fun getSettingsFile() = getProjectDir().resolve("settings.gradle")

    @Test fun `can run with custom jre`() {
        // Setup the test build
        getSettingsFile().writeText("")
        getBuildFile().writeText("""
plugins {
    id('application')
    id('com.ryandens.jlink-application-run')
}

application {
  mainClass.set("com.ryandens.example.App")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
""")

      val file = File(getProjectDir(), "src/main/java/com/ryandens/example/")
      file.mkdirs()
      file.resolve("App.java").writeText("""
        package com.ryandens.example;
        
        public final class App {
        
          public static void main(final String[] args) {
            System.out.println("Hello World");
          }
        
        }
      """.trimIndent())

        // Run the build
        val runner = GradleRunner.create()
        runner.forwardOutput()
        runner.withPluginClasspath()
        runner.withArguments("run")
        runner.withProjectDir(getProjectDir())
        runner.build();

        // Verify the result
        assertTrue(File(getProjectDir(), "build/jlink-jre/jre/bin/java").exists())
    }
}
