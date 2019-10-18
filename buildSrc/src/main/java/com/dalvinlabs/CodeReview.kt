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
        project.tasks.create(
            "CodeReview", // Task name
            CodeReviewTask::class.java // Task type
        )

        reviewBuildFiles(project)
    }
}

private fun reviewBuildFiles(project: Project) {
    val dependencies = project.configurations.getByName("implementation").dependencies

    if (project.name == "A") {
        dependencies.forEach {
            if (it.name == "C") {
                throw GradleException("Module ${project.name} must not depend upon module ${it.name}")
            }
        }
    }

    println("Module ${project.name} passed code review")
}

open class CodeReviewTask : DefaultTask() {

    @TaskAction
    fun action() {}
}