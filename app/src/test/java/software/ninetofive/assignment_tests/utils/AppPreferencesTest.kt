package software.ninetofive.assignment_tests.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import software.ninetofive.assignment_tests.main.SelectedScreen

class AppPreferencesTest {

    @Test
    fun instantiate() {
        val appPreferences = AppPreferences(null)

        assertEquals(SelectedScreen.SCREEN_C, appPreferences.getStartScreen())
    }
}