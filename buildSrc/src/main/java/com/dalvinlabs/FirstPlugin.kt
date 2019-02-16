package com.dalvinlabs

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction
import org.gradle.wrapper.Download

/**
 * Entry point for plugin
 *
 * [Project] configured project in which this plugin is going to run
 *
 */
class FirstPlugin : Plugin<Project> {

    override fun apply(target: Project?) {
        target?.tasks?.create(
            "hello", // Task name
            FirstTask::class.java // Task type
        )
    }
}

open class FirstTask : DefaultTask() {

    var message: String = "Default Message"

    @TaskAction
    fun action() {
        println("FirstPlugin Task's action is executed")
        println("message = $message")
    }
}