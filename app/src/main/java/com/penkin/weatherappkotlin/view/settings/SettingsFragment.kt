package com.penkin.weatherappkotlin.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.penkin.weatherappkotlin.R
import com.penkin.weatherappkotlin.databinding.FragmentSettingsBinding
import com.penkin.weatherappkotlin.presenter.SettingsPresenter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class SettingsFragment: MvpAppCompatFragment(), SettingsView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @InjectPresenter
    lateinit var presenter: SettingsPresenter
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private var activeButtonColor: Int = R.color.design_default_color_error
    private var passiveButtonColor: Int = R.color.colorGreenPrimary

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initToolbar()
        clickListenerInit()
        presenter.setButtons()
    }

    private fun initToolbar(){
        (activity as AppCompatActivity).setSupportActionBar(binding.settingsToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun clickListenerInit(){
        binding.celButtonSetFrag.setOnClickListener(this)
        binding.farButtonSetFrag.setOnClickListener(this)
        binding.winterThemeSetFrag.setOnClickListener(this)
        binding.springThemeSetFrag.setOnClickListener(this)
        binding.notSwitchSetFrag.setOnCheckedChangeListener(this)
    }

    override fun setCelsius() {
        binding.celButtonSetFrag
                .setBackgroundColor(resources
                        .getColor(activeButtonColor, null))
    }

    override fun setFahrenheit() {
        binding.farButtonSetFrag
                .setBackgroundColor(resources
                        .getColor(activeButtonColor, null))
    }

    override fun setWinter() {
        binding.winterThemeSetFrag
                .setBackgroundColor(resources
                        .getColor(activeButtonColor, null))
        binding.springThemeSetFrag
                .setBackgroundColor(resources
                        .getColor(passiveButtonColor, null))
    }

    override fun setSpring() {
        binding.springThemeSetFrag
                .setBackgroundColor(resources
                        .getColor(activeButtonColor, null))
        binding.winterThemeSetFrag
                .setBackgroundColor(resources
                        .getColor(passiveButtonColor, null))
    }

    override fun setNotification() {
        binding.notSwitchSetFrag.isChecked = true
    }

    override fun setButtonsColor(active: Int, passive: Int) {
        activeButtonColor = active
        passiveButtonColor = passive
    }

    override fun saveCurrentTheme(theme: Int, statusBarColor: Int) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putInt(getString(R.string.current_theme), theme)
        editor.putInt(getString(R.string.status_bar_color), statusBarColor)
        editor.apply()
    }

    override fun saveCurrentSettings(units: String?, theme: String?, isNotificationOn: Boolean) {
        val prefs = android.preference.PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putString(getString(R.string.units_in_settings_fragment), units)
        editor.putString(getString(R.string.theme_in_settings_fragment), theme)
        editor.putBoolean(getString(R.string.not_in_settings_fragment), isNotificationOn)
        editor.apply()
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.celButtonSetFrag -> {
                binding.celButtonSetFrag.setBackgroundColor(resources
                        .getColor(activeButtonColor, null))
                binding.farButtonSetFrag.setBackgroundColor(resources
                        .getColor(passiveButtonColor, null))
                presenter.setUnits(true)
            }
            R.id.farButtonSetFrag -> {
                binding.celButtonSetFrag.setBackgroundColor(resources
                        .getColor(passiveButtonColor, null))
                binding.farButtonSetFrag.setBackgroundColor(resources
                        .getColor(activeButtonColor, null))
                presenter.setUnits(false)
            }
            R.id.winterThemeSetFrag -> {
                binding.winterThemeSetFrag.setBackgroundColor(resources
                        .getColor(activeButtonColor, null))
                binding.springThemeSetFrag.setBackgroundColor(resources
                        .getColor(passiveButtonColor, null))
                presenter.setTheme(getString(R.string.winter))
                if (activity != null) requireActivity().recreate()
            }
            R.id.springThemeSetFrag -> {
                binding.winterThemeSetFrag.setBackgroundColor(resources
                        .getColor(passiveButtonColor, null))
                binding.springThemeSetFrag.setBackgroundColor(resources
                        .getColor(activeButtonColor, null))
                presenter.setTheme(getString(R.string.spring))
                if (activity != null) requireActivity().recreate()
            }
        }
    }

    override fun onCheckedChanged(compoundButton: CompoundButton?, isChecked: Boolean) {
        presenter.setNotification(isChecked)
        presenter.saveSettings()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}