package com.example.appmarcos.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import kotlinx.coroutines.runBlocking


@RunWith(AndroidJUnit4::class)
class AnimeDatabaseTest {

    private lateinit var db: AnimeDatabase
    private lateinit var dao: AnimeDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AnimeDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = db.animeDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun testInsertVisto() {
        val anime = Visto(
            name = "Death Note",
            type = "TV"
        )

        var result: List<Visto> = emptyList()

        runBlocking {
            dao.insertVisto(anime)
            result = dao.getAllVisto()
        }

        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals("Death Note", result[0].name)
        assertEquals("TV", result[0].type)
    }

    @Test
    fun testDeleteVisto() {
        val anime = Visto(
            name = "Death Note",
            type = "TV"
        )

        runBlocking {
            // Insertamos primero
            dao.insertVisto(anime)

            // Comprobamos que se ha insertado
            var result = dao.getAllVisto()
            assertEquals(1, result.size)

            // Eliminamos
            dao.deleteVisto(result[0].name)

            // Volvemos a consultar
            result = dao.getAllVisto()

            // Comprobamos que está vacío
            assertEquals(0, result.size)
        }
    }
}

