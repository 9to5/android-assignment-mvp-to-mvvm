package software.ninetofive.assignment_tests.main

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import software.ninetofive.assignment_tests.utils.AppPreferences
import software.ninetofive.assignment_tests.utils.InMemorySharedPreferences

class LoadSettingsTest {

    @Test
    fun defaultSelectedScreen() {
        val appPreferences = AppPreferences(InMemorySharedPreferences())
        val presenter = MainPresenter(appPreferences)
        val subscriber = presenter.onScreenSelectedFlowable.test()

        assertEquals(SelectedScreen.SCREEN_C, subscriber.values().last())
    }

    @Test
    fun selectedScreenSelection() {
        val appPreferences = AppPreferences(InMemorySharedPreferences())
        val presenter = MainPresenter(appPreferences)
        val subscriber = presenter.onScreenSelectedFlowable.test()

        presenter.selectScreen(SelectedScreen.SCREEN_B)
        presenter.selectScreen(SelectedScreen.SCREEN_A)

        assertEquals(
            listOf(
                SelectedScreen.SCREEN_C,
                SelectedScreen.SCREEN_B,
                SelectedScreen.SCREEN_A
            ),
            subscriber.values()
        )
    }
}