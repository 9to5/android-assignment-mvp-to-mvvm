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
}