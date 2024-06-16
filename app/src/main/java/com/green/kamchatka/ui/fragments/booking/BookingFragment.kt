package com.green.kamchatka.ui.fragments.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.green.kamchatka.R
import com.green.kamchatka.databinding.FragmentBookingBinding
import com.green.kamchatka.ui.fragments.booking.adapters.PeopleBookingAdapter
import com.green.kamchatka.ui.models.PeopleBookingModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!
    private val peopleBookingAdapter = PeopleBookingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        peopleBookingAdapter.submitList(listOf(PeopleBookingModel(1)))
        binding.rvPeopleBooking.adapter = peopleBookingAdapter
    }
}