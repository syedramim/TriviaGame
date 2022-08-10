package com.example.animetrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val actionBar = supportActionBar

        val rules: TextView = findViewById(R.id.rules)

        val rulesString: String = "First of all, thank you for trying out this game!\n\n" +
                "Rules of the game:\n" + "1. Try and get as many points as possible by getting the right answers\n" +
                "2. If you do end up getting a wrong answer, your points will be set to 0 and you will have to start from square one\n" +
                "3. Just try and enjoy the app! :)\n"

        rules.text = rulesString

        actionBar!!.title = "HelpActivity"

        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}