package com.example.appmarcos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController


class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        // Configura el modo de pantalla completa según la versión de Android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            // Oculta barras de estado y navegación
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.hide(
                WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars()
            )

            // Permite mostrar las barras temporalmente al deslizar
            window.insetsController?.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else {


            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }

        // Referencias a los botones del menú principal
        val addAnimeButton = findViewById<Button>(R.id.btnAddAnime)
        val deleteAnimeButton = findViewById<Button>(R.id.btnDeleteAnime)
        val consultAnimesButton = findViewById<Button>(R.id.btnConsultAnimes)

        // Abre la pantalla para añadir un anime
        addAnimeButton.setOnClickListener {
            startActivity(Intent(this, AddAnimeActivity::class.java))
        }

        // Abre la pantalla para borrar un anime
        deleteAnimeButton.setOnClickListener {
            startActivity(Intent(this, DeleteAnimeActivity::class.java))
        }

        // Abre la pantalla para consultar los animes guardados
        consultAnimesButton.setOnClickListener {
            startActivity(Intent(this, ConsultAnimeActivity::class.java))
        }
    }
}
