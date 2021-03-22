package com.penkin.weatherappkotlin.view.changecity

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ChangeCityView: MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun setHistory(cities: String?)

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun setCurrentCity(city: String?)
}