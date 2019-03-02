package com.okysoft.annictim.infra

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import com.okysoft.annictim.BuildConfig

abstract class SharedPreferenceManager(filename: String, context: Context) {

    private val INT_DEFAULT = -1

    private val STRING_DEFAULT = ""

    private val BOOLEAN_DEFAULT = false

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(
                BuildConfig.APPLICATION_ID + ".preferences." + filename,
                Context.MODE_PRIVATE)
    }

    protected fun saveInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    protected fun getInt(key: String): Int = sharedPreferences.getInt(key, INT_DEFAULT)

    protected fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    protected fun getString(key: String): String = sharedPreferences.getString(key, STRING_DEFAULT)

    protected fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    protected fun saveByteArray(key: String, value: ByteArray) {
        val encodedValue = Base64.encodeToString(value, Base64.DEFAULT)
        sharedPreferences.edit().putString(key, encodedValue).apply()
    }

    protected fun getByteArray(key: String): ByteArray {
        val encodedValue = sharedPreferences.getString(key, STRING_DEFAULT)
        return Base64.decode(encodedValue, Base64.DEFAULT)
    }

}