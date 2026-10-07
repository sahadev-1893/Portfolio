package com.example.model

enum class ThemeMode(val title: String) {
    DARK("Dark (Default)"),
    LIGHT("Light"),
    SYSTEM("System Default")
}

data class DeveloperProfile(
    val name: String,
    val initials: String,
    val jobTitle: String,
    val availabilityStatus: String,
    val bioSummary: String,
    val bioExtended: String,
    val goalStatement: String,
    val experienceYears: String,
    val completedProjectsCount: String,
    val publishedArticlesCount: String,
    val clientSatisfaction: String,
    val location: String,
    val currentRole: String,
    val email: String,
    val phone: String,
    val githubUrl: String,
    val linkedinUrl: String,
    val telegramUrl: String,
    val instagramUrl: String,
    val facebookUrl: String,
    val xUrl: String,
    val resumeUrl: String
)

data class Project(
    val id: String,
    val number: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val description: String,
    val overview: String,
    val problem: String,
    val solution: String,
    val features: List<String>,
    val technologies: List<String>,
    val challenges: String,
    val results: String,
    val githubUrl: String,
    val liveDemoUrl: String,
    val keyMetrics: List<Pair<String, String>>,
    val isFeatured: Boolean = false,
    val visualAccentHex: Long = 0xFFFFFFFF
)

data class ArticleSection(
    val heading: String,
    val content: String,
    val codeBlock: String? = null,
    val codeLanguage: String? = null
)

data class Article(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val author: String,
    val publishDate: String,
    val readingTime: String,
    val summary: String,
    val introduction: String,
    val sections: List<ArticleSection>,
    val bestPractices: List<String>,
    val conclusion: String,
    val isBookmarked: Boolean = false
)

data class Experience(
    val id: String,
    val yearRange: String,
    val duration: String,
    val company: String,
    val position: String,
    val location: String,
    val description: String,
    val achievements: List<String>,
    val technologies: List<String>
)

data class SkillItem(
    val name: String,
    val proficiency: Int, // 1 to 100
    val experience: String
)

data class SkillCategory(
    val categoryName: String,
    val skills: List<SkillItem>
)

data class SocialLink(
    val platform: String,
    val handle: String,
    val url: String
)

data class ContactMessage(
    val name: String,
    val email: String,
    val subject: String,
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
)
