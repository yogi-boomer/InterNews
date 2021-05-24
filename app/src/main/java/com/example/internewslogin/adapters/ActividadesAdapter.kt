package com.example.internewslogin.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internewslogin.ActividadesGen
import com.example.internewslogin.R
import com.example.internewslogin.dataClases.Actividad
import kotlinx.android.synthetic.main.activity_card_view.view.*


class actividadesAdapter(val actividades: List<Actividad>): RecyclerView.Adapter<actividadesAdapter.actividadHolder>() {
    class actividadHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun data(actvidades: Actividad){
            itemView.textoTile.text = actvidades.titulo
            itemView.descripcion.text = actvidades.descripcion
            itemView.imgBackgrond.setImageResource(actvidades.img)

        }
        init {
            itemView.btnInformacion.setOnClickListener {
                val intent:Intent = Intent(itemView.context, ActividadesGen::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): actividadHolder {
        val LayoutInflater= LayoutInflater.from(parent.context)
        return actividadHolder(LayoutInflater.inflate(R.layout.activity_card_view, parent, false))
    }

    override fun onBindViewHolder(holder: actividadHolder, position: Int) {
        val item = actividades[position]
        holder.data(item)
    }

    override fun getItemCount(): Int {
        return actividades.size
    }

}