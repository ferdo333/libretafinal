package com.example.libretacomunicaciones.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.libretacomunicaciones.Models.EnviadaEntity
import com.example.libretacomunicaciones.Models.RecibidaEntity

@Dao
interface EnviadaDAO {


    @Query("SELECT * FROM enviadas WHERE user_id = :userId")
    fun findAll(userId: Long): List<EnviadaEntity>

    @Query("SELECT * FROM enviadas WHERE id = :id")
    fun findById(id: Long): EnviadaEntity?


    @Insert
    fun insert(enviada: EnviadaEntity)

    @Update
    fun update(enviada: EnviadaEntity)

    @Query("DELETE  FROM enviadas WHERE id = :id")
    fun delete(id: Long)


}