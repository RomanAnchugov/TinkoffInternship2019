package romananchugov.ru.tinkoffinternship.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news")
class SpecificNewsModel {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int = 0

    @ColumnInfo(name = "text")
    @SerializedName("text")
    lateinit var text: String

}