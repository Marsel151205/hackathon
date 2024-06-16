package com.green.kamchatka.ui.fragments.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.green.kamchatka.R
import com.green.kamchatka.data.local.sharedPreferences.PreferencesHelper
import com.green.kamchatka.databinding.FragmentProfileBinding
import com.green.kamchatka.ui.fragments.profile.adapters.MyTicketAdapter
import com.green.kamchatka.ui.models.MyTicketModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val myTicketAdapter = MyTicketAdapter()
    private val preferencesHelper: PreferencesHelper by lazy {
        PreferencesHelper(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupView()
        setupListeners()
    }

    private fun initialize() {
        initializeRecyclerView()
    }

    private fun setupView() {
        setupPersonalData()
    }

    private fun setupListeners() {
        binding.containerPersonalData.setOnClickListener {
            showDialogChangeAPersonalData()
        }
        binding.ivUserPhoto.setOnClickListener {
            changeUserPhoto()
        }
    }

    private fun initializeRecyclerView() {
        myTicketAdapter.submitList(localLoadDataFromMyTickets())
        binding.rvMyTickets.adapter = myTicketAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun setupPersonalData() {
        with(binding) {
            if (preferencesHelper.imageUri != null) {
                Glide.with(ivUserPhoto).load(preferencesHelper.imageUri).into(ivUserPhoto)
            }
            tvNickaname.text = "Никнейм: ${preferencesHelper.username}"
            tvEmail.text = "E-mail: ${preferencesHelper.email}"
            tvPhone.text = "Телефон: ${preferencesHelper.phone}"
        }
    }

    private fun localLoadDataFromMyTickets(): List<MyTicketModel> {
        val myTickets = mutableListOf<MyTicketModel>()

        myTickets.add(
            MyTicketModel(
                0,
                "15 сентебря 2024 г.",
                "Парк Налычево\nТропа Авачинский-кордон\n“Центральный”",
                R.drawable.qr_code,
                3
            )
        )
        myTickets.add(
            MyTicketModel(
                1,
                "18 августа 2025 г.",
                "Парк Налычево\nТропа Авачинский-кордон\n“Центральный”",
                R.drawable.qr_code,
                2
            )
        )
        return myTickets
    }

    @SuppressLint("SetTextI18n")
    private fun showDialogChangeAPersonalData() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_person_data, null)
        val dialog = AlertDialog.Builder(requireContext(), R.style.CustomDialog)
            .setView(dialogView)
            .create()

        dialog.setOnShowListener {
            val window = dialog.window
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        val etUsername = dialogView.findViewById<EditText>(R.id.et_username)
        val etEmail = dialogView.findViewById<EditText>(R.id.et_email)
        val etPhone = dialogView.findViewById<EditText>(R.id.et_phone_number)

        dialogView.findViewById<MaterialButton>(R.id.btn_change_personal_data).setOnClickListener {
            preferencesHelper.username = etUsername.text.toString()
            preferencesHelper.email = etEmail.text.toString()
            preferencesHelper.phone = etPhone.text.toString()
            binding.tvNickaname.text = "Никнейм: ${etUsername.text}"
            binding.tvEmail.text = "E-mail: ${etEmail.text}"
            binding.tvPhone.text = "Телефон: ${etPhone.text}"
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun changeUserPhoto() {

            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, 101)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            101 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    changeUserPhoto()
                } else {
                    Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                101 -> {
                    val imageUri = data?.data
                    if (imageUri != null) {
                        requireContext().grantUriPermission(
                            requireContext().packageName,
                            imageUri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                        )
                        preferencesHelper.imageUri = imageUri.toString()
                        Glide.with(binding.ivUserPhoto).load(imageUri.toString()).into(binding.ivUserPhoto)
                    }
                }
            }
        }
    }
}