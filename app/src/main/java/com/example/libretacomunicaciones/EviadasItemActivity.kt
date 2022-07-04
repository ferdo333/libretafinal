package com.example.libretacomunicaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.example.libretacomunicaciones.Models.Enviada
import com.example.libretacomunicaciones.controllers.AuthController
import com.example.libretacomunicaciones.controllers.EnviadaController
import com.google.android.material.textfield.TextInputLayout


class EviadasItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eviadas_item)

        val authController = AuthController(this)
        val enviadaController = EnviadaController(this, authController.getSessionUserId())


        val btnUpdate = findViewById<TextView>(R.id.activity_enviada_item_btn_update)
        val btnDelete = findViewById<TextView>(R.id.activity_enviada_item_btn_delete)

        //val recibida = intent.getSerializableExtra("recibida")
        val enviada = intent.getSerializableExtra("enviada") as Enviada

        val tvTitle = findViewById<TextView>(R.id.activity_enviadas_item_tv_title)
        //val tvDescription = findViewById<TextView>(R.id.activity_enviadas_item_til_descripcion)
        tvTitle.text = "| Asunto: " + enviada.asunto + "| Fecha: " + enviada.fecha + "| Asignatura: " + enviada.asignatura + "" +
                "| Estado :" + enviada.estado + "| Descripción: " + enviada.description


        btnUpdate.setOnClickListener {

            val intent = Intent(this, NotifFormActivity::class.java)

            intent.putExtra("enviada", enviada)

            startActivity(intent)

        }



        btnDelete.setOnClickListener {

            enviadaController.delete(enviada.id!!)

        }





    }
}