package com.example.internewslogin.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.internewslogin.ActividadesGen
import com.example.internewslogin.R
import com.example.internewslogin.dataClases.Actividad
import kotlinx.android.synthetic.main.activity_card_view.view.*


class actividadesAdapter(val actividades: MutableList<Actividad>): RecyclerView.Adapter<actividadesAdapter.actividadHolder>() {
    class actividadHolder(view: View) : RecyclerView.ViewHolder(view) {

        val btn: Button = itemView.findViewById(R.id.btnInformacion)

        fun data(actividades: Actividad){
            val id = actividades.id_conv
            itemView.textoTile.text = actividades.titulo
            itemView.descripcion.text = actividades.descripcion
            itemView.idActividad.text = actividades.id_conv.toString()

        }
        init {
            btn.setOnClickListener{
                datos()
            }
        }

        private fun datos() {
            var intent: Intent = Intent(itemView.context,ActividadesGen::class.java)
            intent.putExtra("id_conv",itemView.idActividad.text.toString())
            intent.putExtra("titulo",itemView.textoTile.text.toString())
            itemView.context.startActivity(intent)
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