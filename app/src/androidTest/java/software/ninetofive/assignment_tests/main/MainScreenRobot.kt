package software.ninetofive.assignment_tests.main

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import software.ninetofive.assignment_tests.R
import software.ninetofive.assignment_tests.main.SelectedScreen.*
import software.ninetofive.assignment_tests.main.ViewingOption.*

fun launchMainScreen(
    block: MainScreenRobot.() -> Unit
): MainScreenRobot {
    ActivityScenario.launch(MainActivity::class.java)
    return MainScreenRobot().apply(block)
}

class MainScreenRobot {

    fun checkSelectedScreenHeaderShown() {
        onView(withText(R.string.choose_start_screen_header)).check(matches(isDisplayed()))
    }

    fun checkSelectedScreenOptionIs(selectedScreen: SelectedScreen) {
        val screenASelected = if (selectedScreen == SCREEN_A) isChecked() else isNotChecked()
        val screenBSelected = if (selectedScreen == SCREEN_B) isChecked() else isNotChecked()
        val screenCSelected = if (selectedScreen == SCREEN_C) isChecked() else isNotChecked()

        onView(withId(R.id.radio_screen_a)).check(matches(screenASelected))
        onView(withId(R.id.radio_screen_b)).check(matches(screenBSelected))
        onView(withId(R.id.radio_screen_c)).check(matches(screenCSelected))
    }

    fun checkViewingOptionsHeader() {
        onView(withText(R.string.check_view_options_label)).check(matches(isDisplayed()))
    }

    fun checkSelectedViewingOptionIs(viewingOption: ViewingOption) {
        val showNameSelected = if (viewingOption == SHOW_NAME) isChecked() else isNotChecked()
        val showDateSelected = if (viewingOption == DATE) isChecked() else isNotChecked()
        val showNothingSelected = if (viewingOption == NOTHING) isChecked() else isNotChecked()

        onView(withId(R.id.radio_show_name)).check(matches(showNameSelected))
        onView(withId(R.id.radio_show_date)).check(matches(showDateSelected))
        onView(withId(R.id.radio_show_nothing)).check(matches(showNothingSelected))
    }

    fun checkShowValidDotOptionSelected(isSelected: Boolean) {
        val selected = if (isSelected) isChecked() else isNotChecked()
        onView(withId(R.id.valid_dot_switch)).check(matches(selected))
    }
}