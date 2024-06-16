package com.green.kamchatka.ui.models

import com.green.kamchatka.utils.BaseDiffModel

data class MyTicketModel(
    override val id: Int,
    val date: String,
    val title: String,
    val qrCode: Int,
    val countPeople: Int
) : BaseDiffModel<Int>
