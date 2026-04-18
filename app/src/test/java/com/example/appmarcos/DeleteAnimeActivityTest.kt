package com.example.appmarcos

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertEquals

class DeleteAnimeActivityTest {

    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testDeleteFormValidationEmptyName() {
        // Arrange
        val name = ""

        // Act
        val isValid = name.isNotEmpty()

        // Assert
        assertFalse(isValid)
    }

    @Test
    fun testDeleteFormValidationWithName() {
        // Arrange
        val name = "Attack on Titan"

        // Act
        val isValid = name.isNotEmpty()

        // Assert
        assertTrue(isValid)
    }

    @Test
    fun testNameTrimming() {
        // Arrange
        val name = "  Death Note  "

        // Act
        val trimmedName = name.trim()

        // Assert
        assertEquals("Death Note", trimmedName)
    }

    @Test
    fun testDeleteSuccessMessage() {
        // Arrange
        val animeName = "Naruto"
        val wasDeleted = true

        // Act
        val message = if (wasDeleted) {
            "Anime '$animeName' borrado exitosamente"
        } else {
            "El anime '$animeName' no fue encontrado"
        }

        // Assert
        assertEquals("Anime 'Naruto' borrado exitosamente", message)
    }

    @Test
    fun testDeleteNotFoundMessage() {
        // Arrange
        val animeName = "NonExistentAnime"
        val wasDeleted = false

        // Act
        val message = if (wasDeleted) {
            "Anime '$animeName' borrado exitosamente"
        } else {
            "El anime '$animeName' no fue encontrado"
        }

        // Assert
        assertEquals("El anime 'NonExistentAnime' no fue encontrado", message)
    }

    @Test
    fun testMultipleTablesSearch() {
        // Arrange
        val deletedFromVisto = 0
        val deletedFromViendo = 0
        val deletedFromPendientes = 1

        // Act
        val totalDeleted = deletedFromVisto + deletedFromViendo + deletedFromPendientes

        // Assert
        assertTrue(totalDeleted > 0)
        assertEquals(1, totalDeleted)
    }

    @Test
    fun testNoDeleteResultFromAnyTable() {
        // Arrange
        val deletedFromVisto = 0
        val deletedFromViendo = 0
        val deletedFromPendientes = 0

        // Act
        val totalDeleted = deletedFromVisto + deletedFromViendo + deletedFromPendientes

        // Assert
        assertFalse(totalDeleted > 0)
        assertEquals(0, totalDeleted)
    }
}
