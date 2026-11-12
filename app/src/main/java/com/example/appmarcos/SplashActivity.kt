package com.example.appmarcos

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Muestra la pantalla de inicio durante 3 segundos antes de pasar al menú principal
        Handler(Looper.getMainLooper()).postDelayed({

            // Inicia la actividad del menú principal
            startActivity(Intent(this, MainMenuActivity::class.java))
            finish()
        }, 3000) // Tiempo de espera en milisegundos

    }
}
