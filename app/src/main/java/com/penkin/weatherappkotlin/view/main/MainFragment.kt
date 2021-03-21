package com.penkin.weatherappkotlin.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.penkin.weatherappkotlin.databinding.FragmentMainBinding
import com.penkin.weatherappkotlin.presenter.MainPresenter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class MainFragment: MvpAppCompatFragment(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}