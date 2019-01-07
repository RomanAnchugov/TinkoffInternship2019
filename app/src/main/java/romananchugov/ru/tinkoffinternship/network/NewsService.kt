package romananchugov.ru.tinkoffinternship.network


import io.reactivex.Observable
import retrofit2.http.GET
import romananchugov.ru.tinkoffinternship.model.NewsListModel


interface NewsService {
    @GET("news")
    fun newsList(): Observable<NewsListModel>
}