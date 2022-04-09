package software.ninetofive.assignment_tests.main

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import software.ninetofive.assignment_tests.InstantTaskExecutorExtension
import software.ninetofive.assignment_tests.main.SelectedScreen.*
import software.ninetofive.assignment_tests.main.ViewingOption.*
import software.ninetofive.assignment_tests.main.state.ScreenState
import software.ninetofive.assignment_tests.utils.AppPreferences
import software.ninetofive.assignment_tests.utils.InMemorySharedPreferences

@ExtendWith(InstantTaskExecutorExtension::class)
class LoadSettingsTest {

    private val appPreferences = AppPreferences(InMemorySharedPreferences())
    private val viewModel = MainViewModel(appPreferences)

    @Test
    fun defaultSelectedScreen() {
        assertEquals(SCREEN_C, viewModel.selectedScreenLiveData.value)
        assertEquals(ScreenState(selectedScreen = SCREEN_C), viewModel.screenState.value)
    }

    @Test
    fun selectedScreenSelection() {
        val selectedScreenObservedValues = mutableListOf<SelectedScreen>()
        viewModel.selectedScreenLiveData.observeForever { selectedScreenObservedValues.add(it) }

        viewModel.selectScreen(SCREEN_B)
        viewModel.selectScreen(SCREEN_A)

        assertEquals(listOf(SCREEN_C, SCREEN_B, SCREEN_A), selectedScreenObservedValues)
    }

    @Test
    fun storingScreenSelection() {
        viewModel.selectScreen(SCREEN_B)

        assertEquals(SCREEN_B, appPreferences.getStartScreen())
    }

    @Test
    fun defaultViewingOption() {
        assertEquals(NOTHING, viewModel.viewingOptionLiveData.value)
    }

    @Test
    fun selectedViewSelection() {
        val viewingOptionObservedValues = mutableListOf<ViewingOption>()
        viewModel.viewingOptionLiveData.observeForever { viewingOptionObservedValues.add(it) }

        viewModel.selectViewingOption(SHOW_NAME)
        viewModel.selectViewingOption(DATE)

        assertEquals(listOf(NOTHING, SHOW_NAME, DATE), viewingOptionObservedValues)
    }

    @Test
    fun storingViewingOptionSelection() {
        viewModel.selectViewingOption(SHOW_NAME)

        assertEquals(SHOW_NAME, appPreferences.getViewingOption())
    }

    @Test
    fun defaultValidDotCheckedOption() {
        assertFalse(viewModel.isValidDotChecked())
    }

    @Test
    fun validDotToggling() {
        viewModel.setValidDotVisibility(true)
        assertTrue(viewModel.isValidDotChecked())
        assertEquals(true, appPreferences.shouldShowValidDot())
    }
}