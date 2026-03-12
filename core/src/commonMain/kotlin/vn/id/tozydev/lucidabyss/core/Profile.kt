package vn.id.tozydev.lucidabyss.core

object Profile {
    val programmingLanguages = listOf("Kotlin", "Java", "TypeScript")
    val libraries = listOf("Ktor", "Spring", "Kobweb", "Compose Multiplatform")
    val tools = listOf("IntelliJ IDEA", "VS Code", "Git", "GitHub", "Docker")

    val workingExperiences =
        mapOf(
            SiteLanguage.Vi to
                listOf(
                    WorkingExperience(
                        company = "Trực tuyến",
                        position = "Kotlin/Java Freelancer",
                        startDate = "09-2023",
                        endDate = "01-2026",
                        description = "Phát triển và bảo trì hơn 10 dự án plugin cho server Minecraft",
                    ),
                ),
            SiteLanguage.En to
                listOf(
                    WorkingExperience(
                        company = "Remote",
                        position = "Kotlin/Java Freelancer",
                        startDate = "09-2023",
                        endDate = "01-2026",
                        description = "Developed and maintained more than 10 Minecraft plugin projects",
                    ),
                ),
        )

    data class WorkingExperience(
        val company: String,
        val position: String,
        val startDate: String,
        val endDate: String,
        val description: String,
    )
}
