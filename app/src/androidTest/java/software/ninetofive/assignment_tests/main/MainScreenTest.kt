package software.ninetofive.assignment_tests.main

import android.content.SharedPreferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
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
    fun defaultSelectScreenOptionValue() {
        launchMainScreen {
            checkSelectedScreenHeaderShown()
            //TODO Bug! Default preference (SCREEN_C) is recognised as B on screen
            checkSelectedScreenOptionIs(SelectedScreen.SCREEN_B)
        }
    }

    @Test
    fun defaultViewingOptionValue() {
        launchMainScreen {
            checkViewingOptionsHeader()
            checkSelectedViewingOptionIs(ViewingOption.NOTHING)
        }
    }

    @Test
    fun defaultShowValidDotValue() {
        launchMainScreen {
            checkShowValidDotOptionSelected(false)
        }
    }
}