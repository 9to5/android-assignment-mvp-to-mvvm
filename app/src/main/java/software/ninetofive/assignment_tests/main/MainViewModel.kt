package software.ninetofive.assignment_tests.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import software.ninetofive.assignment_tests.utils.AppPreferences
import javax.inject.Inject

class MainViewModel @Inject constructor(private val appPreferences: AppPreferences) {

    private val mutableSelectedScreenLiveData = MutableLiveData(appPreferences.getStartScreen())
    val selectedScreenLiveData: LiveData<SelectedScreen> = mutableSelectedScreenLiveData

    private val mutableViewingOptionLiveData = MutableLiveData(appPreferences.getViewingOption())
    val viewingOptionLiveData: LiveData<ViewingOption> = mutableViewingOptionLiveData

    fun selectScreen(selectedScreen: SelectedScreen) {
        appPreferences.setStartScreen(selectedScreen)
        mutableSelectedScreenLiveData.value = selectedScreen
    }

    fun selectViewingOption(viewingOption: ViewingOption) {
        appPreferences.setViewingOption(viewingOption)
        mutableViewingOptionLiveData.value = viewingOption
    }

    fun isValidDotChecked(): Boolean = appPreferences.shouldShowValidDot()
    fun setValidDotVisibility(isActive: Boolean) = appPreferences.setShowValidDot(isActive)
}