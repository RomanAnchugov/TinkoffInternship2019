package romananchugov.ru.tinkoffinternship.data

import androidx.room.TypeConverter
import romananchugov.ru.tinkoffinternship.model.SpecificNewsModel



class Converters {
    @TypeConverter
    fun fromPublicationDate(publicationDate: SpecificNewsModel.PublicationDate):Long{
        return publicationDate.milliseconds
    }

    @TypeConverter
    fun toPublicationDate(milliseconds: Long): SpecificNewsModel.PublicationDate{
        val publicationDate = SpecificNewsModel.PublicationDate()
        publicationDate.milliseconds = milliseconds
        return publicationDate
    }

}