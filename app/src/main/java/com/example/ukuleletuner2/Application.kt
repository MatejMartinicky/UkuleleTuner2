package com.example.ukuleletuner2

import android.app.Application
import android.content.Context
import com.example.ukuleletuner2.utility.LocaleHelper

//source
class MyApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.setLocale(base, LocaleHelper.getLanguage(base)))
    }
}