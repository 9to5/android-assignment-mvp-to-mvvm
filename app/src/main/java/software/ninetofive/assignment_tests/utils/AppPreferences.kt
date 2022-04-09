package software.ninetofive.assignment_tests.utils

import android.content.SharedPreferences
import software.ninetofive.assignment_tests.main.SelectedScreen
import software.ninetofive.assignment_tests.main.ViewingOption
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    companion object {
        private const val KEY_SELECTED_SCREEN = "selected_screen"
        private const val KEY_VIEWING_OPTION = "viewing_option"
        private const val KEY_SHOW_VALID_DOT = "show_valid_dot"
    }

    fun setStartScreen(selectedScreen: SelectedScreen) {
        sharedPreferences.edit()
            .putString(KEY_SELECTED_SCREEN, selectedScreen.toString())
            .apply()
    }

    fun getStartScreen(): SelectedScreen =
        when (sharedPreferences.getString(KEY_SELECTED_SCREEN, SelectedScreen.SCREEN_C.toString())) {
            SelectedScreen.SCREEN_A.toString() -> SelectedScreen.SCREEN_A
            SelectedScreen.SCREEN_C.toString() -> SelectedScreen.SCREEN_C
            SelectedScreen.SCREEN_B.toString() -> SelectedScreen.SCREEN_B
            else -> SelectedScreen.SCREEN_C
        }

    fun setViewingOption(viewingOption: ViewingOption) {
        sharedPreferences.edit()
            .putString(KEY_VIEWING_OPTION, viewingOption.toString())
            .apply()
    }

    fun getViewingOption(): ViewingOption =
        when (sharedPreferences.getString(KEY_VIEWING_OPTION, ViewingOption.NOTHING.toString())) {
            ViewingOption.SHOW_NAME.toString() -> ViewingOption.SHOW_NAME
            ViewingOption.DATE.toString() -> ViewingOption.DATE
            ViewingOption.NOTHING.toString() -> ViewingOption.NOTHING
            else -> ViewingOption.NOTHING
        }

    fun setShowValidDot(shouldShowValidDot: Boolean) {
        sharedPreferences.edit()
            .putBoolean(KEY_SHOW_VALID_DOT, shouldShowValidDot)
            .apply()
    }

    fun shouldShowValidDot() = sharedPreferences.getBoolean(KEY_SHOW_VALID_DOT, false)
}
