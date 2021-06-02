package com.example.internewslogin.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internewslogin.R
import com.example.internewslogin.dataClases.Actividad
import com.example.internewslogin.nuevoReporte
import kotlinx.android.synthetic.main.card_actividades.view.*

class misActividadesAdapter (val actividades: List<Actividad>): RecyclerView.Adapter<misActividadesAdapter.mActividaesHolder>() {
    class mActividaesHolder(view:View) :RecyclerView.ViewHolder(view){
        fun data(actividad: Actividad) {
            itemView.textoTile2.text = actividad.titulo
            itemView.descripcion2.text = actividad.descripcion
        }
        init {
            itemView.btnInformacion2.setOnClickListener {
                val intent: Intent = Intent(itemView.context, nuevoReporte::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): misActividadesAdapter.mActividaesHolder {
        val LayoutInflater =LayoutInflater.from(parent.context)
        return mActividaesHolder(LayoutInflater.inflate(R.layout.card_actividades,parent, false))
    }

    override fun onBindViewHolder(holder: misActividadesAdapter.mActividaesHolder, position: Int) {
        holder.data(actividades[position])
    }

    override fun getItemCount(): Int {
        return actividades.size
    }
}