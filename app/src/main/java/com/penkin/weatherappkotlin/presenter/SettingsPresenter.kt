package com.penkin.weatherappkotlin.presenter

import com.penkin.weatherappkotlin.R
import com.penkin.weatherappkotlin.application.Constants
import com.penkin.weatherappkotlin.model.Settings
import com.penkin.weatherappkotlin.view.settings.SettingsView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class SettingsPresenter: MvpPresenter<SettingsView>() {

    fun setNotification(isNotificationOn: Boolean) {
        Settings.isNotificationOn = isNotificationOn
    }

    fun setUnits(units: Boolean) {
        Settings.units = if (units) Constants.METRIC else Constants.IMPERIAL
    }

    fun setButtons() {
        if (Settings.units.equals(Constants.METRIC)) viewState.setCelsius() else viewState.setFahrenheit()
        if (Settings.theme == Constants.SPRING) {
            viewState.setButtonsColor(R.color.design_default_color_error, R.color.colorGreenPrimary)
            viewState.setSpring()
        } else {
            viewState.setButtonsColor(R.color.design_default_color_error, R.color.colorDarkGrey)
            viewState.setWinter()
        }
        if (Settings.isNotificationOn) viewState.setNotification()
    }

    fun setTheme(theme: String) {
        Settings.theme = theme
        if (theme == Constants.SPRING) viewState.saveCurrentTheme(R.style.Theme_WeatherApp20_Spring, R.color.colorGreenPrimaryDark)
        else viewState.saveCurrentTheme(R.style.Theme_WeatherApp20_Winter, R.color.colorLightGrey)
    }

    fun saveSettings() {
        viewState.saveCurrentSettings(
                Settings.units,
                Settings.theme,
                Settings.isNotificationOn)
    }
}