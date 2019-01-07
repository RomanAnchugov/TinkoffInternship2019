package romananchugov.ru.tinkoffinternship.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import romananchugov.ru.tinkoffinternship.data.NewsListRepository

class NewsListViewModelFactory(private val repository: NewsListRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsListViewModel(repository) as T
    }
}