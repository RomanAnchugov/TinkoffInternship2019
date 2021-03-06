package romananchugov.ru.tinkoffinternship.dagger

import dagger.Component
import romananchugov.ru.tinkoffinternship.data.NewsListRepository
import romananchugov.ru.tinkoffinternship.ui.MainActivity
import romananchugov.ru.tinkoffinternship.ui.NewsListAdapter
import romananchugov.ru.tinkoffinternship.ui.NewsListFragment
import romananchugov.ru.tinkoffinternship.ui.SpecificNewsFragment
import romananchugov.ru.tinkoffinternship.viewmodel.NewsListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ArchitectureModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment:NewsListFragment)
    fun inject(repository: NewsListRepository)
    fun inject(newsListViewModel: NewsListViewModel)
    fun inject(adapter: NewsListAdapter)
    fun inject(fragment: SpecificNewsFragment)
}