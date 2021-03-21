package com.penkin.weatherappkotlin.di

import com.penkin.weatherappkotlin.presenter.ChangeCityPresenter
import com.penkin.weatherappkotlin.presenter.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(mainPresenter: MainPresenter?)

    fun inject(changeCityPresenter: ChangeCityPresenter?)
}