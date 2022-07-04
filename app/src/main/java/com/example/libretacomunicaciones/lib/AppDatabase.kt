package com.example.libretacomunicaciones.lib

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.libretacomunicaciones.Models.Enviada
import com.example.libretacomunicaciones.Models.EnviadaEntity
import com.example.libretacomunicaciones.Models.RecibidaEntity
import com.example.libretacomunicaciones.Models.UserEntity
import com.example.libretacomunicaciones.dao.EnviadaDAO
import com.example.libretacomunicaciones.dao.RecibidasDAO
import com.example.libretacomunicaciones.dao.UserDAO
import com.example.libretacomunicaciones.utils.Converters

//creamos la base de datos
@Database(entities = [UserEntity::class, RecibidaEntity::class, EnviadaEntity::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
    abstract fun recibidasDao(): RecibidasDAO
    abstract fun enviadasDao(): EnviadaDAO
    companion object{
        const val DATABASE_NAME = "libreta-app"
    }
}