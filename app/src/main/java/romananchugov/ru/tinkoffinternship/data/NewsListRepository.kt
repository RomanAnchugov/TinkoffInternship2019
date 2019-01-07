package romananchugov.ru.tinkoffinternship.data

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import romananchugov.ru.tinkoffinternship.MyApplication
import romananchugov.ru.tinkoffinternship.model.NewsListModel
import romananchugov.ru.tinkoffinternship.model.SpecificNewsModel
import romananchugov.ru.tinkoffinternship.network.NewsService
import timber.log.Timber
import javax.inject.Inject

class NewsListRepository private constructor() {
    @Inject
    lateinit var newsService: NewsService

    @Inject
    lateinit var database: AppDatabase

    private val newsDao: NewsDao

    init {
        MyApplication.appComponent?.inject(this)
        newsDao = database.newsDao()
    }

    fun getNewsListFromApi(): Observable<NewsListModel>{
        return newsService.newsList().doOnNext{
            Timber.tag("RX").i("Fetch news from API")
            saveNewsInDb(it)
        }

    }

    fun getNewsListFromDb(): Observable<List<SpecificNewsModel>>{
        return newsDao.getNews()
            .toObservable()
            .doOnNext{
                Timber.tag("RX").i("Fetch news from DB")
            }
    }

    @SuppressLint("CheckResult")
    fun saveNewsInDb(newsList:NewsListModel){
        val news = newsList.newsList
        Observable.fromCallable{newsDao.insertAll(news)}
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe{
                Timber.tag("RX").i("news inserted ${news.size}")
            }

    }

    companion object {
        private var instance:NewsListRepository? = null

        fun getInstance(): NewsListRepository{
            return instance ?: NewsListRepository().also {
                instance = it
            }
        }
    }
}