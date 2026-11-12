package com.example.appmarcos

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appmarcos.db.AnimeDatabase
import kotlinx.coroutines.launch

class DeleteAnimeActivity : AppCompatActivity() {

    // Campo de texto donde el usuario introduce el nombre del anime a borrar
    private lateinit var nameEditText: EditText

    // Botón que ejecuta la acción de borrado
    private lateinit var deleteButton: Button

    // Instancia de la base de datos
    private lateinit var db: AnimeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_anime)

        // Activa la flecha de retroceso en el ActionBar y establece el título de la pantalla
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Borrar anime"

        // Inicializar base de datos
        db = AnimeDatabase.getDatabase(this)

        // Referencias a los elementos de la interfaz
        nameEditText = findViewById(R.id.etNameDelete)
        deleteButton = findViewById(R.id.btnDelete)

        // Configura el botón para borrar el anime cuando se pulse
        deleteButton.setOnClickListener {
            deleteAnime()
        }
    }

    // Maneja las acciones del menú superior
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Si el usuario pulsa la flecha de atrás, vuelve a la pantalla anterior
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // Elimina un anime de la base de datos según el nombre introducido
    private fun deleteAnime() {

        // Obtiene el nombre introducido por el usuario y elimina espacios sobrantes
        val name = nameEditText.text.toString().trim()

        // Verifica que el usuario haya escrito un nombre antes de intentar borrar
        if (name.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa el nombre del anime", Toast.LENGTH_SHORT).show()
            return
        }

        // Ejecuta la operación de borrado
        lifecycleScope.launch {
            try {
                val dao = db.animeDao()

                // Intenta borrar el anime en cada una de las tablas según su estado
                val deletedFromVisto = dao.deleteVisto(name)
                val deletedFromViendo = dao.deleteViendo(name)
                val deletedFromPendientes = dao.deletePendientes(name)

                val totalDeleted = deletedFromVisto + deletedFromViendo + deletedFromPendientes

                // Si se eliminó al menos un registro, se notifica al usuario
                if (totalDeleted > 0) {
                    Toast.makeText(
                        this@DeleteAnimeActivity,
                        "Anime '$name' borrado exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Limpia el campo de texto después de borrar
                    nameEditText.text.clear()
                } else {

                    // Si no se encontró el anime en ninguna tabla
                    Toast.makeText(
                        this@DeleteAnimeActivity,
                        "El anime '$name' no fue encontrado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {

                // Maneja cualquier error inesperado durante el proceso
                Toast.makeText(
                    this@DeleteAnimeActivity,
                    "Error al borrar: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
