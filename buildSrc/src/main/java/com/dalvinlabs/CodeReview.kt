package com.dalvinlabs

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

/**
 * Entry point for plugin
 *
 * [Project] configured project in which this plugin is going to run
 *
 */
class CodeReviewPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("CodeReviewPlugin, apply()")

        project.tasks.create(
            "CodeReview", // Task name
            CodeReviewTask::class.java // Task type
        )

        reviewBuildFiles(project)
    }
}

private fun reviewBuildFiles(project: Project) {
    if (!project.name.contains("-data")) return

    val dependencies = project.configurations.getByName("implementation").dependencies

    dependencies.forEach {
        if (it.name.contains("-data")) {
            throw GradleException("A data module ${project.name} must not depend upon another data module ${it.name}")
        }
    }
}

open class CodeReviewTask : DefaultTask() {

    @TaskAction
    fun action() {
        println("CodeReviewTask, action()")
    }
}