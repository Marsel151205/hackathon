package com.green.kamchatka.ui.fragments.chat

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.green.kamchatka.R
import com.green.kamchatka.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private val CAMERA_REQUEST_CODE = 100
    private val GALLERY_REQUEST_CODE = 101
    private var countAttachedReport = 0
    private var countAttachedComment = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    @SuppressLint("SetTextI18n")
    private fun setupListeners() {
        with(binding) {
            tvAttachPhotoReport.setOnClickListener {
                showDialogChooseSource()
                countAttachedReport++
                if (countAttachedReport >= 1) {
                    tvCountAttachedReport.visibility = View.VISIBLE
                    tvCountAttachedReport.text = "Прикреплена: $countAttachedReport x"
                }
            }
            tvAttachPhotoComment.setOnClickListener {
                showDialogChooseSource()
                countAttachedComment++
                if (countAttachedComment >= 1) {
                    tvCountAttachedComment.visibility = View.VISIBLE
                    tvCountAttachedComment.text = "Прикреплена: $countAttachedComment x"
                }
            }
            btnSendReport.setOnClickListener {
                if (etReport.text.isNotEmpty()) {
                    findNavController().navigate(R.id.action_chatFragment_to_resultFragment)
                }else {
                    Snackbar.make(it, "Заполните поле перед отправкой!", Snackbar.ANIMATION_MODE_SLIDE).show()
                }
            }
            btnSendComment.setOnClickListener {
                if (etComment.text.isNotEmpty()) {
                    findNavController().navigate(R.id.action_chatFragment_to_resultFragment)
                }else {
                    Snackbar.make(it, "Заполните поле перед отправкой!", Snackbar.ANIMATION_MODE_SLIDE).show()
                }
            }
        }
    }

    private fun showDialogChooseSource() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_choose_source, null)
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

        dialogView.findViewById<LinearLayout>(R.id.btn_gallery).setOnClickListener {
            openGallery()
            dialog.dismiss()
        }
        dialogView.findViewById<LinearLayout>(R.id.btn_camera).setOnClickListener {
            openCamera()
            dialog.dismiss()
        }
        dialogView.findViewById<MaterialButton>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun openCamera() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {

            requestPermissions(
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE
            )
        } else {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, 1)
        }
    }


    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, 1)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Camera permission is required to use the camera",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {

                }

                GALLERY_REQUEST_CODE -> {
                }
            }
        }
    }
}