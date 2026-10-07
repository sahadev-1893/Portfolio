package com.example.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioRepository
import com.example.ui.screens.*
import com.example.ui.theme.DeveloperPortfolioTheme

enum class MainTab(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    HOME("Home", Icons.Filled.Home, Icons.Outlined.Home),
    ABOUT("About", Icons.Filled.Person, Icons.Outlined.Person),
    PROJECTS("Projects", Icons.Filled.Layers, Icons.Outlined.Layers),
    ARTICLES("Articles", Icons.Filled.Article, Icons.Outlined.Article),
    CONTACT("Contact", Icons.Filled.Email, Icons.Outlined.Email)
}

sealed class Screen {
    data object Splash : Screen()
    data class Main(val tab: MainTab = MainTab.HOME) : Screen()
    data class ProjectDetail(val projectId: String) : Screen()
    data class ArticleDetail(val articleId: String) : Screen()
    data object Experience : Screen()
    data object Resume : Screen()
    data object Settings : Screen()
}

@Composable
fun PortfolioApp() {
    val themeMode by PortfolioRepository.themeMode.collectAsState()

    DeveloperPortfolioTheme(themeMode = themeMode) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }
        var currentTab by remember { mutableStateOf(MainTab.HOME) }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Screen content with smooth animated transitions
                AnimatedContent(
                    targetState = currentScreen,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(220)) togetherWith
                                fadeOut(animationSpec = tween(180))
                    },
                    label = "screen_transition"
                ) { screen ->
                    when (screen) {
                        is Screen.Splash -> {
                            SplashScreen(
                                onSplashFinished = {
                                    currentScreen = Screen.Main(MainTab.HOME)
                                }
                            )
                        }

                        is Screen.Main -> {
                            when (currentTab) {
                                MainTab.HOME -> HomeScreen(
                                    onNavigateToProjects = {
                                        currentTab = MainTab.PROJECTS
                                    },
                                    onNavigateToProjectDetail = { id ->
                                        currentScreen = Screen.ProjectDetail(id)
                                    },
                                    onNavigateToArticles = {
                                        currentTab = MainTab.ARTICLES
                                    },
                                    onNavigateToArticleDetail = { id ->
                                        currentScreen = Screen.ArticleDetail(id)
                                    },
                                    onNavigateToContact = {
                                        currentTab = MainTab.CONTACT
                                    },
                                    onNavigateToResume = {
                                        currentScreen = Screen.Resume
                                    },
                                    onNavigateToSettings = {
                                        currentScreen = Screen.Settings
                                    }
                                )

                                MainTab.ABOUT -> AboutScreen(
                                    onNavigateToExperience = {
                                        currentScreen = Screen.Experience
                                    },
                                    onNavigateToResume = {
                                        currentScreen = Screen.Resume
                                    },
                                    onNavigateToContact = {
                                        currentTab = MainTab.CONTACT
                                    }
                                )

                                MainTab.PROJECTS -> ProjectsScreen(
                                    onNavigateToProjectDetail = { id ->
                                        currentScreen = Screen.ProjectDetail(id)
                                    }
                                )

                                MainTab.ARTICLES -> ArticlesScreen(
                                    onNavigateToArticleDetail = { id ->
                                        currentScreen = Screen.ArticleDetail(id)
                                    }
                                )

                                MainTab.CONTACT -> ContactScreen()
                            }
                        }

                        is Screen.ProjectDetail -> ProjectDetailScreen(
                            projectId = screen.projectId,
                            onNavigateBack = {
                                currentScreen = Screen.Main(currentTab)
                            }
                        )

                        is Screen.ArticleDetail -> ArticleDetailScreen(
                            articleId = screen.articleId,
                            onNavigateBack = {
                                currentScreen = Screen.Main(currentTab)
                            }
                        )

                        is Screen.Experience -> ExperienceScreen(
                            onNavigateBack = {
                                currentScreen = Screen.Main(MainTab.ABOUT)
                            }
                        )

                        is Screen.Resume -> ResumeScreen(
                            onNavigateBack = {
                                currentScreen = Screen.Main(currentTab)
                            }
                        )

                        is Screen.Settings -> SettingsScreen(
                            onNavigateBack = {
                                currentScreen = Screen.Main(currentTab)
                            }
                        )
                    }
                }

                // Floating minimal Bottom Navigation Bar (visible only in Main screens)
                if (currentScreen is Screen.Main) {
                    PortfolioBottomNavigationBar(
                        currentTab = currentTab,
                        onTabSelected = { selectedTab ->
                            currentTab = selectedTab
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .navigationBarsPadding()
                            .padding(horizontal = 20.dp, vertical = 14.dp)
                    )
                }
            }
        }
    }
}

/**
 * Modern floating bottom navigation bar with minimal line icons and pill indicator
 */
@Composable
private fun PortfolioBottomNavigationBar(
    currentTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = Color(0xF0181818),
        border = BorderStroke(1.dp, Color(0x44444444)),
        shadowElevation = 12.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .testTag("portfolio_bottom_nav")
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MainTab.values().forEach { tab ->
                val isSelected = currentTab == tab
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onTabSelected(tab) },
                    icon = {
                        Icon(
                            imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                            contentDescription = tab.title,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    label = {
                        Text(
                            text = tab.title.uppercase(),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                letterSpacing = 0.8.sp
                            )
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF111111),
                        selectedTextColor = Color.White,
                        indicatorColor = Color.White,
                        unselectedIconColor = Color(0xFF888888),
                        unselectedTextColor = Color(0xFF888888)
                    ),
                    modifier = Modifier.testTag("nav_tab_${tab.name.lowercase()}")
                )
            }
        }
    }
}
