package com.example.appmarcos

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appmarcos.db.AnimeDatabase
import com.example.appmarcos.db.Pendientes
import com.example.appmarcos.db.Viendo
import com.example.appmarcos.db.Visto
import kotlinx.coroutines.launch

class AddAnimeActivity : AppCompatActivity() {

    private lateinit var statusSpinner: Spinner
    private lateinit var nameEditText: EditText
    private lateinit var typeEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var db: AnimeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_anime)

        // Inicializar base de datos
        db = AnimeDatabase.getDatabase(this)

        // Inicializar vistas
        nameEditText = findViewById(R.id.etName)
        typeEditText = findViewById(R.id.etType)
        statusSpinner = findViewById(R.id.spinnerStatus)
        saveButton = findViewById(R.id.btnSave)

        val options = listOf(
            getString(R.string.status_pending),
            getString(R.string.status_watching),
            getString(R.string.status_seen)
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        statusSpinner.adapter = adapter

        // Configurar botón de guardar
        saveButton.setOnClickListener {
            saveAnime()
        }
    }

    private fun saveAnime() {
        val name = nameEditText.text.toString().trim()
        val type = typeEditText.text.toString().trim()
        val status = statusSpinner.selectedItemPosition

        if (name.isEmpty() || type.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            try {
                when (status) {
                    0 -> db.animeDao().insertPendientes(Pendientes(name = name, type = type))
                    1 -> db.animeDao().insertViendo(Viendo(name = name, type = type))
                    2 -> db.animeDao().insertVisto(Visto(name = name, type = type))
                }

                Toast.makeText(this@AddAnimeActivity, "Anime guardado exitosamente", Toast.LENGTH_SHORT).show()
                finish()
            } catch (e: Exception) {
                Toast.makeText(this@AddAnimeActivity, "Error al guardar: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
