@file:OptIn(ExperimentalTime::class)

import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import com.varabyte.kobwebx.gradle.markdown.MarkdownEntry
import kotlinx.html.link
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
}

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")
            head.add {
                link("/lucid-abyss.css", rel = "stylesheet")
            }
        }
        export {
            enableTraces()
        }
    }
    markdown {
        defaultLayout = ".components.layouts.PostLayout"
        process = { markdownEntries ->
            data class Post(
                val filePath: String,
                val route: String,
                val title: String,
                val description: String,
                val author: String,
                val publishedAt: Instant,
                val modifiedAt: Instant?,
                val topic: String,
                val tags: Set<String>,
                val coverImage: String?,
            )

            fun MarkdownEntry.toPost(): Post? {
                val title = frontMatter["title"]?.singleOrNull() ?: return null
                val description = frontMatter["description"]?.singleOrNull() ?: return null
                val author = frontMatter["author"]?.singleOrNull() ?: return null
                val publishedAt = frontMatter["publishedAt"]?.singleOrNull()?.let(Instant::parse) ?: return null
                val modifiedAt = frontMatter["modifiedAt"]?.singleOrNull()?.let(Instant::parse)
                val topic = frontMatter["topic"]?.singleOrNull() ?: return null
                val tags = frontMatter["tags"]?.toSet() ?: emptySet()
                val coverImage = frontMatter["coverImage"]?.singleOrNull()

                return Post(
                    filePath = filePath,
                    route = route,
                    title = title,
                    author = author,
                    description = description,
                    publishedAt = publishedAt,
                    modifiedAt = modifiedAt,
                    topic = topic,
                    tags = tags,
                    coverImage = coverImage,
                )
            }

            val invalidPosts = mutableListOf<MarkdownEntry>()
            val posts =
                markdownEntries
                    .filter { it.route.startsWith("/blog/") }
                    .mapNotNull { entry ->
                        entry.toPost().also {
                            if (it == null) {
                                invalidPosts.add(entry)
                            }
                        }
                    }.sortedByDescending { it.publishedAt }

            if (invalidPosts.isNotEmpty()) {
                throw GradleException(
                    "The following markdown entries (${invalidPosts.size}) are missing required front matter fields:\n" +
                        invalidPosts.joinToString("\n") { "- ${it.route}" },
                )
            }

            generateKotlin(
                "vn/id/tozydev/lucidabyss/generated/Posts.kt",
                """
                package vn.id.tozydev.lucidabyss.generated

                import kotlin.time.Instant
                import vn.id.tozydev.lucidabyss.models.Post

                val filePathToPost =
                    mapOf(
                ${
                    posts.joinToString("") { post ->
                        """
                        "${post.filePath}" to
                            Post(
                                route = "${post.route}",
                                title = "${post.title.replace("\"", "\\\"")}",
                                author = "${post.author.replace("\"", "\\\"")}",
                                description = "${post.description.replace("\"", "\\\"")}",
                                publishedAt = Instant.parse("${post.publishedAt}"),
                                modifiedAt = ${if (post.modifiedAt != null) "Instant.parse(\"${post.modifiedAt}\")" else "null"},
                                topic = "${post.topic.replace("\"", "\\\"")}",
                                tags = setOf(${post.tags.joinToString(", ") { "\"${it.replace("\"", "\\\"")}\"" }}),
                                coverImage = ${
                            if (post.coverImage != null) {
                                "\"${
                                    post.coverImage.replace(
                                        "\"",
                                        "\\\"",
                                    )
                                }\""
                            } else {
                                "null"
                            }
                        },
                            ),"""
                    }
                }
                    )

                """.trimIndent(),
            )
        }
        handlers {
            useSilk = false
        }
    }
}

kotlin {
    configAsKobwebApplication("lucid-abyss")

    compilerOptions {
        optIn.addAll(
            "kotlin.time.ExperimentalTime",
        )
        freeCompilerArgs.addAll(
            "-Xcontext-parameters",
        )
    }

    sourceSets {
        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
            implementation(libs.kotlinx.datetime)

            // todo version catalog?
            implementation(devNpm("daisyui", "5.5.14"))
            implementation(devNpm("tailwindcss", "4.1.18"))
            implementation(devNpm("@tailwindcss/postcss", "4.1.18"))
            implementation(devNpm("@tailwindcss/typography", "0.5.19"))
            implementation(devNpm("postcss", "8.5.6"))
            implementation(devNpm("postcss-loader", "8.2.0"))
            implementation(devNpm("css-loader", "7.1.2"))
            implementation(devNpm("mini-css-extract-plugin", "2.9.4"))
            implementation(devNpm("css-minimizer-webpack-plugin", "7.0.4"))
        }
    }
}

tasks {
    val copyProductionStylesheet by registering(Copy::class) {
        from(layout.buildDirectory.dir("kotlin-webpack/js/productionExecutable")) {
            include("*.css")
            include("*.css.map")
        }
        into(layout.projectDirectory.dir(".kobweb/site"))
    }
    kobwebExport {
        finalizedBy(copyProductionStylesheet)
    }
}
