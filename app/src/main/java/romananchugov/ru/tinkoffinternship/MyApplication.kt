package romananchugov.ru.tinkoffinternship

import android.app.Application
import romananchugov.ru.tinkoffinternship.di.AppComponent
import romananchugov.ru.tinkoffinternship.di.ArchitectureModule
import romananchugov.ru.tinkoffinternship.di.DaggerAppComponent
import timber.log.Timber

class MyApplication : Application() {
    init {
        appComponent = DaggerAppComponent.builder().architectureModule(ArchitectureModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        var appComponent:AppComponent? = null
    }
}