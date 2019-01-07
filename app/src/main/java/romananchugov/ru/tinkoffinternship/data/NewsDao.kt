package romananchugov.ru.tinkoffinternship.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import romananchugov.ru.tinkoffinternship.model.SpecificNewsModel

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getNews(): Single<List<SpecificNewsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news:SpecificNewsModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<SpecificNewsModel>)
}