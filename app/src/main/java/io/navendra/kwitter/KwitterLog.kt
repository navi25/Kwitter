package io.navendra.kwitter

import android.util.Log

object KwitterLog {

    fun d(message : ()->String){
        Log.d("Kwitter",message.invoke())
    }

}
