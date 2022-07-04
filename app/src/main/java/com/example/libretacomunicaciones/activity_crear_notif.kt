package com.example.libretacomunicaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.example.libretacomunicaciones.Models.Enviada
import com.example.libretacomunicaciones.controllers.AuthController
import com.example.libretacomunicaciones.controllers.EnviadaController
import com.google.android.material.textfield.TextInputLayout

class activity_crear_notif : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_notif)

        val authController = AuthController(this)
        val enviadaController = EnviadaController(this, authController.getSessionUserId())

        val btnToBack = findViewById<Button>(R.id.activity_crear_notif_btn_to_back)
        val btnCrear = findViewById<Button>(R.id.activity_crear_notif_btn_enviar_mensaje)

        val spnAsignatura= findViewById<Spinner>(R.id.activity_crear_notif_spinner_ramos)

        val enviada = intent.getSerializableExtra("enviada") as Enviada

        val tvTittle = findViewById<TextView>(R.id.activity_crear_notif_tv_title)
        val tilAsunto = findViewById<TextInputLayout>(R.id.activity_crear_notif_til_asunto)
        val tilFecha = findViewById<TextInputLayout>(R.id.activity_crear_notif_til_fecha)
        val tilEstado = findViewById<TextInputLayout>(R.id.activity_crear_notif_til_estado)

        val tilMensaje = findViewById<TextInputLayout>(R.id.activity_crear_notif_til_message)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.ramo_array,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnAsignatura.adapter = adapter

        if (enviada != null) {
            tvTittle.text = "Editando tarea ${enviada.id}"
            tilAsunto.editText?.text = Editable.Factory.getInstance().newEditable(enviada.asunto)
            tilFecha.editText?.text = Editable.Factory.getInstance().newEditable(enviada.fecha)
            spnAsignatura.selectedItem.equals(Editable.Factory.getInstance().newEditable(enviada.asignatura))
            tilMensaje.editText?.text = Editable.Factory.getInstance().newEditable(enviada.description)
            tilEstado.editText?.text = Editable.Factory.getInstance().newEditable(enviada.estado)

        }

        btnCrear.setOnClickListener{
            if (enviada == null) {
                val newEnviada = Enviada(
                    id = null,
                    asunto = tilAsunto.editText?.text.toString(),
                    fecha = tilFecha.editText?.text.toString(),
                    asignatura= spnAsignatura.selectedItem.toString(),
                    description = tilMensaje.editText?.text.toString(),
                    estado = tilEstado.editText?.text.toString()

                )
                enviadaController.create(newEnviada)
            } else {
                val updatedEnviada = Enviada(
                    id = enviada.id,
                    asunto = tilAsunto.editText?.text.toString(),
                    fecha = tilFecha.editText?.text.toString(),
                    asignatura= spnAsignatura.selectedItem.toString(),
                    description = tilMensaje.editText?.text.toString(),
                    estado = tilEstado.editText?.text.toString()
                )
                enviadaController.update(updatedEnviada)
            }


        }


        btnToBack.setOnClickListener {
            val intent = Intent(this, activity_indx::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)


        }



    }





}


