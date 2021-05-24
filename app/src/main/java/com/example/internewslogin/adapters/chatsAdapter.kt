package com.example.internewslogin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internewslogin.R
import com.example.internewslogin.dataClases.Chat
import kotlinx.android.synthetic.main.card_chat.view.*

class chatsAdapter(val chats: List<Chat>): RecyclerView.Adapter<chatsAdapter.chatHolder>() {
    class chatHolder(view: View) : RecyclerView.ViewHolder(view)  {
        fun data(chats: Chat){
            itemView.textoEnviado.text = chats.contenido
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chatsAdapter.chatHolder {
        val LayoutInflater =LayoutInflater.from(parent.context)
        return chatsAdapter.chatHolder(LayoutInflater.inflate(R.layout.card_chat,parent,false))
    }

    override fun onBindViewHolder(holder: chatsAdapter.chatHolder, position: Int) {
        val item = chats[position]
        holder.data(item)
    }


    override fun getItemCount(): Int {
        return chats.size
    }
}