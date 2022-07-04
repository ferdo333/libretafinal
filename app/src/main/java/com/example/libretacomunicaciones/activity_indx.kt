package com.example.libretacomunicaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.example.libretacomunicaciones.controllers.AuthController
import com.example.libretacomunicaciones.controllers.EnviadaController
import com.example.libretacomunicaciones.ui.EnviadaAdapter

class activity_indx : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indx)



        val btnToEnd = findViewById<Button>(R.id.activity_index_btn_to_End)
        val btnToEditPass = findViewById<Button>(R.id.activity_index_btn_to_EditPassword)
        val btntoCrearNotif = findViewById<Button>(R.id.activity_index_btn_to_crear_notif)
        val btntoRecibidas = findViewById<Button>(R.id.activity_index_btn_to_recibidas)
        val btntoEnviadas = findViewById<Button>(R.id.activity_index_btn_to_enviadas)




        btnToEnd.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)


        }

        btnToEditPass.setOnClickListener {
            val intent = Intent(this, PassEditActivity::class.java)
            startActivity(intent)


        }

        btntoCrearNotif.setOnClickListener {
            val intent = Intent(this, NotifFormActivity::class.java)
            startActivity(intent)


        }

        btntoRecibidas.setOnClickListener {
            val intent = Intent(this, activity_recibidas::class.java)
            startActivity(intent)


        }

        btntoEnviadas.setOnClickListener {
            val intent = Intent(this, enviadasActivity::class.java)
            startActivity(intent)


        }
    }
}