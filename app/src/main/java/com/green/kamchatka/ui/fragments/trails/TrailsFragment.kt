package com.green.kamchatka.ui.fragments.trails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.green.kamchatka.App
import com.green.kamchatka.R
import com.green.kamchatka.databinding.FragmentTrailsBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapType
import com.yandex.mapkit.mapview.MapView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrailsFragment : Fragment() {

    private var _binding: FragmentTrailsBinding? = null
    private val binding get() = _binding!!
    private var mapView: MapView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupView()
        setupListeners()
    }

    private fun initialize() {
        initializeMap()
    }


    private fun setupView() {
        setupMap()
    }

    private fun initializeMap() {
        MapKitFactory.initialize(requireContext())
    }

    private fun setupMap() {
        mapView = binding.mapViewYandex
        mapView?.map?.let { map ->
            map.mapType = MapType.MAP
            map.isRotateGesturesEnabled = true
            map.move(
                CameraPosition(Point(55.655465, 160.485067), 15.0f, 0.0f, 0.0f)
            )
        }
    }

    private fun setupListeners() {
        binding.btnOrder.setOnClickListener {
            findNavController().navigate(R.id.action_trailsFragment_to_bookingFragment)
        }
    }
}