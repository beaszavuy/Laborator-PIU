package com.example.piu.model

import com.example.piu.R
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class DataGenerator {
    fun getOffers(): ArrayList<Offer> {
        val response = ArrayList<Offer>();
        response.add(Offer(1,"Barcelona, 3 nights", R.drawable.offer_1, 300,"EUR", "Desscription for Barcelona ..."))
        response.add(Offer(2,"Maldive, 7 nights", R.drawable.offer_2, 1050,"EUR", "Desscription for Maldive ..."))
        response.add(Offer(1,"Thailand, 10 nights", R.drawable.offer_3, 1250,"EUR", "Desscription for Thailand ..."))
        return response
    }


    fun getChatMessages(): ArrayList<ChatMessage>
    {
        val response = ArrayList<ChatMessage>()
        response.add(ChatMessage(1,"teodor","Salut, ce faci?", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS").withZone(ZoneOffset.UTC).format(Instant.now())))
        response.add(ChatMessage(2,"petrica","Salut, sunt bine!", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS").withZone(ZoneOffset.UTC).format(Instant.now())))
        return response
    }
}