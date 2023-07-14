package com.afoxplus.products.demo.global

import com.afoxplus.network.global.AppProperties
import com.afoxplus.products.demo.BuildConfig
import javax.inject.Inject

class AppPropertiesDemo @Inject constructor() : AppProperties {
    override fun getCurrencyID(): String {
        return "getCurrencyID"
    }

    override fun getDeviceData(): String {
        return "getDeviceData"
    }

    override fun getUserUUID(): String {
        return "getUserUUID"
    }

    override fun isAppDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}