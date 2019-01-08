package romananchugov.ru.tinkoffinternship.ui

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_news_list_item.view.*
import romananchugov.ru.tinkoffinternship.MyApplication
import romananchugov.ru.tinkoffinternship.R
import romananchugov.ru.tinkoffinternship.model.SpecificNewsModel
import javax.inject.Inject

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

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

        val text: Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(newsList[position].text, Html.FROM_HTML_MODE_COMPACT)
        }else{
            Html.fromHtml(newsList[position].text)
        }
        holder.textView.text = text
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.news_header_tv
    }
}