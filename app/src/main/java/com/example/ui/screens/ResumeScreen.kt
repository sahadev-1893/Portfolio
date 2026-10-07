package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.ui.components.*
import com.example.ui.theme.PortfolioAccentCyan
import com.example.ui.theme.PortfolioAccentGreen

@Composable
fun ResumeScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onNavigateBack() }
    val context = LocalContext.current
    val profile = PortfolioRepository.profile
    val scrollState = rememberScrollState()

    Box(modifier = modifier.fillMaxSize()) {
        PortfolioDecorativeBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.statusBarsPadding())
            Spacer(modifier = Modifier.height(16.dp))

            // Navigation bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .testTag("resume_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }

                IconButton(
                    onClick = {
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "Resume of ${profile.name} - ${profile.jobTitle}\n\nDownload: ${profile.resumeUrl}\nEmail: ${profile.email}"
                            )
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(sendIntent, "Share Resume"))
                    },
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share Resume",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Heading
            SectionHeader(
                title = "Resume",
                eyebrow = "Curriculum Vitae"
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Download & Action Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PrimaryPillButton(
                    text = "DOWNLOAD CV",
                    onClick = {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(profile.resumeUrl))
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("Resume URL", profile.resumeUrl)
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "Resume URL copied to clipboard!", Toast.LENGTH_LONG).show()
                        }
                    },
                    icon = Icons.Default.Download,
                    modifier = Modifier.weight(1f)
                )

                SecondaryPillButton(
                    text = "COPY LINK",
                    onClick = {
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("Resume URL", profile.resumeUrl)
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(context, "Resume link copied to clipboard!", Toast.LENGTH_SHORT).show()
                    },
                    icon = Icons.Default.ContentCopy
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ==========================================
            // RESUME SHEET / CARD
            // ==========================================
            Surface(
                shape = RoundedCornerShape(26.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    // Header Block
                    Text(
                        text = profile.name.uppercase(),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = profile.jobTitle,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = PortfolioAccentGreen,
                            letterSpacing = 1.2.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "${profile.email} • ${profile.phone} • ${profile.location}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    Spacer(modifier = Modifier.height(20.dp))

                    // Professional Summary
                    ResumeSectionHeader("PROFESSIONAL SUMMARY")
                    Text(
                        text = "Results-driven Full-Stack Engineer with over 10 years of production experience designing, deploying, and maintaining high-availability web and mobile architectures. Expert in modern React/Next.js frontends, event-driven Go and Node.js microservices, and automated DevOps workflows.",
                        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Core Competencies
                    ResumeSectionHeader("CORE COMPETENCIES")
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        ResumeBullet("Frontend: React, Next.js, TypeScript, React Native, Redux Toolkit, TailwindCSS, Jetpack Compose")
                        ResumeBullet("Backend: Node.js, Express, Golang, Python, REST APIs, GraphQL, gRPC")
                        ResumeBullet("Databases: PostgreSQL, MySQL, MongoDB, Redis, Schema Indexing & Tuning")
                        ResumeBullet("Cloud & DevOps: Docker, Kubernetes, CI/CD, GitHub Actions, AWS, Nginx")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Experience Highlights
                    ResumeSectionHeader("EXPERIENCE HIGHLIGHTS")
                    PortfolioRepository.experiences.forEach { exp ->
                        Column(modifier = Modifier.padding(bottom = 16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = exp.position,
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = exp.yearRange,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                            Text(
                                text = exp.company,
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                                color = PortfolioAccentCyan
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            exp.achievements.forEach { bullet ->
                                ResumeBullet(bullet)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Education & Certifications
                    ResumeSectionHeader("EDUCATION")
                    Text(
                        text = "B.S. in Computer Science",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "University of California, Berkeley • Graduated Magna Cum Laude",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    ResumeSectionHeader("CERTIFICATIONS")
                    ResumeBullet("AWS Certified Solutions Architect – Associate")
                    ResumeBullet("Google Cloud Certified Professional Cloud Architect")
                    ResumeBullet("Certified Kubernetes Application Developer (CKAD)")
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            SecondaryPillButton(
                text = "BACK TO PORTFOLIO",
                onClick = onNavigateBack,
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(110.dp))
        }
    }
}

@Composable
private fun ResumeSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
            letterSpacing = 1.4.sp
        ),
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
private fun ResumeBullet(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "•",
            style = MaterialTheme.typography.bodyMedium,
            color = PortfolioAccentGreen
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
        )
    }
}
