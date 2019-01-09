package romananchugov.ru.tinkoffinternship.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*
import romananchugov.ru.tinkoffinternship.R

class MainActivity : AppCompatActivity() {
    /*TODO:
        0. back button
        1. Styles for views(colors, dimens)
        2. Custom animation for fragment navigation
        3. Store specific news in DB
        4. Work with error
        5.

     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onSupportNavigateUp(): Boolean {
        Navigation.findNavController(this, fragment_container.id).popBackStack()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        return super.onSupportNavigateUp()
    }
}
