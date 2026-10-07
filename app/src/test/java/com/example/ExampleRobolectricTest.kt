package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.PortfolioRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Developer Portfolio", appName)
  }

  @Test
  fun `verify portfolio repository has complete data`() {
    val profile = PortfolioRepository.profile
    assertEquals("Alex Vance", profile.name)
    assertEquals("AV", profile.initials)
    assertEquals("FULL-STACK DEVELOPER", profile.jobTitle)

    val projects = PortfolioRepository.projects
    assertTrue("Projects count should be at least 5", projects.size >= 5)
    assertNotNull(PortfolioRepository.getProjectById("proj_01"))

    val articles = PortfolioRepository.articles
    assertTrue("Articles count should be at least 6", articles.size >= 6)
    assertNotNull(PortfolioRepository.getArticleById("art_01"))

    val skills = PortfolioRepository.skillCategories
    assertTrue("Skill categories should exist", skills.isNotEmpty())

    val experiences = PortfolioRepository.experiences
    assertTrue("Experiences should exist", experiences.isNotEmpty())
  }
}
