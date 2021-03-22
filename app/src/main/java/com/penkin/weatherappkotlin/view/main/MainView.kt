package com.penkin.weatherappkotlin.view.main

import com.penkin.weatherappkotlin.model.entity.CurrentResponseInfo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MainView: MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun renderWeather(response: CurrentResponseInfo)

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun showError(msg: Int)

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun loadSettings()

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun saveLastKey(key: String?)
}