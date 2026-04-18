package com.example.appmarcos.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@RunWith(AndroidJUnit4::class)
class AnimeDatabaseTest {

    private lateinit var database: AnimeDatabase
    private lateinit var animeDao: AnimeDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AnimeDatabase::class.java
        ).build()
        animeDao = database.animeDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testDatabaseCreation() {
        // Assert
        assertNotNull(animeDao)
    }

    @Test
    fun testInsertAndRetrieveVisto() = runBlocking {
        // Arrange
        val anime = Visto(id = 1, name = "Test Anime", type = "TV")

        // Act
        animeDao.insertVisto(anime)
        val retrieved = animeDao.getVistoByName("Test Anime")

        // Assert
        assertNotNull(retrieved)
        assertEquals("Test Anime", retrieved?.name)
        assertEquals("TV", retrieved?.type)
    }

    @Test
    fun testInsertAndRetrieveViendo() = runBlocking {
        // Arrange
        val anime = Viendo(id = 1, name = "Watching Anime", type = "Movie")

        // Act
        animeDao.insertViendo(anime)
        val retrieved = animeDao.getViendoByName("Watching Anime")

        // Assert
        assertNotNull(retrieved)
        assertEquals("Watching Anime", retrieved?.name)
        assertEquals("Movie", retrieved?.type)
    }

    @Test
    fun testInsertAndRetrievePendientes() = runBlocking {
        // Arrange
        val anime = Pendientes(id = 1, name = "Pending Anime", type = "OVA")

        // Act
        animeDao.insertPendientes(anime)
        val retrieved = animeDao.getPendientesByName("Pending Anime")

        // Assert
        assertNotNull(retrieved)
        assertEquals("Pending Anime", retrieved?.name)
        assertEquals("OVA", retrieved?.type)
    }

    @Test
    fun testMultipleInsertsAndRetrieval() = runBlocking {
        // Arrange
        val visto1 = Visto(id = 1, name = "Anime1", type = "TV")
        val visto2 = Visto(id = 2, name = "Anime2", type = "Movie")
        val visto3 = Visto(id = 3, name = "Anime3", type = "OVA")

        // Act
        animeDao.insertVisto(visto1)
        animeDao.insertVisto(visto2)
        animeDao.insertVisto(visto3)
        val allAnimes = animeDao.getAllVisto()

        // Assert
        assertEquals(3, allAnimes.size)
    }

    @Test
    fun testDeleteAnimeFromVisto() = runBlocking {
        // Arrange
        val anime = Visto(id = 1, name = "Delete Me", type = "TV")
        animeDao.insertVisto(anime)

        // Act
        val deletedCount = animeDao.deleteVistoByName("Delete Me")

        // Assert
        assertEquals(1, deletedCount)
        assertNull(animeDao.getVistoByName("Delete Me"))
    }

    @Test
    fun testDeleteAnimeFromViendo() = runBlocking {
        // Arrange
        val anime = Viendo(id = 1, name = "Remove This", type = "TV")
        animeDao.insertViendo(anime)

        // Act
        val deletedCount = animeDao.deleteViendoByName("Remove This")

        // Assert
        assertEquals(1, deletedCount)
        assertNull(animeDao.getViendoByName("Remove This"))
    }

    @Test
    fun testDeleteAnimeFromPendientes() = runBlocking {
        // Arrange
        val anime = Pendientes(id = 1, name = "Skip This", type = "TV")
        animeDao.insertPendientes(anime)

        // Act
        val deletedCount = animeDao.deletePendientesByName("Skip This")

        // Assert
        assertEquals(1, deletedCount)
        assertNull(animeDao.getPendientesByName("Skip This"))
    }

    @Test
    fun testTablesAreIndependent() = runBlocking {
        // Arrange
        val visto = Visto(id = 1, name = "Visto Anime", type = "TV")
        val viendo = Viendo(id = 1, name = "Viendo Anime", type = "TV")
        val pendientes = Pendientes(id = 1, name = "Pendientes Anime", type = "TV")

        // Act
        animeDao.insertVisto(visto)
        animeDao.insertViendo(viendo)
        animeDao.insertPendientes(pendientes)

        val vistoList = animeDao.getAllVisto()
        val viendoList = animeDao.getAllViendo()
        val pendientesList = animeDao.getAllPendientes()

        // Assert
        assertEquals(1, vistoList.size)
        assertEquals(1, viendoList.size)
        assertEquals(1, pendientesList.size)
    }
}
