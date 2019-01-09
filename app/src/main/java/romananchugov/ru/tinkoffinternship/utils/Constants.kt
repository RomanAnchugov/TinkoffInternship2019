package romananchugov.ru.tinkoffinternship.utils

import android.os.Build
import android.text.Html
import android.text.Spanned

class Constants {
    companion object {
        const val BASE_URL = "https://api.tinkoff.ru/v1/"

        fun validateHtmlText(htmlText:String): Spanned {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
            }else{
                Html.fromHtml(htmlText)
            }
        }
    }
}