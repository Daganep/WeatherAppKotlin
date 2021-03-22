package com.penkin.weatherappkotlin.view.settings

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SettingsView: MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun setCelsius()

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun setFahrenheit()

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun setWinter()

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun setSpring()

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun setNotification()

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun setButtonsColor(active: Int, passive: Int)

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun saveCurrentTheme(theme: Int, statusBarColor: Int)

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun saveCurrentSettings(units: String?, theme: String?, isNotificationOn: Boolean)
}