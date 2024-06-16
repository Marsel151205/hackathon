package com.green.kamchatka.ui.fragments.booking.adapters

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.green.kamchatka.databinding.ItemPeopleBookingBinding
import com.green.kamchatka.ui.models.PeopleBookingModel
import com.green.kamchatka.utils.BaseDiffUtilItemCallback

class PeopleBookingAdapter :
    ListAdapter<PeopleBookingModel, PeopleBookingAdapter.PeopleBookingAdapter>(
        BaseDiffUtilItemCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleBookingAdapter =
        PeopleBookingAdapter(
            ItemPeopleBookingBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    override fun onBindViewHolder(holder: PeopleBookingAdapter, position: Int) {
        getItem(position).let { holder.onBind(it) }
    }

    inner class PeopleBookingAdapter(
        private val binding: ItemPeopleBookingBinding
    ) : ViewHolder(binding.root) {
        fun onBind(model: PeopleBookingModel) {
            binding.tvScore.text = "${model.id} человек"
        }
    }
}