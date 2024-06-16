package com.green.kamchatka.ui.fragments.profile.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.green.kamchatka.databinding.ItemTicketBinding
import com.green.kamchatka.ui.models.MyTicketModel
import com.green.kamchatka.utils.BaseDiffUtilItemCallback

class MyTicketAdapter :
    ListAdapter<MyTicketModel, MyTicketAdapter.MyTicketsViewHolder>(BaseDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTicketsViewHolder =
        MyTicketsViewHolder(
            ItemTicketBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    override fun onBindViewHolder(holder: MyTicketsViewHolder, position: Int) {
        getItem(position).let { holder.onBind(it) }
    }

    class MyTicketsViewHolder(
        private val binding: ItemTicketBinding
    ) : ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(model: MyTicketModel) {
            with(binding) {
                tvDate.text = model.date
                tvNameTrail.text = model.title
                tvNumberPeople.text = "${model.countPeople} человек"
                Glide.with(ivQrCode).load(model.qrCode).into(ivQrCode)
                tvCancelDate.text = model.date
                btnCancel.setOnClickListener {
                    llCanceledTrail.visibility = View.VISIBLE
                    llActive.visibility = View.GONE
                }
                btnRecover.setOnClickListener {
                    llCanceledTrail.visibility = View.GONE
                    llActive.visibility = View.VISIBLE
                }
            }
        }
    }
}