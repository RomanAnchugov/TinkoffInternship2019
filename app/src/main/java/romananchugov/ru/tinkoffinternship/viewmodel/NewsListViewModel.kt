package romananchugov.ru.tinkoffinternship.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import romananchugov.ru.tinkoffinternship.MyApplication
import romananchugov.ru.tinkoffinternship.data.NewsListRepository
import romananchugov.ru.tinkoffinternship.model.SpecificNewsModel
import timber.log.Timber
import javax.inject.Inject


class NewsListViewModel(val repository: NewsListRepository) : ViewModel() {

    lateinit var newsListLiveData:MutableLiveData<List<SpecificNewsModel>>

    @Inject
    lateinit var context: Context

    init {
        MyApplication.appComponent?.inject(this)
        newsListLiveData = MutableLiveData()
    }


    @SuppressLint("CheckResult")
    fun onCreateView() {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        if (activeNetwork.isConnected) {
            repository.getNewsListFromApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        Timber.tag("RX").i("On Next ${it.newsList.size}")
                        newsListLiveData.value = it.newsList
                    },
                    onComplete = { Timber.tag("RX").i("On Complete") },
                    onError = { Timber.tag("RX").i("On Error ${it.message}") }
                )
        }else{
            repository.getNewsListFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        Timber.tag("RX").i("On Next ${it.size}")
                        newsListLiveData.value = it
                    },
                    onComplete = { Timber.tag("RX").i("On Complete") },
                    onError = { Timber.tag("RX").i("On Error ${it.message}") }
                )
        }

    }
}