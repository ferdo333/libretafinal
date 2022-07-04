package com.example.libretacomunicaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.widget.ListView
import com.example.libretacomunicaciones.controllers.AuthController
import com.example.libretacomunicaciones.controllers.EnviadaController

import com.example.libretacomunicaciones.ui.EnviadaAdapter

class enviadasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enviadas)

        val authController = AuthController(this)


    val lvEnviadas = findViewById<ListView>(R.id.activity_lv_enviadas)
    val allEnviadas= EnviadaController(this, authController.getSessionUserId()).getAll()
    val adapter = EnviadaAdapter(this, allEnviadas)


    lvEnviadas.adapter = adapter
        lvEnviadas.setOnItemClickListener{ AdapterView,view,i,l ->
        run {
            val enviada = allEnviadas[i]
            val intent = Intent(view.context, EviadasItemActivity::class.java)
            intent.putExtra("enviada", enviada)
            view.context.startActivity(intent)


        }
    }
}}



