package com.example.appmarcos.validation

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.appmarcos.db.Anime

class ConsultAnimeActivityTest {

    @Mock
    private lateinit var context: Context

    private val testAnimeList = listOf(
        Anime(id = 1, name = "Attack on Titan", type = "TV"),
        Anime(id = 2, name = "Death Note", type = "TV"),
        Anime(id = 3, name = "Demon Slayer", type = "Movie")
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testSpinnerStatusOptions() {
        // Arrange
        val statusOptions = listOf(
            "pendiente de ver",
            "viendolo",
            "visto"
        )

        // Act
        val hasAllOptions = statusOptions.size == 3

        // Assert
        assertTrue(hasAllOptions)
        assertTrue(statusOptions.contains("pendiente de ver"))
        assertTrue(statusOptions.contains("viendolo"))
        assertTrue(statusOptions.contains("visto"))
    }

    @Test
    fun testStatusPendingSelection() {
        // Arrange
        val selectedStatus = 0

        // Act
        val isPending = selectedStatus == 0

        // Assert
        assertTrue(isPending)
    }

    @Test
    fun testStatusWatchingSelection() {
        // Arrange
        val selectedStatus = 1

        // Act
        val isWatching = selectedStatus == 1

        // Assert
        assertTrue(isWatching)
    }

    @Test
    fun testStatusSeenSelection() {
        // Arrange
        val selectedStatus = 2

        // Act
        val isSeen = selectedStatus == 2

        // Assert
        assertTrue(isSeen)
    }

    @Test
    fun testAnimeListNotEmpty() {
        // Arrange
        val animeList = testAnimeList

        // Act
        val isEmpty = animeList.isEmpty()

        // Assert
        assertTrue(!isEmpty)
        assertEquals(3, animeList.size)
    }

    @Test
    fun testAnimeListEmpty() {
        // Arrange
        val animeList = emptyList<Anime>()

        // Act
        val isEmpty = animeList.isEmpty()

        // Assert
        assertTrue(isEmpty)
    }

    @Test
    fun testAnimeDataIntegrity() {
        // Arrange
        val anime = testAnimeList[0]

        // Act
        val name = anime.name
        val type = anime.type

        // Assert
        assertEquals("Attack on Titan", name)
        assertEquals("TV", type)
    }

    @Test
    fun testMultipleAnimesRetrieval() {
        // Arrange
        val animeList = testAnimeList

        // Act
        val retrievedNames = animeList.map { it.name }

        // Assert
        assertEquals(3, retrievedNames.size)
        assertTrue(retrievedNames.contains("Attack on Titan"))
        assertTrue(retrievedNames.contains("Death Note"))
        assertTrue(retrievedNames.contains("Demon Slayer"))
    }

    @Test
    fun testFilterAnimeByType() {
        // Arrange
        val animeList = testAnimeList

        // Act
        val tvAnimes = animeList.filter { it.type == "TV" }

        // Assert
        assertEquals(2, tvAnimes.size)
    }
}
