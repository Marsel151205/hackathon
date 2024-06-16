package com.green.kamchatka.ui.models

import com.green.kamchatka.utils.BaseDiffModel

data class ParkModel(
    override val id: Int,
    val name: String,
    val image: Int,
    val rating: Float
) : BaseDiffModel<Int>