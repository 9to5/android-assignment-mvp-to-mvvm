package software.ninetofive.assignment_tests.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import software.ninetofive.assignment_tests.main.SelectedScreen
import software.ninetofive.assignment_tests.main.ViewingOption

class AppPreferencesTest {

    @Test
    fun defaultSelectedScreen() {
        val appPreferences = AppPreferences(InMemorySharedPreferences())

        assertEquals(SelectedScreen.SCREEN_C, appPreferences.getStartScreen())
    }

    @Test
    fun selectedScreenA() {
        val appPreferences = AppPreferences(InMemorySharedPreferences())

        appPreferences.setStartScreen(SelectedScreen.SCREEN_A)

        assertEquals(SelectedScreen.SCREEN_A, appPreferences.getStartScreen())
    }

    @Test
    fun selectedScreenB() {
        val appPreferences = AppPreferences(InMemorySharedPreferences())

        appPreferences.setStartScreen(SelectedScreen.SCREEN_B)

        assertEquals(SelectedScreen.SCREEN_B, appPreferences.getStartScreen())
    }

    @Test
    fun selectedScreenC() {
        val appPreferences = AppPreferences(InMemorySharedPreferences())

        appPreferences.setStartScreen(SelectedScreen.SCREEN_C)

        assertEquals(SelectedScreen.SCREEN_C, appPreferences.getStartScreen())
    }

    @Test
    fun defaultViewingOption() {
        val appPreferences = AppPreferences(InMemorySharedPreferences())

        assertEquals(ViewingOption.NOTHING, appPreferences.getViewingOption())
    }

    @Test
    fun selectedShowName() {
        val appPreferences = AppPreferences(InMemorySharedPreferences())

        appPreferences.setViewingOption(ViewingOption.SHOW_NAME)

        assertEquals(ViewingOption.SHOW_NAME, appPreferences.getViewingOption())
    }

    @Test
    fun selectedDate() {
        val appPreferences = AppPreferences(InMemorySharedPreferences())

        appPreferences.setViewingOption(ViewingOption.DATE)

        assertEquals(ViewingOption.DATE, appPreferences.getViewingOption())
    }

    @Test
    fun selectedNothing() {
        val appPreferences = AppPreferences(InMemorySharedPreferences())

        appPreferences.setViewingOption(ViewingOption.NOTHING)

        assertEquals(ViewingOption.NOTHING, appPreferences.getViewingOption())
    }

    @Test
    fun defaultShowValidDot() {
        val appPreferences = AppPreferences(InMemorySharedPreferences())

        assertEquals(false, appPreferences.shouldShowValidDot())
    }

    @Test
    fun toggleShowValidDot() {
        val appPreferences = AppPreferences(InMemorySharedPreferences())

        appPreferences.setShowValidDot(true)

        assertEquals(true, appPreferences.shouldShowValidDot())
    }

}