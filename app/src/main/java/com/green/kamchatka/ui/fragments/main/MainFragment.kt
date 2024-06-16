package com.green.kamchatka.ui.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.green.kamchatka.R
import com.green.kamchatka.databinding.FragmentMainBinding
import com.green.kamchatka.ui.fragments.main.adapters.ParkAdapter
import com.green.kamchatka.ui.models.ParkModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val parksAdapter = ParkAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        parksAdapter.submitList(localLoadToParksList())
        binding.rvParks.adapter = parksAdapter
    }

    private fun localLoadToParksList(): List<ParkModel> {
        val listParks = arrayListOf<ParkModel>()
        listParks.add(ParkModel(0, "Налычево", R.drawable.first_park, 3.5f))
        listParks.add(ParkModel(1, "Южно-камчатский", R.drawable.second_park, 3.5f))
        listParks.add(ParkModel(2, "Ключевской", R.drawable.third_park, 3.5f))
        listParks.add(ParkModel(3, "Быстринский", R.drawable.fourth_park, 3.5f))
        return listParks
    }
}