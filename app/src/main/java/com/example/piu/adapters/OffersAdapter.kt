package com.example.piu.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.piu.R
import com.example.piu.model.Offer
import java.text.FieldPosition

class OffersAdapter(private val context: Context,
                    private var dataSource: ArrayList<Offer>) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(index: Int): Offer {
        return dataSource[index]
    }

    override fun getItemId(index: Int): Long {
        return dataSource[index].id.toLong()
    }

    override fun getView(index: Int, existingTemplate: View?, elementGroup: ViewGroup): View
    {
        // get view for row item
// check if we can reuse a previously defined cell which now is not visible anymore
        val rowView = existingTemplate ?:inflater.inflate(R.layout.offers_list_element, elementGroup, false)

        val titleRef = rowView.findViewById<TextView>(R.id.offerTitle)
        titleRef.text = getItem(index).title

        val imageRef = rowView.findViewById<ImageView>(R.id.offerimage)
        imageRef.setImageResource(getItem(index).image)

        val priceRef = rowView.findViewById<TextView>(R.id.offerPrice)
        priceRef.text = getItem(index).price.toString() + " " + getItem(index).currency

        val descriptionRef = rowView.findViewById<TextView>(R.id.offerDescription)
        descriptionRef.text = getItem(index).description

        return rowView
    }
    fun addOffer(offer: Offer, position: Int)
    {
        dataSource.add(position, offer)
    }
    fun removeOffer(position: Int)
    {
        dataSource.removeAt(position)
    }
}




