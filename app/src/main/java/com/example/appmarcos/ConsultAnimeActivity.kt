package com.example.appmarcos

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmarcos.adapter.AnimeAdapter
import com.example.appmarcos.db.AnimeDatabase
import com.example.appmarcos.db.Anime
import kotlinx.coroutines.launch

class ConsultAnimeActivity : AppCompatActivity() {

    private lateinit var statusSpinner: Spinner
    private lateinit var recyclerView: RecyclerView
    private lateinit var animeAdapter: AnimeAdapter
    private lateinit var db: AnimeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consult_anime)

        // Activa la flecha de retroceso en el ActionBar y establece el título de la pantalla
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Consultar Anime"

        // Inicializar base de datos
        db = AnimeDatabase.getDatabase(this)

        // Inicializar vistas
        statusSpinner = findViewById(R.id.spinnerStatusConsult)
        recyclerView = findViewById(R.id.rvAnimes)

        // Opciones disponibles para filtrar los animes
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

        // Configurar RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Listener para cambios en el spinner
        statusSpinner.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {

            // Se ejecuta cuando el usuario selecciona una opción
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                loadAnimes(position)
            }

            // Se ejecuta cuando no hay selección
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}
        }

        // Cargar animes iniciales (pendientes)
        loadAnimes(0)
    }

    // Maneja las acciones del menú superior
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // Carga los animes desde la base de datos
    private fun loadAnimes(status: Int) {

        // Ejecuta la operación
        lifecycleScope.launch {
            try {
                val dao = db.animeDao()

                // Obtiene la lista de animes según el estado:
                // 0 = pendientes, 1 = viendo, 2 = vistos
                val animeList: List<Anime> = when (status) {
                    0 -> dao.getAllPendientes().map { Anime(it.id, it.name, it.type) }
                    1 -> dao.getAllViendo().map { Anime(it.id, it.name, it.type) }
                    2 -> dao.getAllVisto().map { Anime(it.id, it.name, it.type) }
                    else -> emptyList()
                }

                // Asigna la lista al adaptador del RecyclerView
                animeAdapter = AnimeAdapter(animeList)
                recyclerView.adapter = animeAdapter

                // Si no hay animes en la categoría seleccionada, muestra un mensaje
                if (animeList.isEmpty()) {
                    Toast.makeText(
                        this@ConsultAnimeActivity,
                        "No hay animes en esta categoría",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                // Muestra un mensaje si ocurre un error al cargar los datos
                Toast.makeText(
                    this@ConsultAnimeActivity,
                    "Error al cargar animes: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
