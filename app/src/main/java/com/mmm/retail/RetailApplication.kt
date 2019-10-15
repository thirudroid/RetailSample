package com.mmm.retail

import android.app.Application
import android.content.Context

import com.mmm.retail.helper.RxEvent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RetailApplication : Application() {

    private val event by lazy { RxEvent() }

    override fun onCreate() {
        super.onCreate()
        app = this
        startKoin {
            androidLogger()
            androidContext(this@RetailApplication)
            modules(retailModules)
        }
    }

    fun event(): RxEvent {
        return event
    }

    companion object {
        private lateinit var app:RetailApplication
        fun app(): RetailApplication {
            return app
        }
    }
}
