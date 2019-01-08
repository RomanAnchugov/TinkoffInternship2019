package romananchugov.ru.tinkoffinternship.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.design.indefiniteSnackbar
import romananchugov.ru.tinkoffinternship.MyApplication
import romananchugov.ru.tinkoffinternship.R
import romananchugov.ru.tinkoffinternship.data.NewsListRepository
import timber.log.Timber
import javax.inject.Inject

class SpecificNewsFragment : Fragment() {

    @Inject
    lateinit var repository: NewsListRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_specific_news, container, false)

        val newsId = SpecificNewsFragmentArgs.fromBundle(arguments).news_id

        repository.getSpecificNewsContentFromApi(newsId)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribeBy (
                onNext = {Timber.tag("RX").i("OnNext, load content ${it.payload.title.text}")},
                onComplete = {Timber.tag("RX").i("Loaded content")},
                onError = {Timber.tag("RX").i("Some error occurred, during loading content ${it.message}")}
            )

        view.indefiniteSnackbar("Id is: $newsId")

        return view
    }
}
