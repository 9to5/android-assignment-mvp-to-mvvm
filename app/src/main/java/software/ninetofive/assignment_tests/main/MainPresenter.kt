package software.ninetofive.assignment_tests.main

import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor
import software.ninetofive.assignment_tests.utils.AppPreferences
import javax.inject.Inject

class MainPresenter @Inject constructor(private val appPreferences: AppPreferences) {

    private val selectScreenSubject: FlowableProcessor<SelectedScreen> = PublishProcessor.create()
    private val viewingOptionsSubject: FlowableProcessor<ViewingOption> = PublishProcessor.create()

    val onScreenSelectedFlowable: Flowable<SelectedScreen> = selectScreenSubject
        .startWith(appPreferences.getStartScreen())

    val viewingOptionFlowable: Flowable<ViewingOption> = viewingOptionsSubject
        .startWith(appPreferences.getViewingOption())

    val dispose = {
        CompositeDisposable()
    }

    fun selectScreen(selectedScreen: SelectedScreen) {
        selectScreenSubject.onNext(selectedScreen)
        appPreferences.setStartScreen(selectedScreen)
    }

    fun selectViewingOption(viewingOption: ViewingOption) {
        viewingOptionsSubject.onNext(viewingOption)
        appPreferences.setViewingOption(viewingOption)
    }

    fun isValidDotChecked(): Boolean = appPreferences.shouldShowValidDot()
    fun setValidDotVisibility(isActive: Boolean) = appPreferences.setShowValidDot(isActive)
}