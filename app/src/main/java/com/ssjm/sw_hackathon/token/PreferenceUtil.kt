package com.ssjm.sw_hackathon.token

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun getInt(key: String, defValue: Int): Int {
        return prefs.getInt(key, defValue).toInt()
    }

    fun setInt(key: String, int: Int) {
        prefs.edit().putInt(key, int).apply()
    }

    fun removeString(key: String) {
        prefs.edit().remove(key).apply()
    }

    fun clearString() {
        prefs.edit().clear().apply()
    }
}