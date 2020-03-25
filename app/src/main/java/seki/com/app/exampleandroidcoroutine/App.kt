package seki.com.app.exampleandroidcoroutine

import android.app.Application

class App : Application() {

    lateinit var component: Component

    override fun onCreate() {
        super.onCreate()
        component = DaggerComponent.builder().build()
    }
}