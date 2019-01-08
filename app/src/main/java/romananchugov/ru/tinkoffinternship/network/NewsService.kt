package romananchugov.ru.tinkoffinternship.network


import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import romananchugov.ru.tinkoffinternship.model.NewsListModel
import romananchugov.ru.tinkoffinternship.model.SpecificNewsContentModel


interface NewsService {
    @GET("news")
    fun getNewsList(): Observable<NewsListModel>

    @GET("news_content")
    fun getSpecificNews(@Query("id") id:Int): Observable<SpecificNewsContentModel>
}