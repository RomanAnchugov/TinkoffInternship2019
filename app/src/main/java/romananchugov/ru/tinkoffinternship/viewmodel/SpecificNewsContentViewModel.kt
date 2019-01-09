package romananchugov.ru.tinkoffinternship.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import romananchugov.ru.tinkoffinternship.data.NewsListRepository
import romananchugov.ru.tinkoffinternship.model.SpecificNewsContentModel
import timber.log.Timber

class SpecificNewsContentViewModel(private val repository: NewsListRepository):ViewModel() {

    private lateinit var disposable: Disposable
    var contentModel: MutableLiveData<SpecificNewsContentModel> = MutableLiveData()
    var error: MutableLiveData<Boolean> = MutableLiveData()

    fun onCreate(newsId: Int){
        loadData(newsId)
    }

    fun loadData(newsId: Int){
        disposable = repository.getSpecificNewsContentFromApi(newsId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    Timber.tag("RX").i("OnNext, load content ${it.payload.title.text}")
                    contentModel.value = it
                },
                onComplete = { Timber.tag("RX").i("Loaded content") },
                onError = { Timber.tag("RX").i("Some error occurred, during loading content ${it.message}")
                    error.value = true
                }
            )
    }

    fun onStop(){
        disposable.dispose()
    }
}