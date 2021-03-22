package com.penkin.weatherappkotlin.view.changecity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.penkin.weatherappkotlin.R
import com.penkin.weatherappkotlin.databinding.FragmentChangecityBinding
import com.penkin.weatherappkotlin.presenter.ChangeCityPresenter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ChangeCityFragment: MvpAppCompatFragment(), ChangeCityView, View.OnClickListener, TextWatcher {

    @InjectPresenter
    lateinit var presenter: ChangeCityPresenter
    private var _binding: FragmentChangecityBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChangecityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View) {
        initToolbar()
        clickListenerInit()
        autoCompleteInit()
        navController = Navigation.findNavController(view)
        presenter.getCurrentCity()
        presenter.getCityHistory()
    }

    private fun autoCompleteInit() {
        val adapter: ArrayAdapter<*> = ArrayAdapter
                .createFromResource(requireContext(),
                        R.array.cities,
                        android.R.layout.select_dialog_item)
        binding.cityNameACTV.threshold = 1
        binding.cityNameACTV.setAdapter(adapter)
        binding.cityNameACTV.addTextChangedListener(this)
    }

    private fun initToolbar(){
        (activity as AppCompatActivity).setSupportActionBar(binding.changeCityToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun clickListenerInit() {
        binding.determineButton.setOnClickListener(this)
        binding.moscowButton.setOnClickListener(this)
        binding.spbButton.setOnClickListener(this)
        binding.vladimirButton.setOnClickListener(this)
        binding.bishkekButton.setOnClickListener(this)
        binding.novosibirskButton.setOnClickListener(this)
        binding.almatyButton.setOnClickListener(this)
        binding.acceptButton.setOnClickListener(this)
        binding.clearHistoryButton.setOnClickListener(this)
    }

    override fun setHistory(cities: String?) {
        binding.citiesInHistoryTV.text = cities
    }

    override fun setCurrentCity(city: String?) {
        binding.cityNameACTV.setText(city);
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.acceptButton -> {
                presenter.setCurrentCity(binding.cityNameACTV.text.toString());
                presenter.saveHistory(binding.cityNameACTV.text.toString());
                navController.navigate(R.id.mainFragment);
            }
            R.id.moscowButton -> {
                binding.cityNameACTV.setText(R.string.moscow);
            }
            R.id.spbButton -> {
                binding.cityNameACTV.setText(R.string.saint_petersburg)
            }
            R.id.vladimirButton -> {
                binding.cityNameACTV.setText(R.string.vladimir)
            }
            R.id.bishkekButton -> {
                binding.cityNameACTV.setText(R.string.bishkek)
            }
            R.id.novosibirskButton -> {
                binding.cityNameACTV.setText(R.string.novosibirsk)
            }
            R.id.almatyButton -> {
                binding.cityNameACTV.setText(R.string.almaty)
            }
            R.id.determineButton -> {
                presenter.getLocationCity()
            }
            R.id.clearHistoryButton -> {
                presenter.clearHistory()
            }
        }
    }

    override fun beforeTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {
    }

    override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}