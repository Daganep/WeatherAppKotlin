package com.penkin.weatherappkotlin.view.settings

import com.penkin.weatherappkotlin.presenter.SettingsPresenter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class SettingsFragment: MvpAppCompatFragment(), SettingsView {

    @InjectPresenter
    lateinit var presenter: SettingsPresenter
}