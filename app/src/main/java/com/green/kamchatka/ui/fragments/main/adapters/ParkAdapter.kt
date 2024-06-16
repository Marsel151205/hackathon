package com.green.kamchatka.ui.fragments.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.green.kamchatka.databinding.ItemParkBinding
import com.green.kamchatka.ui.models.ParkModel
import com.green.kamchatka.utils.BaseDiffUtilItemCallback

class ParkAdapter() :
    ListAdapter<ParkModel, ParkAdapter.ParksViewHolder>(BaseDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParksViewHolder =
        ParksViewHolder(
            ItemParkBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: ParksViewHolder, position: Int) {
        getItem(position).let { holder.onBind(it) }
    }

    inner class ParksViewHolder(
        private val binding: ItemParkBinding
    ) : ViewHolder(binding.root) {
        fun onBind(model: ParkModel) {
            with(binding) {
                tvTitle.text = model.name
                Glide.with(ivPark).load(model.image).into(ivPark)
                rtFullness.stepSize = model.rating

            }
        }
    }
}