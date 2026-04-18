package com.example.appmarcos.adapter

import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import com.example.appmarcos.db.Anime
import kotlin.test.assertEquals

class AnimeAdapterTest {

    private lateinit var adapter: AnimeAdapter
    private val testAnimeList = listOf(
        Anime(id = 1, name = "Attack on Titan", type = "TV"),
        Anime(id = 2, name = "Death Note", type = "TV"),
        Anime(id = 3, name = "Demon Slayer", type = "Movie"),
        Anime(id = 4, name = "One Piece", type = "TV"),
        Anime(id = 5, name = "Naruto", type = "TV")
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        adapter = AnimeAdapter(testAnimeList)
    }

    @Test
    fun testAdapterItemCount() {
        // Arrange
        val expectedCount = 5

        // Act
        val actualCount = adapter.itemCount

        // Assert
        assertEquals(expectedCount, actualCount)
    }

    @Test
    fun testAdapterEmptyList() {
        // Arrange
        val emptyList = emptyList<Anime>()

        // Act
        val emptyAdapter = AnimeAdapter(emptyList)

        // Assert
        assertEquals(0, emptyAdapter.itemCount)
    }

    @Test
    fun testAdapterSingleItem() {
        // Arrange
        val singleItemList = listOf(Anime(id = 1, name = "Test Anime", type = "TV"))

        // Act
        val singleAdapter = AnimeAdapter(singleItemList)

        // Assert
        assertEquals(1, singleAdapter.itemCount)
    }

    @Test
    fun testAdapterWithLargeList() {
        // Arrange
        val largeList = (1..100).map {
            Anime(id = it, name = "Anime $it", type = "TV")
        }

        // Act
        val largeAdapter = AnimeAdapter(largeList)

        // Assert
        assertEquals(100, largeAdapter.itemCount)
    }

    @Test
    fun testAnimePropertiesInList() {
        // Arrange
        val animeList = testAnimeList

        // Act
        val firstAnime = animeList[0]

        // Assert
        assertEquals(1, firstAnime.id)
        assertEquals("Attack on Titan", firstAnime.name)
        assertEquals("TV", firstAnime.type)
    }

    @Test
    fun testMultipleAnimeProperties() {
        // Arrange
        val animeList = testAnimeList

        // Act
        val names = animeList.map { it.name }
        val types = animeList.map { it.type }

        // Assert
        assertEquals(5, names.size)
        assertEquals(5, types.size)
        assertEquals(listOf("Attack on Titan", "Death Note", "Demon Slayer", "One Piece", "Naruto"), names)
    }

    @Test
    fun testAnimeTypeVariety() {
        // Arrange
        val animeList = testAnimeList

        // Act
        val distinctTypes = animeList.map { it.type }.distinct()

        // Assert
        assertEquals(2, distinctTypes.size)
        assertTrue(distinctTypes.contains("TV"))
        assertTrue(distinctTypes.contains("Movie"))
    }

    @Test
    fun testAnimeIdsUnique() {
        // Arrange
        val animeList = testAnimeList

        // Act
        val ids = animeList.map { it.id }
        val uniqueIds = ids.distinct()

        // Assert
        assertEquals(ids.size, uniqueIds.size)
    }
}
