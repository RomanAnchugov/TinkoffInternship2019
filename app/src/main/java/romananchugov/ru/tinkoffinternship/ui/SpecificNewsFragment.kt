package romananchugov.ru.tinkoffinternship.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_specific_news.view.*
import org.jetbrains.anko.design.indefiniteSnackbar
import romananchugov.ru.tinkoffinternship.MyApplication
import romananchugov.ru.tinkoffinternship.R
import romananchugov.ru.tinkoffinternship.utils.Constants
import romananchugov.ru.tinkoffinternship.viewmodel.SpecificNewsContentViewModel
import romananchugov.ru.tinkoffinternship.viewmodel.SpecificNewsContentViewModelFactory
import javax.inject.Inject

class SpecificNewsFragment : Fragment() {

    @Inject
    lateinit var factory: SpecificNewsContentViewModelFactory

    lateinit var viewModel: SpecificNewsContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_specific_news, container, false)
        val newsId = SpecificNewsFragmentArgs.fromBundle(arguments).news_id

        viewModel = ViewModelProviders.of(this, factory).get(SpecificNewsContentViewModel::class.java)
        viewModel.onCreate(newsId)

        viewModel.contentModel.observe(this, Observer {
            view.specific_news_text_tv.text = Constants.validateHtmlText(it.payload.title.text)
            view.specific_news_content_tv.text = Constants.validateHtmlText(it.payload.content)
        })

        viewModel.error.observe(this, Observer {
            if(it){
                view.indefiniteSnackbar(R.string.error_snackbar_message, R.string.error_snackbar_action)
                {
                    viewModel.loadData(newsId)
                }
            }
        })

        return view
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}
