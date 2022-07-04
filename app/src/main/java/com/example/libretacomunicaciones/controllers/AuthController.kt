package com.example.libretacomunicaciones.controllers


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.room.Room
import com.example.libretacomunicaciones.LoginActivity
import com.example.libretacomunicaciones.Models.*
import com.example.libretacomunicaciones.activity_indx
import com.example.libretacomunicaciones.lib.AppDatabase
import com.example.libretacomunicaciones.lib.BCrypt
import com.example.libretacomunicaciones.lib.RetrofitClient
import com.example.libretacomunicaciones.services.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat


class AuthController constructor(ctx: Context) {
    private val sharedPref = (ctx as Activity).getPreferences(Context.MODE_PRIVATE)
    private val INCORRECT_CREDENTIALS = "Credenciales incorrectas"
    private val retrofit = RetrofitClient.getRetrofitInstance()
    private val authService = retrofit.create(AuthService::class.java)
    private val ctx = ctx
    private val dao = Room.databaseBuilder(
        ctx,
        AppDatabase::class.java, "database-name"

    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
        .userDao()




    fun login(email: String, password: String) {
        val loginPayload = LoginPayloadDTO(email, password)
        val call = authService.login(loginPayload)

        call.enqueue(object : Callback<LoginResponseDTO> {
            override fun onFailure(call: Call<LoginResponseDTO>, t: Throwable) {
                Toast.makeText(ctx, "Error de conexión", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<LoginResponseDTO>,
                response: Response<LoginResponseDTO>
            ) {
                if (response.code() != 200) {
                    Toast.makeText(ctx, INCORRECT_CREDENTIALS, Toast.LENGTH_SHORT).show()
                } else {
                    val bodyResponse = response.body()
                    Toast.makeText(ctx, "Bienvenido ${bodyResponse?.user?.id}", Toast.LENGTH_SHORT).show()
                    val sharedEdit = sharedPref.edit()
                    sharedEdit.putLong("user_id", bodyResponse?.user?.id!!)
                    sharedEdit.putString("user_jwt", bodyResponse?.jwt!!)
                    sharedEdit.commit()

                    val intent = Intent(ctx, activity_indx::class.java)
                    ctx.startActivity(intent)
                    (ctx as Activity).finish()
                }
            }
        })
    }


    fun getSessionUserId(): Long {

        return sharedPref.getLong("user_id", -1)

    }



        fun register(user: User) {
            val registerPayload = RegisterPayloadDTO(
                user.id ,
                user.nombre,
                user.ap_alumno,
                user.email,
                user.password,
                user.rpassword,
                user.birth,
                user.curso
            )
            val call = authService.register(registerPayload)

            call.enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(ctx, "Error de conexión", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>
                ) {
                    if (response.code() != 200) {
                        Toast.makeText(ctx, "Cuenta existente", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(ctx, "Cuenta registrada", Toast.LENGTH_SHORT).show()
                        val intent = Intent(ctx, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        ctx.startActivity(intent)
                    }
                }
            })
        }}













