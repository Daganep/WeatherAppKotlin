package com.penkin.weatherappkotlin.view

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.penkin.weatherappkotlin.BuildConfig
import com.penkin.weatherappkotlin.R
import com.penkin.weatherappkotlin.application.Constants
import com.penkin.weatherappkotlin.databinding.ActivityMainBinding
import com.penkin.weatherappkotlin.model.Settings
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var curTheme: Int = 0
    private var statusBarColor: Int = 0
    private lateinit var binding: ActivityMainBinding


    var mLocManager: LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        themeInit()
        getLocation()
    }

    private fun themeInit(){
        loadTheme()
        setTheme(curTheme)
        window.statusBarColor = resources.getColor(statusBarColor, null)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 100) {
            var permissionsGranted = true
            for (grantResult in grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    permissionsGranted = false
                    break
                }
            }
            if (permissionsGranted) recreate()
        }
    }

    private fun getLocation(){
        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 100
            )
        }else {
            // Location manager
            mLocManager = getSystemService(LOCATION_SERVICE) as LocationManager

            // Current Location
            var loc: Location

            // Receive information from GPS provider
            if (BuildConfig.DEBUG && mLocManager == null) {
                error("Assertion failed")
            }
            loc = mLocManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!

            // Receive information from Passive (virtual) provider
            loc = mLocManager!!.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)!!

            //mPassiveInfo.setText(locToString(loc));
            Settings.locationCity = getCityLoc(loc)
        }
    }

    private fun getCityLoc(loc: Location): String{
        // Create geocoder
        val geo: Geocoder = Geocoder(this)

        // Try to get addresses list
        var list: List<Address>
        try {
            list = geo.getFromLocation(loc.latitude, loc.longitude, 1)
        }catch (e: IOException){
            list = ArrayList()
            e.printStackTrace()
            e.localizedMessage
        }

        // If list is empty, return "No data" string
        if (list.isEmpty()) return Constants.MSG_NO_DATA

        // Get first element from List
        val a: Address = list[0]

        // Get a Postal Code
        val index: Int = a.maxAddressLineIndex
        var postal: String? = null
        if(index > 0)postal = a.getAddressLine(index)
        return a.adminArea
    }

    private fun loadTheme(){
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        curTheme = prefs.getInt(
            getString(R.string.current_theme),
            R.style.Theme_WeatherApp20_Spring
        )
        statusBarColor = prefs.getInt(
            getString(R.string.status_bar_color),
            R.color.colorGreenPrimaryDark
        )
    }
}