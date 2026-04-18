package com.example.appmarcos.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "visto")
data class Visto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String
)

@Entity(tableName = "viendo")
data class Viendo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String
)

@Entity(tableName = "pendientes")
data class Pendientes(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String
)
