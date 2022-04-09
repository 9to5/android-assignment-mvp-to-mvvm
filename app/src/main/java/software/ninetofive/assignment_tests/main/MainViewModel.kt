package software.ninetofive.assignment_tests.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import software.ninetofive.assignment_tests.main.state.ScreenState
import software.ninetofive.assignment_tests.utils.AppPreferences
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val mutableScreenState = MutableLiveData(ScreenState())
    val screenState: LiveData<ScreenState> = mutableScreenState

    fun loadSettings() {
        val selectedScreen = appPreferences.getStartScreen()
        val viewingOption = appPreferences.getViewingOption()
        val validDotVisible = appPreferences.shouldShowValidDot()
        mutableScreenState.update { ScreenState(selectedScreen, viewingOption, validDotVisible) }
    }

    fun selectScreen(selectedScreen: SelectedScreen) {
        appPreferences.setStartScreen(selectedScreen)
        mutableScreenState.update { it.copy(selectedScreen = selectedScreen) }
    }

    fun selectViewingOption(viewingOption: ViewingOption) {
        appPreferences.setViewingOption(viewingOption)
        mutableScreenState.update { it.copy(viewingOption = viewingOption) }
    }

    fun setValidDotVisibility(isActive: Boolean) {
        appPreferences.setShowValidDot(isActive)
        mutableScreenState.update { it.copy(isValidDotChecked = isActive) }
    }

    private fun <T> MutableLiveData<T>.update(block: (T) -> T) {
        val current = value
        val updated = current?.let { block(current) }
        value = updated
    }
}