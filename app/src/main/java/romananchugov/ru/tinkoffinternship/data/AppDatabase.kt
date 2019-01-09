package romananchugov.ru.tinkoffinternship.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import romananchugov.ru.tinkoffinternship.model.SpecificNewsModel

@Database(entities = arrayOf(SpecificNewsModel::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun newsDao():NewsDao
}