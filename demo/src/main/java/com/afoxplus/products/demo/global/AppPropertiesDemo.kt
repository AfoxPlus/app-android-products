package com.afoxplus.products.demo.global

import com.afoxplus.network.global.AppProperties
import com.afoxplus.products.demo.BuildConfig
import javax.inject.Inject

class AppPropertiesDemo @Inject constructor() : AppProperties {
    override fun getDeviceData(): String {
        return ""
    }

    override fun isAppDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}