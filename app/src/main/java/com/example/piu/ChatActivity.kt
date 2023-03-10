package com.example.piu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.piu.adapters.ChatAdapter
import com.example.piu.model.ChatMessage
import com.example.piu.model.DataGenerator
import com.example.piu.model.Userinfo
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class ChatActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var chatAdapter: ChatAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val linearLayoutManager = LinearLayoutManager ( this )
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        chatAdapter = ChatAdapter(this, DataGenerator().getChatMessages())

        val recyclerViewRef = findViewById<RecyclerView>(R.id.chatMessagesList)
        recyclerViewRef.layoutManager = linearLayoutManager
        recyclerViewRef.adapter = chatAdapter

        val sendButtonRef = findViewById<Button>(R.id.chatSendButton)
        sendButtonRef.setOnClickListener(this)

        val usernameRef = findViewById<TextView>(R.id.usernameDisplay)
        usernameRef.text = Userinfo.username.toString()
    }

    override fun onClick(p0: View?) {
        val messageFieldRef = findViewById<EditText>(R.id.chatMessageField)
        chatAdapter.addMessage(ChatMessage(chatAdapter.itemCount, Userinfo.username, messageFieldRef.text.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS").withZone(ZoneOffset.UTC).format(Instant.now())))
        chatAdapter.notifyItemInserted(0)
        messageFieldRef.setText("")
    }
}