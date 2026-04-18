package com.example.appmarcos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appmarcos.db.AnimeDatabase
import kotlinx.coroutines.launch

class DeleteAnimeActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var deleteButton: Button
    private lateinit var db: AnimeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_anime)

        // Inicializar base de datos
        db = AnimeDatabase.getDatabase(this)

        // Inicializar vistas
        nameEditText = findViewById(R.id.etNameDelete)
        deleteButton = findViewById(R.id.btnDelete)

        // Configurar botón de borrar
        deleteButton.setOnClickListener {
            deleteAnime()
        }
    }

    private fun deleteAnime() {
        val name = nameEditText.text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa el nombre del anime", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            try {
                val dao = db.animeDao()

                // Intentar borrar de cada tabla
                val deletedFromVisto = dao.deleteVistoByName(name)
                val deletedFromViendo = dao.deleteViendoByName(name)
                val deletedFromPendientes = dao.deletePendientesByName(name)

                val totalDeleted = deletedFromVisto + deletedFromViendo + deletedFromPendientes

                if (totalDeleted > 0) {
                    Toast.makeText(
                        this@DeleteAnimeActivity,
                        "Anime '$name' borrado exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    nameEditText.text.clear()
                } else {
                    Toast.makeText(
                        this@DeleteAnimeActivity,
                        "El anime '$name' no fue encontrado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@DeleteAnimeActivity,
                    "Error al borrar: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
