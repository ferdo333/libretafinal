package com.example.libretacomunicaciones.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.room.Room
import com.example.libretacomunicaciones.Models.Enviada
import com.example.libretacomunicaciones.Models.EnviadaEntity
import com.example.libretacomunicaciones.enviadasActivity


import com.example.libretacomunicaciones.lib.AppDatabase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EnviadaController  constructor(ctx: Context, userId: Long = 0){

    private val ctx = ctx
    private val sharedPref = ctx.getSharedPreferences("LIBRETA_APP", Context.MODE_PRIVATE)
    private val userId = userId
    private val dao = Room.databaseBuilder(
        ctx,
        AppDatabase::class.java, "database-name"

    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
        .enviadasDao()

    fun getAll ():List<Enviada>{
        val enviadaEntity = dao.findAll(userId)
        val enviadas = ArrayList<Enviada>()


        enviadaEntity.forEach{enviada -> enviadas.add(
            Enviada(
            id = enviada.id,
            asunto=enviada.asunto,
            fecha= enviada.fecha,
            asignatura=enviada.asignatura,
            description=enviada.description,
            estado=enviada.estado,
            done=enviada.done
        )
        )}
        return enviadas
    }

    fun getById(id: Long): Enviada? {
        val entity = dao.findById(id) ?: return null

        return Enviada(
            id = entity?.id,
            asunto=entity.asunto,
            fecha= entity.fecha,
            asignatura=entity.asignatura,
            description=entity.description,
            estado=entity?.estado,
            done=entity.done
        )
    }

    fun create (enviada:  Enviada) {
        val entity = EnviadaEntity(
            id = null,
            asunto=enviada.asunto,
            fecha= enviada.fecha,
            asignatura=enviada.asignatura,
            description=enviada.description,
            estado=enviada.estado,
            done=enviada.done,
            userId = sharedPref.getLong("user_id", userId)
        )
        dao.insert(entity)

        val intent = Intent(ctx, enviadasActivity::class.java)
        ctx.startActivity(intent)
        (this.ctx as Activity).finish()
    }
    fun update (enviada: Enviada) {
        val entity = EnviadaEntity(
            id = enviada.id,
            asunto = enviada.asunto,
            fecha= enviada.fecha,
            asignatura=enviada.asignatura,
            description=enviada.description,
            estado=enviada.estado,
            done=enviada.done,
            userId = sharedPref.getLong("user_id", userId)
        )
        dao.update(entity)

        val intent = Intent(ctx, enviadasActivity::class.java)
        ctx.startActivity(intent)
        (this.ctx as Activity).finish()
    }

    fun delete (id:Long) {

        dao.delete(id)



        val intent = Intent(ctx, enviadasActivity::class.java)

        ctx.startActivity(intent)

        (this.ctx as Activity).finish()

    }





}





