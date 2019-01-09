package romananchugov.ru.tinkoffinternship.data

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import romananchugov.ru.tinkoffinternship.MyApplication
import romananchugov.ru.tinkoffinternship.model.NewsListModel
import romananchugov.ru.tinkoffinternship.model.SpecificNewsContentModel
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

    fun getNewsListFromApi(): Observable<NewsListModel> {
        return newsService.getNewsList()
            .doOnNext {
                Timber.tag("RX").i("Fetch news from API")
                saveNewsInDb(it.newsList)
            }
    }

    fun getNewsListFromDb(): Observable<List<SpecificNewsModel>> {
        return newsDao.getNews()
            .toObservable()
            .doOnNext {
                Timber.tag("RX").i("Fetch news from DB")
            }
    }

    @SuppressLint("CheckResult")
    fun saveNewsInDb(news: List<SpecificNewsModel>) {
        Observable.fromCallable { newsDao.insertAll(news) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribeBy(
                onNext = { Timber.tag("RX").i("OnNext Save news in DB ${news.size}") },
                onComplete = { Timber.tag("RX").i("OnComplete Save news in DB") },
                onError = { Timber.tag("RX").i("OnError Save news in DB ${it.message}") }
            )

    }

    fun getSpecificNewsContentFromApi(id: Int): Observable<SpecificNewsContentModel> {
        return newsService.getSpecificNews(id)
    }

    companion object {
        private var instance: NewsListRepository? = null

        fun getInstance(): NewsListRepository {
            return instance ?: NewsListRepository().also {
                instance = it
            }
        }
    }
}