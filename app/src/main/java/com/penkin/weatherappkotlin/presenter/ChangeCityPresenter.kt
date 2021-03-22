package com.penkin.weatherappkotlin.presenter

import android.util.Log
import com.penkin.weatherappkotlin.application.Constants
import com.penkin.weatherappkotlin.application.WeatherApp
import com.penkin.weatherappkotlin.model.Settings
import com.penkin.weatherappkotlin.model.database.CityHistoryDatabase
import com.penkin.weatherappkotlin.model.database.ICityDao
import com.penkin.weatherappkotlin.model.entity.City
import com.penkin.weatherappkotlin.view.changecity.ChangeCityView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ChangeCityPresenter: MvpPresenter<ChangeCityView>() {

    @Inject lateinit var appDatabase: CityHistoryDatabase
    private var cityDao: ICityDao
    private var subscriptions: CompositeDisposable?
    private var cities: MutableList<City?>? = null

    init {
        WeatherApp.appComponent.inject(this)
        cityDao = appDatabase.cityDao()
        subscriptions = CompositeDisposable()
    }

    fun getCityHistory() {
        subscriptions?.add(cityDao.getAll()!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ emitter ->
                    cities = emitter
                    if (!cities.isNullOrEmpty()) viewState.setHistory(historyToString(cities))
                }) { throwable -> Log.e(Constants.TAG, Constants.ERROR_MESSAGE + throwable) })
    }

    private fun historyToString(cities: MutableList<City?>?): String? {
        val builder = StringBuilder()
        if (cities != null) {
            for (city in cities) {
                builder.append(city?.name)
                builder.append("  ")
            }
        }
        return builder.toString()
    }

    fun saveHistory(city: String?) {
        cities?.add(City(city!!))
        cityDao.insertList(cities)
                ?.subscribeOn(Schedulers.io())
                ?.subscribeOn(AndroidSchedulers.mainThread())
                ?.subscribe { throwable -> Log.e(Constants.TAG, Constants.ERROR_MESSAGE + throwable) }?.let { subscriptions?.add(it) }
    }

    fun clearHistory() {
        cities!!.clear()
        viewState.setHistory("")
        cityDao.deleteAll()
                ?.subscribeOn(Schedulers.io())
                ?.subscribe { throwable -> Log.e(Constants.TAG, Constants.ERROR_MESSAGE + throwable) }?.let { subscriptions!!.add(it) }
    }

    fun setCurrentCity(cityName: String?) {
        Settings.currentCity = cityName
    }

    fun getCurrentCity() {
        viewState.setCurrentCity(Settings.currentCity)
    }

    fun getLocationCity() {
        var city: String = Settings.locationCity
        if (city.isEmpty()) city = Constants.LOCATION_ERROR
        viewState.setCurrentCity(city)
    }
}