package software.ninetofive.assignment_tests.main

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import software.ninetofive.assignment_tests.main.SelectedScreen.*
import software.ninetofive.assignment_tests.utils.AppPreferences
import software.ninetofive.assignment_tests.utils.InMemorySharedPreferences

class LoadSettingsTest {

    private val appPreferences = AppPreferences(InMemorySharedPreferences())
    private val presenter = MainPresenter(appPreferences)

    @Test
    fun defaultSelectedScreen() {
        val subscriber = presenter.onScreenSelectedFlowable.test()

        assertEquals(SCREEN_C, subscriber.values().last())
    }

    @Test
    fun selectedScreenSelection() {
        val subscriber = presenter.onScreenSelectedFlowable.test()

        presenter.selectScreen(SCREEN_B)
        presenter.selectScreen(SCREEN_A)

        assertEquals(listOf(SCREEN_C, SCREEN_B, SCREEN_A), subscriber.values())
    }
}