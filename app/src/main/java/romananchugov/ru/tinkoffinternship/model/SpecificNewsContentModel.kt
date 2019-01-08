package romananchugov.ru.tinkoffinternship.model

import com.google.gson.annotations.SerializedName

class SpecificNewsContentModel{
    @SerializedName("payload")
    lateinit var payload:PayloadModel

    class PayloadModel{

        @SerializedName("title")
        lateinit var title:TitleModel

        @SerializedName("content")
        lateinit var content:String
    }

    class TitleModel{
        @SerializedName("text")
        lateinit var text:String
    }
}