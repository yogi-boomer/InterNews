package com.example.internewslogin.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internewslogin.dataClases.Actividad

class actividadesInscritasAdapter(val actividadesIns: List<Actividad>):RecyclerView.Adapter<actividadesInscritasAdapter.actividadesInsHolder>() {
    class actividadesInsHolder(view: View): RecyclerView.ViewHolder(view) {
        fun data(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): actividadesInsHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: actividadesInsHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}