package com.example.rxstudy.login

import android.content.Context
import android.content.Context.MODE_PRIVATE

class PrefUtil(context: Context) {
   companion object {
      const val DEFAULT_VALUE = ""
      const val PREF_DEFAULT = "PREF_DEFAULT"
      const val ACCESS_TOKEN = "LOGIN_TOKEN"
   }

   private val sharedPref = context.getSharedPreferences(PREF_DEFAULT, MODE_PRIVATE)
   private fun getEdit() = sharedPref.edit()

   fun saveToken(token: String) {
      getEdit().putString(ACCESS_TOKEN, token).apply()
   }

   fun getToken() = sharedPref.getString(ACCESS_TOKEN, DEFAULT_VALUE)
}
