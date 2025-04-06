package es.uji.vj1229.fortuji

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.uji.vj1229.fortuji.newsActivity.NewsActivity
import es.uji.vj1229.fortuji.newsActivity.NewsAdapter

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class NewsActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(NewsActivity::class.java)

    @Test
    fun testTitles() {
        Thread.sleep(2000)
        for ((title, _) in news) {
            recyclerViewCheckInText<NewsAdapter.ViewHolder>(
                R.id.newsRecyclerView,
                title
            )
        }
    }

    @Test
    fun testImageIsDisplayed() {
        Thread.sleep(2000)
        isVisible(R.id.newsImageView)
        hasDrawable(R.id.newsImageView)
    }

    companion object {
        val news = arrayOf(
            Pair(
                "LAWLESS Battle Pass",
                "Get the Lawless Battle Pass to instantly unlock the Cassidy Quinn Outfit. Earn XP anywhere in Fortnite to level up and unlock more rewards, including up to 1,500 V-Bucks back!"
            ),
            Pair(
                "Fortnite Twitch Drops",
                "Watch Fortnite on Twitch to earn the Lil’ Armored Truck Emote before April 8 at 4 AM UTC!"
            ),
            Pair(
                "Chapter 6 Season 2: LAWLESS",
                "Cause chaos in Battle Royale Chapter 6 Season 2: LAWLESS. Ransack Fletcher Kane’s banks, pull off a train heist, and make an explosive getaway! This season's mayhem comes with a side of pickles, featuring Big Dill's newest single 'Runamok'."
            ),
            Pair(
                "Lift Loot from a Vault",
                "- Use Thermite to start opening a Vault.\n\n- Damage weak points and fight off guards.\n\n- Loot up. Don’t miss the Dill Bits! \n\n- Visit a Black Market to spend Gold Bars and Dill Bits on powerful items.\n\n- Try a heist to go by holding up the Vault on the Armored Train or the Armored Transport convoy."
            ),
            Pair(
                "Daily Shop Refresh",
                "Check out the Shop to see what new items have been added! The Shop refreshes daily, usually at 0:00 UTC."
            ),
        )
    }
}