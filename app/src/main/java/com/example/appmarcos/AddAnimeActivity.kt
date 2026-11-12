package com.example.appmarcos

import android.view.MenuItem
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

        // Activa la flecha de retroceso en el ActionBar y establece el título de la pantalla
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Añadir anime"

        // Inicializar base de datos
        db = AnimeDatabase.getDatabase(this)

        // Inicializar vistas
        nameEditText = findViewById(R.id.etName)
        typeEditText = findViewById(R.id.etType)
        statusSpinner = findViewById(R.id.spinnerStatus)
        saveButton = findViewById(R.id.btnSave)

        //Opciones disponibles para el estado del anime
        val options = listOf(
            getString(R.string.status_pending),
            getString(R.string.status_watching),
            getString(R.string.status_seen)
        )

        // Adaptador que conecta la lista de opciones con el Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)

        // Establece el diseño del desplegable
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        statusSpinner.adapter = adapter

        // Configurar botón de guardar
        saveButton.setOnClickListener {
            saveAnime()
        }
    }

    // Detecta si ha pulsado la flecha de retroceso
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    //Guarda un nuevo anime en la base de datos
    private fun saveAnime() {

        //Obtiene los valores introducidos por el usuario
        val name = nameEditText.text.toString().trim()
        val type = typeEditText.text.toString().trim()
        val status = statusSpinner.selectedItemPosition

        //Verifica que los campos obligatorios no estén vaíos
        if (name.isEmpty() || type.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        //Ejecuta la inserción en la base de datos
        lifecycleScope.launch {

            //Inserta el anime según el estado seleccionado
            try {
                when (status) {
                    0 -> db.animeDao().insertPendientes(Pendientes(name = name, type = type))
                    1 -> db.animeDao().insertViendo(Viendo(name = name, type = type))
                    2 -> db.animeDao().insertVisto(Visto(name = name, type = type))
                }

                //Notifica que se ha guardado
                Toast.makeText(this@AddAnimeActivity, "Anime guardado exitosamente", Toast.LENGTH_SHORT).show()
                finish()
            } catch (e: Exception) {

                //notifica que ha habido un error durante el guardado
                Toast.makeText(this@AddAnimeActivity, "Error al guardar: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
