package es.uji.vj1229.fortuji

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.uji.vj1229.fortuji.newsActivity.NewsActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class NewsDialogTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(NewsActivity::class.java)

    @Test
    fun testNewsDialog() {
        Thread.sleep(2000)
        for ((title, body) in NewsActivityTest.news) {
            clickOnText(title)
            Thread.sleep(2000)
            R.id.newsTitleTextView displays title
            R.id.newsBodyTextView displays body
            hasDrawable(R.id.newsDialogImageView)
            clickOnText("OK")
            Thread.sleep(1000)
        }
    }
}

