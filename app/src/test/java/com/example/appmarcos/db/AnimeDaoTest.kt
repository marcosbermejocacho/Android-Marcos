package com.example.appmarcos.db

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AnimeDaoTest {

    @Mock
    private lateinit var animeDao: AnimeDao

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testInsertVisto() = runBlocking {
        // Arrange
        val anime = Visto(id = 1, name = "Attack on Titan", type = "TV")

        // Act
        animeDao.insertVisto(anime)

        // Assert
        val retrievedAnime = animeDao.getVistoByName("Attack on Titan")
        assertEquals(anime.name, retrievedAnime?.name)
    }

    @Test
    fun testInsertViendo() = runBlocking {
        // Arrange
        val anime = Viendo(id = 1, name = "Death Note", type = "TV")

        // Act
        animeDao.insertViendo(anime)

        // Assert
        val retrievedAnime = animeDao.getViendoByName("Death Note")
        assertEquals(anime.name, retrievedAnime?.name)
    }

    @Test
    fun testInsertPendientes() = runBlocking {
        // Arrange
        val anime = Pendientes(id = 1, name = "One Piece", type = "TV")

        // Act
        animeDao.insertPendientes(anime)

        // Assert
        val retrievedAnime = animeDao.getPendientesByName("One Piece")
        assertEquals(anime.name, retrievedAnime?.name)
    }

    @Test
    fun testDeleteVisto() = runBlocking {
        // Arrange
        val anime = Visto(id = 1, name = "Naruto", type = "TV")
        animeDao.insertVisto(anime)

        // Act
        val deletedCount = animeDao.deleteVistoByName("Naruto")

        // Assert
        assertEquals(1, deletedCount)
        assertNull(animeDao.getVistoByName("Naruto"))
    }

    @Test
    fun testDeleteViendo() = runBlocking {
        // Arrange
        val anime = Viendo(id = 1, name = "Bleach", type = "TV")
        animeDao.insertViendo(anime)

        // Act
        val deletedCount = animeDao.deleteViendoByName("Bleach")

        // Assert
        assertEquals(1, deletedCount)
        assertNull(animeDao.getViendoByName("Bleach"))
    }

    @Test
    fun testDeletePendientes() = runBlocking {
        // Arrange
        val anime = Pendientes(id = 1, name = "Demon Slayer", type = "TV")
        animeDao.insertPendientes(anime)

        // Act
        val deletedCount = animeDao.deletePendientesByName("Demon Slayer")

        // Assert
        assertEquals(1, deletedCount)
        assertNull(animeDao.getPendientesByName("Demon Slayer"))
    }

    @Test
    fun testGetAnimeByNameNotFound() = runBlocking {
        // Act
        val anime = animeDao.getVistoByName("NonExistingAnime")

        // Assert
        assertNull(anime)
    }

    @Test
    fun testGetAllVisto() = runBlocking {
        // Arrange
        val anime1 = Visto(id = 1, name = "Anime1", type = "TV")
        val anime2 = Visto(id = 2, name = "Anime2", type = "Movie")
        animeDao.insertVisto(anime1)
        animeDao.insertVisto(anime2)

        // Act
        val animes = animeDao.getAllVisto()

        // Assert
        assertEquals(2, animes.size)
    }

    @Test
    fun testGetAllViendo() = runBlocking {
        // Arrange
        val anime1 = Viendo(id = 1, name = "Anime1", type = "TV")
        val anime2 = Viendo(id = 2, name = "Anime2", type = "OVA")
        animeDao.insertViendo(anime1)
        animeDao.insertViendo(anime2)

        // Act
        val animes = animeDao.getAllViendo()

        // Assert
        assertEquals(2, animes.size)
    }

    @Test
    fun testGetAllPendientes() = runBlocking {
        // Arrange
        val anime1 = Pendientes(id = 1, name = "Anime1", type = "TV")
        val anime2 = Pendientes(id = 2, name = "Anime2", type = "Movie")
        animeDao.insertPendientes(anime1)
        animeDao.insertPendientes(anime2)

        // Act
        val animes = animeDao.getAllPendientes()

        // Assert
        assertEquals(2, animes.size)
    }
}
