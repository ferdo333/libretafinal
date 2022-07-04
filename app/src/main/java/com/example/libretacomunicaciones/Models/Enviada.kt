package com.example.libretacomunicaciones.Models
import java.io.Serializable

data class Enviada (

    val id: Long?,
    val asunto: String,
    val fecha: String,
    val asignatura: String,
    val description: String,
    val estado: String?,
    val done: Boolean=false



    ):Serializable

