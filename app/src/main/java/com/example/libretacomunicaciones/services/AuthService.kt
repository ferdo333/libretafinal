package com.example.libretacomunicaciones.services

import com.example.libretacomunicaciones.Models.LoginPayloadDTO
import com.example.libretacomunicaciones.Models.LoginResponseDTO
import com.example.libretacomunicaciones.Models.RegisterPayloadDTO


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/auth/local")
    fun login(@Body payload: LoginPayloadDTO): Call<LoginResponseDTO>

    @POST("/api/auth/local/register")
    fun register(@Body payload: RegisterPayloadDTO): Call<Void>
}