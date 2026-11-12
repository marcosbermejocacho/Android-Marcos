package com.example.appmarcos.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnimeDao {
    // Operaciones Visto
    @Insert
    suspend fun insertVisto(anime: Visto)

    @Query("DELETE FROM visto WHERE name = :name")
    suspend fun deleteVisto(name: String): Int

    @Query("SELECT * FROM visto WHERE name = :name LIMIT 1")
    suspend fun getVistoByName(name: String): Visto?

    @Query("SELECT * FROM visto")
    suspend fun getAllVisto(): List<Visto>

    // Operaciones Viendo
    @Insert
    suspend fun insertViendo(anime: Viendo)

    @Query("DELETE FROM viendo WHERE name = :name")
    suspend fun deleteViendo(name: String): Int

    @Query("SELECT * FROM viendo WHERE name = :name LIMIT 1")
    suspend fun getViendoByName(name: String): Viendo?

    @Query("SELECT * FROM viendo")
    suspend fun getAllViendo(): List<Viendo>

    // Operaciones Pendientes
    @Insert
    suspend fun insertPendientes(anime: Pendientes)

    @Query("DELETE FROM pendientes WHERE name = :name")
    suspend fun deletePendientes(name: String): Int

    @Query("SELECT * FROM pendientes WHERE name = :name LIMIT 1")
    suspend fun getPendientesByName(name: String): Pendientes?

    @Query("SELECT * FROM pendientes")
    suspend fun getAllPendientes(): List<Pendientes>
}
