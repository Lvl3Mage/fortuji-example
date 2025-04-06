package es.uji.vj1229.fortuji

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.uji.vj1229.fortuji.searchActivity.SearchActivity
import es.uji.vj1229.fortuji.searchActivity.SearchViewModel.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class SearchActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(SearchActivity::class.java)

    private val nameSearches = arrayOf (
        Pair("Fire", arrayOf(
            Pair("Fryer Fire", "Triple fried and charred to taste. Salt as necessary."),
            Pair("Fire Vortex", "The swirling flame of the great beyond."),
            Pair("Firestarter", "Warning: Contents of firestarter may be hot.")
        )),
        Pair("Lightning", arrayOf(
            Pair("Lightning Cloak", "Crackling with furious energy."),
            Pair("Lightning Dropper", "It's like riding, but faster."),
            Pair("Lightning Kick!", "Hundred Lightning Legs!")
        )),
        Pair("Karasu", arrayOf(
            Pair("Karasu", "Guide of the ancients."),
            Pair("Karasu Carver", "Burn the night.")
        ))
    )

    private val idSearches = arrayOf(
        Pair("BID_283_StormSoldier",
            Pair("Lightning Cloak", "Crackling with furious energy.")),
        Pair("Backpack_FreeDrive",
            Pair("Fryer Fire", "Triple fried and charred to taste. Salt as necessary.")),
        Pair("Character_CorvidStomp",
            Pair("Karasu", "Guide of the ancients.")),
    )

    private val descriptionSearches = arrayOf(
        Pair("furious", arrayOf(
            Pair("Lightning Cloak", "Crackling with furious energy."),
            Pair("Redcap", "The furious fungus enters the fight."),
        )),
        Pair("charred", arrayOf(
            Pair("Fryer Fire", "Triple fried and charred to taste. Salt as necessary."),
        )),
        Pair("ancients", arrayOf(
            Pair("Apocalypse Shroud", "Handed down from the ever-vengeful ancients."),
            Pair("Frozen Shroud", "A relic of the ancients."),
            Pair("Karasu", "Guide of the ancients."),
        )),
    )

    @Test
    fun testNameSearch() {
        for ((name, results) in nameSearches) {
            writeOn(name, R.id.searchContentEditText)
            clickOn(R.id.searchButton)
            Thread.sleep(2000)
            for ((title, description) in results) {
                title isDisplayedOnA R.id.nameTextView
                description isDisplayedOnA R.id.descriptionTextView
            }
        }
    }

    @Test
    fun testIdSearch() {
        selectInSpinner(SearchType.ID, R.id.searchTypeSpinner)
        for ((id, result) in idSearches) {
            writeOn(id, R.id.searchContentEditText)
            clickOn(R.id.searchButton)
            Thread.sleep(2000)
            val (title, description) = result
            title isDisplayedOnA R.id.nameTextView
            description isDisplayedOnA R.id.descriptionTextView
        }
    }

    @Test
    fun testDescriptionSearch() {
        selectInSpinner(SearchType.DESCRIPTION, R.id.searchTypeSpinner)
        for ((content, results) in descriptionSearches) {
            writeOn(content, R.id.searchContentEditText)
            clickOn(R.id.searchButton)
            Thread.sleep(2000)
            for ((title, description) in results) {
                title isDisplayedOnA R.id.nameTextView
                description isDisplayedOnA R.id.descriptionTextView
            }
        }
    }

    @Test
    fun testSearchError() {
        for (word in arrayOf("anciente", "croww")) {
            writeOn(word, R.id.searchContentEditText)
            clickOn(R.id.searchButton)
            Thread.sleep(2000)
            textExists("\"$word\" not found")
        }
    }
}

internal fun selectInSpinner(type: SearchType, id: Int) {
    onView(withId(id)).perform(click())
    onData(allOf(`is`(instanceOf(SearchType::class.java)), `is`(type))).perform(click())
}
