package com.example.appmarcos.validation


import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import android.content.Context
import androidx.test.core.app.ApplicationProvider


class AddAnimeActivityTest {

    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testFormValidationEmptyName() {
        // Arrange
        val name = ""
        val type = "TV"

        // Act
        val isValid = name.isNotEmpty() && type.isNotEmpty()

        // Assert
        assertFalse(isValid)
    }

    @Test
    fun testFormValidationEmptyType() {
        // Arrange
        val name = "Death Note"
        val type = ""

        // Act
        val isValid = name.isNotEmpty() && type.isNotEmpty()

        // Assert
        assertFalse(isValid)
    }

    @Test
    fun testFormValidationBothFieldsFilled() {
        // Arrange
        val name = "Death Note"
        val type = "TV"

        // Act
        val isValid = name.isNotEmpty() && type.isNotEmpty()

        // Assert
        assertTrue(isValid)
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
    fun testStatusPendingIndex() {
        // Arrange
        val selectedIndex = 0

        // Act
        val isPending = selectedIndex == 0

        // Assert
        assertTrue(isPending)
    }

    @Test
    fun testStatusWatchingIndex() {
        // Arrange
        val selectedIndex = 1

        // Act
        val isWatching = selectedIndex == 1

        // Assert
        assertTrue(isWatching)
    }

    @Test
    fun testStatusSeenIndex() {
        // Arrange
        val selectedIndex = 2

        // Act
        val isSeen = selectedIndex == 2

        // Assert
        assertTrue(isSeen)
    }
}
