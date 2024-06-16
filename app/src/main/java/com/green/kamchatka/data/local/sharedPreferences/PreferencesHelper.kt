package com.green.kamchatka.data.local.sharedPreferences

import android.content.Context
import android.net.Uri

class PreferencesHelper(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    var username: String?
        set(value) = sharedPreferences.edit().putString("username", value).apply()
        get() = sharedPreferences.getString("username", "unknown")

    var email: String?
        set(value) = sharedPreferences.edit().putString("email", value).apply()
        get() = sharedPreferences.getString("email", "unknown")

    var phone: String?
        set(value) = sharedPreferences.edit().putString("phone", value).apply()
        get() = sharedPreferences.getString("phone", "unknown")

    var imageUri: String?
        set(value) = sharedPreferences.edit().putString("image", value).apply()
        get() = sharedPreferences.getString("image", null)
}