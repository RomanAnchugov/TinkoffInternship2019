package romananchugov.ru.tinkoffinternship.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import romananchugov.ru.tinkoffinternship.MyApplication
import romananchugov.ru.tinkoffinternship.data.NewsListRepository
import romananchugov.ru.tinkoffinternship.model.SpecificNewsModel
import timber.log.Timber
import javax.inject.Inject




class NewsListViewModel(val repository: NewsListRepository) : ViewModel() {

    var newsListLiveData:MutableLiveData<List<SpecificNewsModel>>
    var error:MutableLiveData<Boolean>

    lateinit var disposable: Disposable

    @Inject
    lateinit var context: Context

    init {
        MyApplication.appComponent?.inject(this)
        newsListLiveData = MutableLiveData()
        error = MutableLiveData()
    }

    fun onCreateView() {
        loadData()
    }

    fun onRefresh() {
        loadData()
    }

    private fun loadData(){
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        if (activeNetwork != null && activeNetwork.isConnected) {
            Timber.tag("RX").i("Get data from API")
            disposable = repository.getNewsListFromApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        Timber.tag("RX").i("On Next ${it.newsList.size}")
                        newsListLiveData.value = it.newsList
                    },
                    onComplete = {
                        Timber.tag("RX").i("get news from api On Complete")
                    },
                    onError = {
                        Timber.tag("RX").i("On Error ${it.message}")
                        error.value = true
                    }
                )
        }else{
            Timber.tag("RX").i("Get data from DB")
            disposable = repository.getNewsListFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        Timber.tag("RX").i("On Next ${it.size}")
                        newsListLiveData.value = it.asReversed()
                    },
                    onComplete = {
                        Timber.tag("RX").i("On Complete")
                    },
                    onError = {
                        Timber.tag("RX").i("On Error ${it.message}")
                        error.value = true
                    }
                )
        }
    }

    fun onStop(){
        disposable.dispose()
    }
}