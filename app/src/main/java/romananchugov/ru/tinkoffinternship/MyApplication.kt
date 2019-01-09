package romananchugov.ru.tinkoffinternship

import android.app.Application
import romananchugov.ru.tinkoffinternship.dagger.AppComponent
import romananchugov.ru.tinkoffinternship.dagger.ArchitectureModule
import romananchugov.ru.tinkoffinternship.dagger.DaggerAppComponent
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