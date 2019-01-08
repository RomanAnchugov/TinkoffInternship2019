package romananchugov.ru.tinkoffinternship.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import org.jetbrains.anko.design.indefiniteSnackbar
import romananchugov.ru.tinkoffinternship.MyApplication
import romananchugov.ru.tinkoffinternship.R
import romananchugov.ru.tinkoffinternship.viewmodel.NewsListViewModel
import romananchugov.ru.tinkoffinternship.viewmodel.NewsListViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class NewsListFragment : Fragment() {

    @Inject
    lateinit var factory: NewsListViewModelFactory

    lateinit var viewModel: NewsListViewModel

    var newsListAdapter: NewsListAdapter = NewsListAdapter()//TODO: injection?

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_news_list, container, false)
        viewModel = ViewModelProviders.of(this, factory)
            .get(NewsListViewModel::class.java)

        view.news_list_rv.adapter = newsListAdapter

        view.news_list_rv.layoutManager = LinearLayoutManager(this.context)

        view.news_list_refresher.setRefreshing(true)
        view.news_list_refresher.setOnRefreshListener {
            Timber.tag("RX").i("Refresh event")
            viewModel.onRefresh()
        }



        viewModel.newsListLiveData.observe(this, Observer {
            newsListAdapter.newsList = it
            view.news_list_refresher.setRefreshing(false)

            if (it.isEmpty()) {
                view
                    .indefiniteSnackbar(R.string.no_data_snackbar_message, R.string.no_data_snackbar_action)
                    {
                        view.news_list_refresher.setRefreshing(true)
                        viewModel.onRefresh()
                    }
            }
        })

        viewModel.error.observe(this, Observer {
            if(it){
                view.news_list_refresher.setRefreshing(false)
                view.indefiniteSnackbar(R.string.error_snackbar_message, R.string.error_snackbar_action)
                {
                    viewModel.onRefresh()
                }
            }
        })

        viewModel.onCreateView()
        return view
    }
}
