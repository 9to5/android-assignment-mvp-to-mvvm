package software.ninetofive.assignment_tests.main

import android.content.SharedPreferences
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import software.ninetofive.assignment_tests.R
import software.ninetofive.assignment_tests.utils.InMemorySharedPreferences
import software.ninetofive.assignment_tests.utils.SharedPreferencesModule

@HiltAndroidTest
@UninstallModules(SharedPreferencesModule::class)
@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @BindValue
    val sharedPreferences: SharedPreferences = InMemorySharedPreferences()

    @Test
    fun defaultScreen() {

        ActivityScenario.launch(MainActivity::class.java)

        onView(withText(R.string.choose_start_screen_header))
            .check(matches(isDisplayed()))
    }
}