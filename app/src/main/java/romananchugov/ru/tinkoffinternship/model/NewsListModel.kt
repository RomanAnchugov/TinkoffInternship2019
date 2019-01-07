package romananchugov.ru.tinkoffinternship.model

import com.google.gson.annotations.SerializedName

class NewsListModel {
    @SerializedName("payload")
    lateinit var newsList: List<SpecificNewsModel>
}