package com.example.libretacomunicaciones

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
import java.text.SimpleDateFormat
import java.util.*

class NotifFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notif_form)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        val authController = AuthController(this)
        val enviadaController = EnviadaController(this, authController.getSessionUserId())

        val enviada = intent.getSerializableExtra("enviada") as Enviada?

        val tvTitle = findViewById<TextView>(R.id.activity_notif_form_tv_title)
        val tilAsunto = findViewById<TextInputLayout>(R.id.activity_notif_form_til_asunto)
        val tilDescription = findViewById<TextInputLayout>(R.id.activity_notif_form_til_description)
       // val tilFecha = findViewById<TextInputLayout>(R.id.activity_notif_form_til_fecha)
        val tilEstado = findViewById<TextInputLayout>(R.id.activity_notif_form_til_estado)
        //val tilAsignatura = findViewById<TextInputLayout>(R.id.activity_notif_form_til_asignatura)
        val spnRamos = findViewById<Spinner>(R.id.activity_notif_form_spn_ramos)

        val btnSubmit = findViewById<Button>(R.id.activity_task_form_btn_submit)
        val tvBack = findViewById<TextView>(R.id.activity_task_form_tv_back)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.ramo_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnRamos.adapter = adapter




        if (enviada != null) {
            tvTitle.text = "Editando tarea ${enviada.id}"
            tilAsunto.editText?.text = Editable.Factory.getInstance().newEditable(enviada.asunto)


            tilDescription.editText?.text = Editable.Factory.getInstance().newEditable(enviada.description)

        }

        btnSubmit.setOnClickListener {
            if (enviada == null) {
                val newEnviada = Enviada(
                    id = null,
                    asunto = tilAsunto.editText?.text.toString(),
                    fecha = currentDate,
                     asignatura= spnRamos.selectedItem.toString(),

                    description = tilDescription.editText?.text.toString(),
                    estado = "ENVIADO"

                )
                enviadaController.create(newEnviada)

            } else {
                val updatedEnviada = Enviada(
                    id = enviada.id,
                    asunto = tilAsunto.editText?.text.toString(),
                    fecha = currentDate,
                  //  asignatura= spnAsignatura.selectedItem.toString(),
                   asignatura = spnRamos.selectedItem.toString(),
                    description = tilDescription.editText?.text.toString(),
                    estado = "EDITADO"
                )
                enviadaController.update(updatedEnviada)
            }
        }

        tvBack.setOnClickListener {
            super.onBackPressed()
        }
    }
}


