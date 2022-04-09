package software.ninetofive.assignment_tests.main

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import software.ninetofive.assignment_tests.main.SelectedScreen.*
import software.ninetofive.assignment_tests.main.ViewingOption.*
import software.ninetofive.assignment_tests.utils.AppPreferences
import software.ninetofive.assignment_tests.utils.InMemorySharedPreferences

class LoadSettingsTest {

    private val appPreferences = AppPreferences(InMemorySharedPreferences())
    private val presenter = MainPresenter(appPreferences)

    @Test
    fun defaultSelectedScreen() {
        val subscriber = presenter.onScreenSelectedFlowable.test()

        assertEquals(SCREEN_C, subscriber.values().last())
        assertEquals(SCREEN_C, presenter.selectedScreenLiveData.value)
    }

    @Test
    fun selectedScreenSelection() {
        val subscriber = presenter.onScreenSelectedFlowable.test()

        presenter.selectScreen(SCREEN_B)
        presenter.selectScreen(SCREEN_A)

        assertEquals(listOf(SCREEN_C, SCREEN_B, SCREEN_A), subscriber.values())
    }

    @Test
    fun storingScreenSelection() {
        presenter.selectScreen(SCREEN_B)

        assertEquals(SCREEN_B, appPreferences.getStartScreen())
    }

    @Test
    fun defaultViewingOption() {
        val subscriber = presenter.viewingOptionFlowable.test()

        assertEquals(NOTHING, subscriber.values().last())
    }

    @Test
    fun selectedViewSelection() {
        val subscriber = presenter.viewingOptionFlowable.test()

        presenter.selectViewingOption(SHOW_NAME)
        presenter.selectViewingOption(DATE)

        assertEquals(listOf(NOTHING, SHOW_NAME, DATE), subscriber.values())
    }

    @Test
    fun storingViewingOptionSelection() {
        presenter.selectViewingOption(SHOW_NAME)

        assertEquals(SHOW_NAME, appPreferences.getViewingOption())
    }

    @Test
    fun defaultValidDotCheckedOption() {
        assertFalse(presenter.isValidDotChecked())
    }

    @Test
    fun validDotToggling() {
        presenter.setValidDotVisibility(true)
        assertTrue(presenter.isValidDotChecked())
        assertEquals(true, appPreferences.shouldShowValidDot())
    }
}