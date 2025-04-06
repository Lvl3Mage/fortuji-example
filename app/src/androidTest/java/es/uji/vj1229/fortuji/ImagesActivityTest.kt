package es.uji.vj1229.fortuji

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.uji.vj1229.fortuji.imagesActivity.ImagesActivity
import es.uji.vj1229.fortuji.imagesActivity.ImagesAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImagesActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(ImagesActivity::class.java)

    private val instances = arrayListOf(
        Pair(
            "Mariposa",
            arrayListOf(
                "Small icon",
                "https://fortnite-api.com/images/cosmetics/br/character_agentsherbert/smallicon.png",
                "Icon",
                "https://fortnite-api.com/images/cosmetics/br/character_agentsherbert/icon.png",
                "Lego small",
                "https://fortnite-api.com/images/cosmetics/lego/character_agentsherbert/small.png",
                "Lego large",
                "https://fortnite-api.com/images/cosmetics/lego/character_agentsherbert/large.png",
                "Bean small",
                "https://fortnite-api.com/images/cosmetics/beans/bean_agentsherbert_dew/small.png",
                "Bean large",
                "https://fortnite-api.com/images/cosmetics/beans/bean_agentsherbert_dew/large.png"
            )
        ),
        Pair(
            "Save the World",
            arrayListOf(
                "Small Icon",
                "https://fortnite-api.com/images/cosmetics/br/musicpack_000_stw_default/smallicon.png",
                "Icon",
                "https://fortnite-api.com/images/cosmetics/br/musicpack_000_stw_default/icon.png",
                "Coverart",
                "https://fortnite-api.com/images/cosmetics/br/musicpack_000_stw_default/coverart.png"
            )
        )
    )

    private fun createIntent(name: String, images: ArrayList<String>) = Intent(ApplicationProvider.getApplicationContext(), ImagesActivity::class.java).apply {
        putExtra(ImagesActivity.NAME, name)
        putStringArrayListExtra(ImagesActivity.IMAGES, images)
    }

    @Before
    fun setUp() {
        Intents.init()
    }

    private fun launch(name: String, images: ArrayList<String>) {
        val intent = createIntent(name, images)
        ActivityScenario.launch<ImagesActivity>(intent)
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun testAllPresent() {
        for ((name, images) in instances) {
            launch(name, images)
            Thread.sleep(2000)
            textExists(name)
            for ((imageType, _) in images.chunked(2)) {
                recyclerViewCheckInText<ImagesAdapter.ViewHolder>(
                    R.id.imagesRecyclerView,
                    imageType,
                    ViewAssertions.matches(hasDescendant(hasDrawable()))
                )
            }
        }
    }
}