package com.green.kamchatka.utils

interface BaseDiffModel<T> {
    val id: T?
    override fun equals(other: Any?): Boolean
}