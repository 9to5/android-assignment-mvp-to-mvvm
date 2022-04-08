package software.ninetofive.assignment_tests.utils

import android.content.Context
import android.content.SharedPreferences
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import software.ninetofive.assignment_tests.main.SelectedScreen

class AppPreferencesTest {

    @Test
    fun instantiate() {
        val appPreferences = TestableAppPreferences()

        assertEquals(SelectedScreen.SCREEN_C, appPreferences.getStartScreen())
    }

    class TestableAppPreferences : AppPreferences(null) {

        override fun getPreferences(context: Context?): SharedPreferences? {
            return super.getPreferences(context)
        }
    }
}