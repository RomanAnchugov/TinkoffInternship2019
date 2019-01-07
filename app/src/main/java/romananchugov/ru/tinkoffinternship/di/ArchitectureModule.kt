package romananchugov.ru.tinkoffinternship.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import romananchugov.ru.tinkoffinternship.data.AppDatabase
import romananchugov.ru.tinkoffinternship.data.NewsListRepository
import romananchugov.ru.tinkoffinternship.viewmodel.NewsListViewModelFactory
import javax.inject.Singleton

@Module
class ArchitectureModule(val application: Application) {

    @Provides
    @Singleton
    fun providesApplicationCotext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providesApplication():Application{
        return application
    }

    @Provides
    @Singleton
    fun providesNewsListRepository(): NewsListRepository{
        return NewsListRepository.getInstance()
    }

    @Provides
    @Singleton
    fun providesNewsListViewModelFactory(): NewsListViewModelFactory{
        val repository = providesNewsListRepository()
        return NewsListViewModelFactory(repository = repository)
    }

    @Provides
    @Singleton
    fun providesAppDatabase():AppDatabase{
        return Room.databaseBuilder(application.applicationContext,
            AppDatabase::class.java, "database").build()
    }
}