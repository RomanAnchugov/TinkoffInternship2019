package romananchugov.ru.tinkoffinternship.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import romananchugov.ru.tinkoffinternship.MyApplication
import romananchugov.ru.tinkoffinternship.R
import romananchugov.ru.tinkoffinternship.viewmodel.NewsListViewModel
import romananchugov.ru.tinkoffinternship.viewmodel.NewsListViewModelFactory
import javax.inject.Inject

class NewsListFragment : Fragment() {

    @Inject
    lateinit var factory: NewsListViewModelFactory

    lateinit var viewModel: NewsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this, factory)
            .get(NewsListViewModel::class.java)

        viewModel.onCreateView()

        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }
}
