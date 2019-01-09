package romananchugov.ru.tinkoffinternship.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_news_list_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import romananchugov.ru.tinkoffinternship.MyApplication
import romananchugov.ru.tinkoffinternship.R
import romananchugov.ru.tinkoffinternship.model.SpecificNewsModel
import romananchugov.ru.tinkoffinternship.utils.Constants
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    //TODO: add DiffUtils

    var newsList: List<SpecificNewsModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    @Inject
    lateinit var context: Context

    init {
        MyApplication.appComponent?.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.view_news_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.newsCv.onClick {

            val specificNewsAction = NewsListFragmentDirections
                .action_specificNewsFragment().setNews_id(newsList[position].id)

            holder.newsHeaderTv.findNavController().navigate(specificNewsAction)
        }

        holder.newsHeaderTv.text = Constants.validateHtmlText(newsList[position].text)

        val formatter = SimpleDateFormat.getDateInstance()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = newsList[position].publicationDate.milliseconds
        holder.newsPublicDateTv.text = formatter.format(calendar.time)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsCv = view.news_cv!!
        val newsHeaderTv = view.news_header_tv!!
        val newsPublicDateTv = view.news_publication_date_tv!!
    }
}