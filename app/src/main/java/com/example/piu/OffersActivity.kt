package com.example.piu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.example.piu.adapters.OffersAdapter
import com.example.piu.model.DataGenerator
import com.example.piu.model.Offer

class OffersActivity : AppCompatActivity() {
    lateinit var listViewRef: ListView
    lateinit var offersAdapter: OffersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offers)

        listViewRef = findViewById<ListView>(R.id.offersListView)
        offersAdapter = OffersAdapter(this, DataGenerator().getOffers())

        listViewRef.adapter = offersAdapter

        registerForContextMenu(listViewRef)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // check if the menu is created for the targeted list
        if (v!!.id == R.id.offersListView)
        {
            // identify the item selected from the list
//            val info = menuInfo as AdapterView.AdapterContextMenuInfo
            //menu!!.setHeaderTitle(valoare_specifica_elementului)

            // load the visual structure of the menu
            getMenuInflater().inflate(R.menu.offers_context_menu, menu);
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean
    {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when(item.itemId)
        {
            R.id.add_offer_item -> {
                offersAdapter.addOffer(Offer(offersAdapter.count,"Added offer",R.drawable.offer_2, 1000,"EUR","Added offer description..."), info.position )
            }
            R.id.remove_offer_item -> {
                offersAdapter.removeOffer(info.position)
            }
        }
        offersAdapter.notifyDataSetChanged()
        return super.onContextItemSelected(item)
    }

//    override fun onItemClick(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long)
//    {
//        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
//    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {

        getMenuInflater().inflate(R.menu.offers_options_menu, menu);

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.clear_favorites_item)
        {
            Toast.makeText(this, "Clear favorites", Toast.LENGTH_SHORT).show()
        }
        else if( item.itemId == R.id.reset_list_item)
        {
            offersAdapter = OffersAdapter(this, DataGenerator().getOffers())
            listViewRef.adapter = offersAdapter

            Toast.makeText(this, "Reset list", Toast.LENGTH_SHORT).show()

        }
        else if( item.itemId == R.id.chat_with_us_item)
        {
            val intent = Intent(this,ChatActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}