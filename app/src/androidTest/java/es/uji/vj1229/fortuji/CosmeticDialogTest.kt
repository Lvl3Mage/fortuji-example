package es.uji.vj1229.fortuji

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.uji.vj1229.fortuji.searchActivity.SearchActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CosmeticDialogTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(SearchActivity::class.java)

    private val searches = arrayOf (
        Pair("Lightning Cloak", arrayOf(
            "BID_283_StormSoldier",
            "Lightning Cloak",
            "Back Bling",
            "Legendary",
            "2019-11-20T12:50:33Z",
            "Crackling with furious energy."
        )),
        Pair("Banner Cape", arrayOf(
            "BID_289_Banner",
            "Banner Cape",
            "Back Bling",
            "Rare",
            "2019-11-20T12:50:34Z",
            "Customize your look by choosing a Banner and color in your Locker."
        ))
    )

    @Test
    fun testSearchDialog() {
        for ((name, fields) in searches) {
            writeOn(name, R.id.searchContentEditText)
            clickOn(R.id.searchButton)
            Thread.sleep(2000)
            clickOn(R.id.nameTextView)
            Thread.sleep(3000)
            R.id.idTextView displays fields[0]
            R.id.nameTextView displays fields[1]
            R.id.typeTextView displays fields[2]
            R.id.rarityTextView displays fields[3]
            R.id.addedTextView displays fields[4]
            R.id.descriptionTextView displays fields[5]
            hasDrawable(R.id.imageImageView)
            clickOnText("OK")
        }
    }
}