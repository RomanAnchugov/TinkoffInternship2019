package romananchugov.ru.tinkoffinternship.data

import androidx.room.Database
import androidx.room.RoomDatabase
import romananchugov.ru.tinkoffinternship.model.SpecificNewsModel

@Database(entities = arrayOf(SpecificNewsModel::class), version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun newsDao():NewsDao
}