package com.green.kamchatka.ui.fragments.trails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.green.kamchatka.R
import com.green.kamchatka.databinding.FragmentTrailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrailsFragment : Fragment() {

    private var _binding: FragmentTrailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}