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
        val observedStateValues = mutableListOf<ScreenState>()
        viewModel.selectedScreenLiveData.observeForever { selectedScreenObservedValues.add(it) }
        viewModel.screenState.observeForever { observedStateValues.add(it) }

        viewModel.selectScreen(SCREEN_B)
        viewModel.selectScreen(SCREEN_A)

        assertEquals(listOf(SCREEN_C, SCREEN_B, SCREEN_A), selectedScreenObservedValues)
        assertEquals(
            listOf(
                ScreenState(selectedScreen = SCREEN_C),
                ScreenState(selectedScreen = SCREEN_B),
                ScreenState(selectedScreen = SCREEN_A)
            ),
            observedStateValues
        )
    }

    @Test
    fun storingScreenSelection() {
        viewModel.selectScreen(SCREEN_B)

        assertEquals(SCREEN_B, appPreferences.getStartScreen())
    }

    @Test
    fun defaultViewingOption() {
        assertEquals(NOTHING, viewModel.viewingOptionLiveData.value)
        assertEquals(ScreenState(viewingOption = NOTHING), viewModel.screenState.value)
    }

    @Test
    fun selectedViewSelection() {
        val observedValues = mutableListOf<ScreenState>()
        val viewingOptionObservedValues = mutableListOf<ViewingOption>()
        viewModel.viewingOptionLiveData.observeForever { viewingOptionObservedValues.add(it) }
        viewModel.screenState.observeForever { observedValues.add(it) }

        viewModel.selectViewingOption(SHOW_NAME)
        viewModel.selectViewingOption(DATE)

        assertEquals(listOf(NOTHING, SHOW_NAME, DATE), viewingOptionObservedValues)
        assertEquals(
            listOf(
                ScreenState(viewingOption = NOTHING),
                ScreenState(viewingOption = SHOW_NAME),
                ScreenState(viewingOption = DATE)
            ),
            observedValues
        )
    }

    @Test
    fun storingViewingOptionSelection() {
        viewModel.selectViewingOption(SHOW_NAME)

        assertEquals(SHOW_NAME, appPreferences.getViewingOption())
    }

    @Test
    fun defaultValidDotCheckedOption() {
        assertFalse(viewModel.isValidDotChecked())
        assertEquals(ScreenState(isValidDotChecked = false), viewModel.screenState.value)
    }

    @Test
    fun validDotToggling() {
        viewModel.setValidDotVisibility(true)
        assertTrue(viewModel.isValidDotChecked())
        assertEquals(true, appPreferences.shouldShowValidDot())
        assertEquals(ScreenState(isValidDotChecked = true), viewModel.screenState.value)
    }

    @Test
    fun initialSettingsLoad() {
        appPreferences.run {
            setStartScreen(SCREEN_B)
            setViewingOption(DATE)
            setShowValidDot(true)
        }

        viewModel.loadSettings()

        assertEquals(
            ScreenState(selectedScreen = SCREEN_B, viewingOption = DATE, isValidDotChecked = true),
            viewModel.screenState.value
        )
    }
}