package romananchugov.ru.tinkoffinternship.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*
import romananchugov.ru.tinkoffinternship.R

class MainActivity : AppCompatActivity() {
    /*TODO:
        4. Work with error

        5. README

     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onSupportNavigateUp(): Boolean {
        Navigation.findNavController(this, fragment_container.id).popBackStack()
        hideBackButton()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        hideBackButton()
    }

    private fun hideBackButton(){
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
    }
}
