package com.penkin.weatherappkotlin.view.changecity

import com.penkin.weatherappkotlin.presenter.ChangeCityPresenter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ChangeCityFragment: MvpAppCompatFragment(), ChangeCityView {

    @InjectPresenter
    lateinit var presenter: ChangeCityPresenter
}