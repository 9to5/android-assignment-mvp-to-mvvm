package software.ninetofive.assignment_tests.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import dagger.hilt.android.AndroidEntryPoint
import software.ninetofive.assignment_tests.R
import software.ninetofive.assignment_tests.databinding.ActivityChooseStartScreenBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val binding by lazy { ActivityChooseStartScreenBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUI()
        setupLiveDataObserver()
        viewModel.loadSettings()
    }

    private fun setupUI() {
        setupToolbar()
        forceRadioButtonsToCorrectPosition()
        setupSelectStartScreenOptions()
        setupViewingOptions()
        setupValidDotSwitch()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.locationChooseStartScreen)
        supportActionBar?.apply {
            setTitle(R.string.choose_start_screen_title)
        }
    }

    private fun forceRadioButtonsToCorrectPosition() {
        arrayOf(
            binding.chooseStartScreen.radioScreenA,
            binding.chooseStartScreen.radioScreenB,
            binding.chooseStartScreen.radioScreenC,
            binding.chooseViewingOptions.radioShowName,
            binding.chooseViewingOptions.radioShowDate,
            binding.chooseViewingOptions.radioShowNothing
        ).forEach { ViewCompat.setLayoutDirection(it, ViewCompat.LAYOUT_DIRECTION_RTL) }
    }

    private fun setupSelectStartScreenOptions() {
        binding.chooseStartScreen.radioGroupSelectScreen.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.radio_screen_a -> SelectedScreen.SCREEN_A
                R.id.radio_screen_b -> SelectedScreen.SCREEN_C
                R.id.radio_screen_c -> SelectedScreen.SCREEN_B
            }
        }
    }

    private fun setupViewingOptions() {
        binding.chooseViewingOptions.radioGroupViewingOptions.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.radio_show_name -> ViewingOption.SHOW_NAME
                R.id.radio_show_date -> ViewingOption.DATE
                R.id.radio_show_nothing -> ViewingOption.NOTHING
            }
        }
    }

    private fun setupValidDotSwitch() {
        binding.validDotSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setValidDotVisibility(isChecked)
        }
    }

    private fun setupLiveDataObserver() {
        viewModel.screenState.observe(this) {
            onScreenSelected(it.selectedScreen)
            onViewingOptionSelected(it.viewingOption)
            onValidDotToggled(it.isValidDotChecked)
        }
    }

    private fun onScreenSelected(selectedScreen: SelectedScreen) = when (selectedScreen) {
        SelectedScreen.SCREEN_A -> binding.chooseStartScreen.radioScreenA.isChecked = true
        SelectedScreen.SCREEN_C -> binding.chooseStartScreen.radioScreenB.isChecked = true
        SelectedScreen.SCREEN_B -> binding.chooseStartScreen.radioScreenC.isChecked = true
    }

    private fun onViewingOptionSelected(viewingOption: ViewingOption) = when (viewingOption) {
        ViewingOption.SHOW_NAME -> binding.chooseViewingOptions.radioShowName.isChecked = true
        ViewingOption.DATE -> binding.chooseViewingOptions.radioShowDate.isChecked = true
        ViewingOption.NOTHING -> binding.chooseViewingOptions.radioShowNothing.isChecked = true
    }

    private fun onValidDotToggled(validDotChecked: Boolean) {
        binding.validDotSwitch.isChecked = validDotChecked
    }
}
