package com.gassc.fillmyseat.utility

import android.content.Context
import com.gassc.fillmyseat.constants.PreferenceConstant

class SharedPreferenceUtility (context: Context){
    val PREFERENCE_NAME = PreferenceConstant.Get.PREFERENCE_NAME
    val PREFERENCE_EMPTY_EVENTS = PreferenceConstant.Get.PREFERENCE_EMPY_EVENTS
    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun isEmptyEvent()= preference.getBoolean(PREFERENCE_EMPTY_EVENTS, true)

    fun setEmptyEvent(boolean: Boolean){
        val editor = preference.edit()
        editor.putBoolean(PREFERENCE_EMPTY_EVENTS, boolean)
        editor.apply()
    }

    fun clearAll(){
        val editor = preference.edit()
        editor.clear()
        editor.commit()
    }
}