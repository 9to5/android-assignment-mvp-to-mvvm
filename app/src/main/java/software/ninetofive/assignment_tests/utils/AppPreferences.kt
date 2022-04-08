package software.ninetofive.assignment_tests.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor
import software.ninetofive.assignment_tests.main.ViewingOption
import software.ninetofive.assignment_tests.main.SelectedScreen
import java.lang.IllegalStateException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AppPreferences @Inject constructor(
    @ApplicationContext context: Context?,
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        private const val PREFERENCES_NAME = "app_prefs"
        private const val KEY_SELECTED_SCREEN = "selected_screen"
        private const val KEY_VIEWING_OPTION = "viewing_option"
        private const val KEY_SHOW_VALID_DOT = "show_valid_dot"
    }

    private val preferences: SharedPreferences = sharedPreferences

    protected open fun getPreferences(context: Context?) =
        context?.getSharedPreferences(PREFERENCES_NAME, 0)

    private val viewingOptionChangedProcessor: FlowableProcessor<Any> = PublishProcessor.create()
    private val showValidDotProcessor: FlowableProcessor<Boolean> = BehaviorProcessor.create()

    fun setStartScreen(selectedScreen: SelectedScreen) {
        preferences.edit()
            .putString(KEY_SELECTED_SCREEN, selectedScreen.toString())
            .apply()
    }

    fun getStartScreen(): SelectedScreen =
        when (preferences.getString(KEY_SELECTED_SCREEN, SelectedScreen.SCREEN_C.toString())) {
            SelectedScreen.SCREEN_A.toString() -> SelectedScreen.SCREEN_A
            SelectedScreen.SCREEN_C.toString() -> SelectedScreen.SCREEN_C
            SelectedScreen.SCREEN_B.toString() -> SelectedScreen.SCREEN_B
            else -> SelectedScreen.SCREEN_C
        }

    fun setViewingOption(viewingOption: ViewingOption) {
        preferences.edit()
            .putString(KEY_VIEWING_OPTION, viewingOption.toString())
            .apply()
        viewingOptionChangedProcessor.onNext(Any())
    }

    fun getViewingOption(): ViewingOption =
        when (preferences.getString(KEY_VIEWING_OPTION, ViewingOption.NOTHING.toString())) {
            ViewingOption.SHOW_NAME.toString() -> ViewingOption.SHOW_NAME
            ViewingOption.DATE.toString() -> ViewingOption.DATE
            ViewingOption.NOTHING.toString() -> ViewingOption.NOTHING
            else -> ViewingOption.NOTHING
        }


    fun setShowValidDot(shouldShowValidDot: Boolean) {
        preferences.edit()
            .putBoolean(KEY_SHOW_VALID_DOT, shouldShowValidDot)
            .apply()
        showValidDotProcessor.onNext(shouldShowValidDot)
    }

    fun shouldShowValidDot() = preferences.getBoolean(KEY_SHOW_VALID_DOT, false)

}
