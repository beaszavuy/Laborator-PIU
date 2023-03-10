package com.example.piu.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.piu.ChatActivity
import com.example.piu.R
import com.example.piu.model.ChatMessage

class ChatAdapter (
    private val context: Context,
    private val dataSource: ArrayList<ChatMessage>) : RecyclerView.Adapter<ChatViewHolder>()
{
    // la fel ca și în cazul adapter-ului pentru ListView vom defini și aici un LayoutInflater
    // utilizat la interpretarea design-ului descris în XML pentru elementele listei
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = inflater.inflate ( R.layout.chat_list_element, parent, false )
        return ChatViewHolder ( view )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bindData(dataSource[position])
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
    fun addMessage(message:ChatMessage)
    {
        dataSource.add(0,message)
    }
}