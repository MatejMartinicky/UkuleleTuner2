/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2

import android.app.Application
import android.content.Context
import com.example.ukuleletuner2.utility.LocaleHelper

/**
 * sets up locale configuration on app startup
 */
class MyApplication : Application() {
    /**
     * applies saved language locale when app context is created
     * ensures consistent language across entire app lifecycle
     *
     * @param base base context to attach locale to
     */
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.setLocale(base, LocaleHelper.getLanguage(base)))
    }
}