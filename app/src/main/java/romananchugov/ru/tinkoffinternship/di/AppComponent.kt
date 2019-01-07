package romananchugov.ru.tinkoffinternship.di

import dagger.Component
import romananchugov.ru.tinkoffinternship.data.NewsListRepository
import romananchugov.ru.tinkoffinternship.ui.NewsListFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ArchitectureModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(fragment:NewsListFragment)
    fun inject(repository: NewsListRepository)
}