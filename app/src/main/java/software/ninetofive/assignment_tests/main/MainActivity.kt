package software.ninetofive.assignment_tests.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.jakewharton.rxbinding2.widget.RxRadioGroup
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_choose_start_screen.*
import kotlinx.android.synthetic.main.choose_viewing_options.*
import kotlinx.android.synthetic.main.choose_start_screen.*
import software.ninetofive.assignment_tests.R
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: MainPresenter

    protected val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_start_screen)
        setupToolbar()
        // Little hack to force radio buttons on the right. After enabled RTL everything is still ok.
        arrayOf(radio_screen_a, radio_screen_b, radio_screen_c, radio_show_name, radio_show_date, radio_show_nothing)
            .forEach { ViewCompat.setLayoutDirection(it, ViewCompat.LAYOUT_DIRECTION_RTL) }

        valid_dot_switch.isChecked = presenter.isValidDotChecked()
        valid_dot_switch.setOnCheckedChangeListener { buttonView, isChecked -> presenter.setValidDotVisibility(isChecked) }

        compositeDisposable.addAll(
            presenter.dispose(),
            presenter.onScreenSelectedFlowable
                .subscribe(::onScreenSelected),
            presenter.viewingOptionFlowable
                .subscribe(::onViewingOptionSelected),
            RxRadioGroup.checkedChanges(radio_group_select_screen)
                .skipInitialValue()
                .map {
                    when (it) {
                        R.id.radio_screen_a -> SelectedScreen.SCREEN_A
                        R.id.radio_screen_b -> SelectedScreen.SCREEN_C
                        R.id.radio_screen_c -> SelectedScreen.SCREEN_B
                        else -> throw RuntimeException("Unknown select screen for id: $it")
                    }
                }
                .subscribe { presenter.selectScreen(it) },
            RxRadioGroup.checkedChanges(radio_group_viewing_options)
                .skipInitialValue()
                .map {
                    when (it) {
                        R.id.radio_show_name -> ViewingOption.SHOW_NAME
                        R.id.radio_show_date -> ViewingOption.DATE
                        R.id.radio_show_nothing -> ViewingOption.NOTHING
                        else -> throw RuntimeException("Unknown viewing option for id: $it")
                    }
                }
                .subscribe { presenter.selectViewingOption(it) }
        )
    }

    private fun onScreenSelected(selectedScreen: SelectedScreen) = when (selectedScreen) {
        SelectedScreen.SCREEN_A -> radio_screen_a.isChecked = true
        SelectedScreen.SCREEN_C -> radio_screen_b.isChecked = true
        SelectedScreen.SCREEN_B -> radio_screen_c.isChecked = true
    }

    private fun onViewingOptionSelected(viewingOption: ViewingOption) = when (viewingOption) {
        ViewingOption.SHOW_NAME -> radio_show_name.isChecked = true
        ViewingOption.DATE -> radio_show_date.isChecked = true
        ViewingOption.NOTHING -> radio_show_nothing.isChecked = true
    }

    private fun setupToolbar() {
        setSupportActionBar(location_choose_start_screen)
        supportActionBar?.apply {
            setTitle(R.string.choose_start_screen_title)
        }
    }

}
