package com.penkin.weatherappkotlin.view.main

import android.os.Bundle
import androidx.preference.PreferenceManager
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.penkin.weatherappkotlin.R
import com.penkin.weatherappkotlin.application.Constants
import com.penkin.weatherappkotlin.databinding.FragmentMainBinding
import com.penkin.weatherappkotlin.model.entity.CurrentResponseInfo
import com.penkin.weatherappkotlin.presenter.MainPresenter
import com.penkin.weatherappkotlin.utils.ImageSetter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import java.util.*

class MainFragment: MvpAppCompatFragment(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageSetter: ImageSetter
    private lateinit var navController: NavController
    private lateinit var lastKey: String
    private lateinit var units: String
    private lateinit var theme: String
    private var isNotification = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        setHasOptionsMenu(true)
        presenter.getData(lastKey, units, theme, isNotification)
    }

    private fun init(view: View) {
        imageSetter = ImageSetter()
        initToolbar()
        navController = Navigation.findNavController(view)
        loadSettings()
    }

    private fun initToolbar(){
        (activity as AppCompatActivity).setSupportActionBar(binding.mainToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_main, menu)

        val changeCityItem = menu.findItem(R.id.menu_change_city)
        changeCityItem.setOnMenuItemClickListener {
            navController.navigate(R.id.changeCityFragment)
            false
        }

        val settingsItem = menu.findItem(R.id.menu_settings)
        settingsItem.setOnMenuItemClickListener {
            navController.navigate(R.id.settingsFragment)
            false
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun renderWeather(response: CurrentResponseInfo) {
        binding.curCityNameMF.text = response.getCityName()
        binding.curTempMF.text = response.getCurrentTemp()
        imageSetter.setImage(response.getCurrentImagePath(), binding.curTempImageMF)
        binding.curTempDescriptMF.text = response.getCurrentTempDescription()
        binding.curTempSensMF.text = response.getGetCurrentTempSens()
        binding.windSpeedMeanMF.text = response.getWindSpeed()
        binding.pressureMeanMF.text = response.getPressure()
        binding.humidityMeanMF.text = response.getHumidity()

        //later code below will be delete (view will be initialize in RecyclerView)
        binding.tomorrowDateMF.text = response.getOtherDayDate(1)
        binding.tomorrowMF.text = response.getOtherDay(1)
        imageSetter.setImage(response.getOtherDayImagePath(1), binding.tomorrowTempImageMF)
        binding.tomorrowDayTempMF.text = response.getOtherDayTemp(1)
        binding.tomorrowNightTempMF.text = response.getOtherNightTemp(1)
        binding.day3DateMF.text = response.getOtherDayDate(2)
        binding.day3MF.text = response.getOtherDay(2)
        imageSetter.setImage(response.getOtherDayImagePath(2), binding.day3TempImageMF)
        binding.day3DayTempMF.text = response.getOtherDayTemp(2)
        binding.day3NightTempMF.text = response.getOtherNightTemp(2)
        binding.day4DateMF.text = response.getOtherDayDate(3)
        binding.day4MF.text = response.getOtherDay(3)
        imageSetter.setImage(response.getOtherDayImagePath(3), binding.day4TempImageMF)
        binding.day4DayTempMF.text = response.getOtherDayTemp(3)
        binding.day4NightTempMF.text = response.getOtherNightTemp(3)
        binding.day5DateMF.text = response.getOtherDayDate(4)
        binding.day5MF.text = response.getOtherDay(4)
        imageSetter.setImage(response.getOtherDayImagePath(4), binding.day5TempImageMF)
        binding.day5DayTempMF.text = response.getOtherDayTemp(4)
        binding.day5NightTempMF.text = response.getOtherNightTemp(4)
    }

    override fun showError(msg: Int) {
        binding.mainScreen.visibility = View.GONE
        binding.emptyResult.setText(msg)
        binding.emptyResult.visibility = View.VISIBLE
    }

    override fun saveLastKey(key: String?) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putString(getString(R.string.last_key), key)
        editor.apply()
    }

    override fun loadSettings() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        lastKey = prefs.getString(getString(R.string.last_key), "")!!
        units = prefs.getString(getString(R.string.units_in_settings_fragment), Constants.METRIC)!!
        theme = prefs.getString(getString(R.string.theme_in_settings_fragment), Constants.SPRING)!!
        isNotification = prefs.getBoolean(getString(R.string.not_in_settings_fragment), true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}