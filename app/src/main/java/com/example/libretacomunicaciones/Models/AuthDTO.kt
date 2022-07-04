package com.example.libretacomunicaciones.Models

import java.util.*

data class LoginPayloadDTO (
    val email: String,
    val password: String
)

data class UserDTO (
    val id: Long
)

data class LoginResponseDTO (
    val jwt: String,
    val user: UserDTO
)

data class RegisterPayloadDTO (
    val id: Long?,
    val nombre: String,
    val ap_alumno: String,
    val email: String,
    val password: String,
    val rpassword: String,
    val birth: Date,
    val curso: String

)