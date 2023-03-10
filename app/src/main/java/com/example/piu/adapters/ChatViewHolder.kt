package com.example.piu.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.piu.R
import com.example.piu.model.ChatMessage

class ChatViewHolder (private val view: View) : RecyclerView.ViewHolder ( view )
{
    private lateinit var usernameRef: TextView
    private lateinit var messageRef: TextView
    private lateinit var timestampRef: TextView
//    private lateinit var pictureRef: ImageView
    init
    {
        usernameRef = view.findViewById(R.id.chatMessageUsername)
        messageRef = view.findViewById(R.id.chatMessageBody)
        timestampRef = view.findViewById(R.id.chatMessageTimestamp)
//        pictureRef = view.findViewById(R.id.pictureImageView)
    }
    fun bindData (data: ChatMessage)
    {
        usernameRef.text = data.username
        messageRef.text = data.message
        timestampRef.text = data.timestamp
//        pictureRef. setImageResource(data.image)
    }
}
