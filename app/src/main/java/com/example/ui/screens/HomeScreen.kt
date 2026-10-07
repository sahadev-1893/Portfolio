package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioRepository
import com.example.model.Project
import com.example.ui.components.*
import com.example.ui.theme.PortfolioAccentGreen

@Composable
fun HomeScreen(
    onNavigateToProjects: () -> Unit,
    onNavigateToProjectDetail: (String) -> Unit,
    onNavigateToArticles: () -> Unit,
    onNavigateToArticleDetail: (String) -> Unit,
    onNavigateToContact: () -> Unit,
    onNavigateToResume: () -> Unit,
    onNavigateToSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val profile = PortfolioRepository.profile
    val featuredProject = PortfolioRepository.projects.firstOrNull { it.isFeatured }
        ?: PortfolioRepository.projects.first()
    val latestArticle = PortfolioRepository.articles.first()

    val scrollState = rememberScrollState()

    Box(modifier = modifier.fillMaxSize()) {
        // Decorative background lines
        PortfolioDecorativeBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Top Bar
            PortfolioTopBar(
                title = profile.name.uppercase(),
                subtitle = profile.jobTitle,
                onOpenResume = onNavigateToResume,
                onOpenSettings = onNavigateToSettings
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ==========================================
            // HERO SECTION
            // ==========================================
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                // Small Eyebrow Label
                Surface(
                    shape = RoundedCornerShape(999.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(PortfolioAccentGreen)
                        )
                        Text(
                            text = profile.jobTitle,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.2.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                // Main Heading (Space Grotesk bold editorial)
                Text(
                    text = "Hi, I'm ${profile.name}",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Subheading
                Text(
                    text = profile.bioSummary,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Normal,
                        lineHeight = 26.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Extended Goal Description
                Text(
                    text = profile.goalStatement,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Primary Button Row: "VIEW PROJECTS" + Circular Arrow + "RESUME"
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PrimaryPillButton(
                        text = "VIEW PROJECTS",
                        onClick = onNavigateToProjects
                    )

                    CircularArrowButton(
                        onClick = onNavigateToProjects,
                        contentDescription = "View all projects"
                    )

                    SecondaryPillButton(
                        text = "RESUME",
                        onClick = onNavigateToResume,
                        icon = Icons.Default.Description
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                // ==========================================
                // SOCIAL BUTTONS HORIZONTAL SCROLL
                // ==========================================
                Text(
                    text = "CONNECT & SOCIALS",
                    style = MaterialTheme.typography.labelSmall.copy(
                        letterSpacing = 1.4.sp,
                        color = MaterialTheme.colorScheme.secondary
                    ),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    PortfolioRepository.socialLinks.forEach { link ->
                        SocialPillButton(
                            name = link.platform,
                            onClick = {
                                try {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link.url))
                                    context.startActivity(intent)
                                } catch (_: Exception) {}
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // ==========================================
            // QUICK METRICS ROW
            // ==========================================
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MetricStatCard(
                    number = "${profile.experienceYears} Yrs",
                    label = "Experience",
                    modifier = Modifier.weight(1f)
                )
                MetricStatCard(
                    number = profile.completedProjectsCount,
                    label = "Projects",
                    modifier = Modifier.weight(1f)
                )
                MetricStatCard(
                    number = profile.publishedArticlesCount,
                    label = "Articles",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // ==========================================
            // FEATURED PROJECT PREVIEW CARD
            // ==========================================
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "FEATURED WORK",
                        style = MaterialTheme.typography.labelMedium.copy(
                            letterSpacing = 1.4.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    )
                    Text(
                        text = "VIEW ALL",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .clickable(onClick = onNavigateToProjects)
                            .padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Large rounded project card
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNavigateToProjectDetail(featuredProject.id) }
                        .testTag("featured_project_card")
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        // Project visual header
                        ProjectVisualCanvas(
                            title = featuredProject.title,
                            accentHex = featuredProject.visualAccentHex,
                            height = 190.dp
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = featuredProject.number,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.secondary,
                                    letterSpacing = 1.2.sp
                                )
                            )
                            Text(
                                text = featuredProject.category.uppercase(),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = PortfolioAccentGreen,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = featuredProject.title,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = featuredProject.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary,
                            maxLines = 3
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Tech pills
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            featuredProject.technologies.forEach { tech ->
                                TechBadge(name = tech)
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // "Read More" button + Circular arrow button
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            PrimaryPillButton(
                                text = "READ MORE",
                                onClick = { onNavigateToProjectDetail(featuredProject.id) }
                            )

                            CircularArrowButton(
                                onClick = { onNavigateToProjectDetail(featuredProject.id) },
                                contentDescription = "View project details"
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // ==========================================
            // LATEST ARTICLE TEASER CARD
            // ==========================================
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "LATEST ARTICLE",
                        style = MaterialTheme.typography.labelMedium.copy(
                            letterSpacing = 1.4.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    )
                    Text(
                        text = "EXPLORE ALL",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .clickable(onClick = onNavigateToArticles)
                            .padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Surface(
                    shape = RoundedCornerShape(22.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNavigateToArticleDetail(latestArticle.id) }
                        .testTag("latest_article_teaser_card")
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            TechBadge(name = latestArticle.category)
                            Text(
                                text = "•  ${latestArticle.readingTime}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = latestArticle.title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = latestArticle.summary,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary,
                            maxLines = 2
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "READ ARTICLE",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // ==========================================
            // CALL TO ACTION FOOTER
            // ==========================================
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "HAVE A PROJECT IN MIND?",
                        style = MaterialTheme.typography.labelMedium.copy(
                            letterSpacing = 1.4.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Let's build something great together.",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    PrimaryPillButton(
                        text = "GET IN TOUCH",
                        onClick = onNavigateToContact,
                        icon = Icons.Default.Email
                    )
                }
            }

            Spacer(modifier = Modifier.height(110.dp)) // Padding for bottom nav
        }
    }
}
