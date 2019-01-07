package romananchugov.ru.tinkoffinternship.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import romananchugov.ru.tinkoffinternship.data.NewsListRepository
import timber.log.Timber

class NewsListViewModel(val repository: NewsListRepository):ViewModel() {


    @SuppressLint("CheckResult")
    fun onCreateView(){

        repository.getNewsListFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {Timber.tag("RX").i("Completed")},
                onError = {Timber.tag("RX").i("Error ${it.message}")},
                onNext = {Timber.tag("RX").i("OnNext: ${it.size}")}
                )
    }
}