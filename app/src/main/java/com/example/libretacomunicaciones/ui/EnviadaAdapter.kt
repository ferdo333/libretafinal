package com.example.libretacomunicaciones.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.libretacomunicaciones.Models.Enviada
import com.example.libretacomunicaciones.R

class EnviadaAdapter (private val ctx: Context, private val enviadas: List<Enviada>): BaseAdapter()
{

    override fun getCount(): Int {
        return enviadas.size
    }

    override fun getItem(i: Int): Enviada {
        return enviadas[i]
    }

    override fun getItemId(i: Int): Long {
        return enviadas[i].id!!
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
        val inflater = LayoutInflater.from(ctx)

        val rowView = inflater.inflate(R.layout.item_enviada, null)

        val enviada = enviadas[i]

        val tvTitle = rowView.findViewById<TextView>(R.id.item_enviada_tv_title)
        val tvId = rowView.findViewById<TextView>(R.id.item_enviada_tv_id)
        val tvDescription = rowView.findViewById<TextView>(R.id.item_enviada_tv_description)

        tvTitle.text = enviada.asignatura
tvId.text = enviada.id.toString()
        tvDescription.text = enviada.description
       // tvTitle.text = enviada.asignatura + ": " + enviada.asunto

        return rowView
    }

}