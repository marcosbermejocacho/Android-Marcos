package com.example.appmarcos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val addAnimeButton = findViewById<Button>(R.id.btnAddAnime)
        val deleteAnimeButton = findViewById<Button>(R.id.btnDeleteAnime)
        val consultAnimesButton = findViewById<Button>(R.id.btnConsultAnimes)

        addAnimeButton.setOnClickListener {
            startActivity(Intent(this, AddAnimeActivity::class.java))
        }

        deleteAnimeButton.setOnClickListener {
            startActivity(Intent(this, DeleteAnimeActivity::class.java))
        }

        consultAnimesButton.setOnClickListener {
            startActivity(Intent(this, ConsultAnimeActivity::class.java))
        }
    }
}
