package com.penkin.weatherappkotlin.presenter

import android.util.Log
import com.penkin.weatherappkotlin.R
import com.penkin.weatherappkotlin.application.Constants
import com.penkin.weatherappkotlin.application.WeatherApp
import com.penkin.weatherappkotlin.model.Settings
import com.penkin.weatherappkotlin.model.entity.CurrentResponseInfo
import com.penkin.weatherappkotlin.model.entity.OpenWeatherResponse
import com.penkin.weatherappkotlin.model.retrofit.RetrofitApi
import com.penkin.weatherappkotlin.view.main.MainView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import java.io.IOException
import javax.inject.Inject

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {

    @Inject lateinit var retrofitApi: RetrofitApi
    private var subscriptions : CompositeDisposable
    private lateinit var responseInfo: CurrentResponseInfo

    init {
        WeatherApp.appComponent.inject(this)
        subscriptions = CompositeDisposable()
    }

    fun getData(lastKey: String, units: String, theme: String, isNotification: Boolean){
        Settings.units = units
        Settings.theme = theme
        Settings.isNotificationOn = isNotification
        requestData(getCityName(lastKey))
    }

    private fun getCityName(lastKey: String): String {
        return if (Settings.currentCity.equals("")) {
            if (lastKey.isEmpty()) Settings.defaultCity else lastKey
        } else Settings.currentCity!!
    }

    private fun requestData(cityName: String){
        val observable: Observable<OpenWeatherResponse> = retrofitApi
                .requestServer(cityName, Constants.APIKEY, Settings.units)
        subscriptions.add(observable.observeOn(AndroidSchedulers.mainThread()).subscribe(
                { emitter: OpenWeatherResponse? ->
                    responseInfo = CurrentResponseInfo(emitter, Settings.units)
                    if (!responseInfo.getCityName().equals("")) {
                        viewState.saveLastKey(cityName)
                        Settings.currentCity = cityName
                    }
                    viewState.renderWeather(responseInfo)
                }, { throwable: Throwable ->
            if (throwable is IOException) {
                viewState.showError(R.string.load_info_network_error)
            } else {
                viewState.showError(R.string.load_info_server_error)
            }
            Log.e(Constants.TAG, Constants.ERROR_MESSAGE + throwable)
        }))
    }

    override fun onDestroy() {
        super.onDestroy()
        if(subscriptions.isDisposed) subscriptions.dispose()
    }
}