package es.uji.vj1229.fortuji

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.BaseMatcher
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

internal infix fun String.isDisplayedOnA(id: Int) {
    onView(allOf(withId(id), withText(this))).check(matches(isDisplayed()))
}

internal infix fun Int.displays(text: String) {
    onView(withId(this)).check(matches(withText(text)))
}

internal fun clickOn(id: Int) {
    onView(withId(id)).perform(click())
}

internal fun clickOnText(text: String) {
    onView(withText(text)).perform(click())
}

internal fun writeOn(text: String, id: Int) {
    onView(withId(id)).perform(replaceText(text))
}

internal fun textExists(text: String) {
    onView(withText(text)).check(matches(isDisplayed()))
}

internal fun isVisible(id: Int) {
    onView(withId(id)).check(matches(isDisplayed()))
}

internal fun hasDrawable(id: Int) {
    onView(withId(id)).check(matches(hasDrawable()))
}

internal fun <Holder: RecyclerView.ViewHolder> recyclerViewCheckInText(recyclerId: Int, text: String, viewAssertion: ViewAssertion? = null) {
    onView(withId(recyclerId))
        .perform(
            RecyclerViewActions.scrollTo<Holder>(
                hasDescendant(withText(text))
            )
        ).check(
            viewAssertion ?: matches(alwaysTrue)
        )
    textExists(text)
}

internal fun hasDrawable(): TypeSafeMatcher<View> {
    return HasDrawableMatcher()
}

private val alwaysTrue = object : BaseMatcher<Any>() {
    override fun describeTo(description: Description) {
        description.appendText("True")
    }

    override fun matches(item: Any) = true
}


private class HasDrawableMatcher: TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("has a drawable")
    }

    override fun matchesSafely(item: View): Boolean {
        if (item !is ImageView)
            return false
        val imageView = item
        return imageView.drawable != null
    }
}