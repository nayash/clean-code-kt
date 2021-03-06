package com.outliers.cleancodekt.framework

import android.app.Application
import com.outliers.cleancodekt.framework.dagger.CCAppComponent
import com.outliers.cleancodekt.framework.dagger.DaggerCCAppComponent

class CCApplication: Application() {

    lateinit var appComponent: CCAppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerCCAppComponent.create()
    }
}