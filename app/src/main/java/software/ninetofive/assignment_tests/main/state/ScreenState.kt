package software.ninetofive.assignment_tests.main.state

import software.ninetofive.assignment_tests.main.SelectedScreen
import software.ninetofive.assignment_tests.main.ViewingOption

data class ScreenState(
    val selectedScreen: SelectedScreen = SelectedScreen.SCREEN_C,
    val viewingOption: ViewingOption = ViewingOption.NOTHING,
    val isValidDotChecked: Boolean = false
)
