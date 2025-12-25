package com.goboard.settings

import android.content.Context
import com.goboard.model.DefaultLayout
import com.goboard.model.KeyboardLayout
import com.google.gson.Gson

class KeyboardLayoutManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("goboard_settings", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveLayout(layout: KeyboardLayout) {
        val json = gson.toJson(layout)
        sharedPreferences.edit().putString("keyboard_layout", json).apply()
    }

    fun loadLayout(): KeyboardLayout {
        val json = sharedPreferences.getString("keyboard_layout", null)
        return if (json != null) {
            gson.fromJson(json, KeyboardLayout::class.java)
        } else {
            DefaultLayout.qwerty
        }
    }
}
