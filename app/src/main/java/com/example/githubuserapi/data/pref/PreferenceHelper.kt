package com.example.githubuserapi.data.pref

import android.content.Context
import android.content.SharedPreferences
import com.example.githubuserapi.utils.PREF_NAME
import com.example.githubuserapi.utils.PRIVATE_MODE
import com.example.githubuserapi.utils.USERNAME

class PreferenceHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

    var username: String?
        get() {
            return sharedPreferences.getString(USERNAME, "")
        }
        set(value) {
            sharedPreferences.edit().apply {
                putString(USERNAME, value)
                apply()
            }
        }
}