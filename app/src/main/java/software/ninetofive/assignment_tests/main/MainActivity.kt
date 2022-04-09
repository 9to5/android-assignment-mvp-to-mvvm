package software.ninetofive.assignment_tests.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_choose_start_screen.*
import kotlinx.android.synthetic.main.choose_start_screen.*
import kotlinx.android.synthetic.main.choose_viewing_options.*
import software.ninetofive.assignment_tests.R
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_start_screen)
        setupUI()
        setupLiveDataObservers()
    }

    private fun setupUI() {
        setupToolbar()
        forceRadioButtonsToCorrectPosition()
        setupSelectStartScreenOptions()
        setupViewingOptions()
        setupValidDotSwitch()
    }

    private fun setupToolbar() {
        setSupportActionBar(location_choose_start_screen)
        supportActionBar?.apply {
            setTitle(R.string.choose_start_screen_title)
        }
    }

    private fun forceRadioButtonsToCorrectPosition() {
        arrayOf(
            radio_screen_a,
            radio_screen_b,
            radio_screen_c,
            radio_show_name,
            radio_show_date,
            radio_show_nothing
        ).forEach { ViewCompat.setLayoutDirection(it, ViewCompat.LAYOUT_DIRECTION_RTL) }
    }

    private fun setupSelectStartScreenOptions() {
        radio_group_select_screen.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.radio_screen_a -> SelectedScreen.SCREEN_A
                R.id.radio_screen_b -> SelectedScreen.SCREEN_C
                R.id.radio_screen_c -> SelectedScreen.SCREEN_B
            }
        }
    }

    private fun setupViewingOptions() {
        radio_group_viewing_options.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.radio_show_name -> ViewingOption.SHOW_NAME
                R.id.radio_show_date -> ViewingOption.DATE
                R.id.radio_show_nothing -> ViewingOption.NOTHING
            }
        }
    }

    private fun setupValidDotSwitch() {
        valid_dot_switch.isChecked = presenter.isValidDotChecked()
        valid_dot_switch.setOnCheckedChangeListener { _, isChecked ->
            presenter.setValidDotVisibility(isChecked)
        }
    }

    private fun setupLiveDataObservers() {
        presenter.selectedScreenLiveData.observe(this) {
            onScreenSelected(it)
        }
        presenter.viewingOptionLiveData.observe(this) {
            onViewingOptionSelected(it)
        }
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
}
