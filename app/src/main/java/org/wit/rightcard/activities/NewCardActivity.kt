package org.wit.rightcard.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView

import com.google.firebase.auth.FirebaseAuth
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_card.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.items.CardItem
import org.wit.rightcard.persistence.models.CardModel
import org.wit.rightcard.persistence.interfaces.Callback
import org.wit.rightcard.persistence.stores.CardStore


class NewCardActivity : AppCompatActivity(), AnkoLogger {

    val adapter = GroupAdapter<ViewHolder>()
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_card)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = getString(R.string.toolbar_new_card)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        recyclerview_new_card.adapter = adapter
        retrieveCards()
     }

    private fun retrieveCards(){
        val creditCard = CardStore()
        creditCard.getAllNewCards(object: Callback<CardModel> {
            override fun onCallback(list: List<CardModel>) {
                if (list.isNotEmpty()){
                    for (card in list) {
                        findViewById<TextView>(R.id.no_new_cards)?.visibility = View.GONE
                        recyclerview_new_card.visibility = View.VISIBLE
                        adapter.add(CardItem(card))
                    }
                } else {
                    findViewById<TextView>(R.id.no_new_cards)?.visibility = View.VISIBLE
                    recyclerview_new_card.visibility = View.GONE
                    info("card not found")
                }
            }
        })
    }



            override fun onCreateOptionsMenu(menu: Menu?): Boolean {
                menuInflater.inflate(R.menu.menu_main, menu)
                return super.onCreateOptionsMenu(menu)
            }

            override fun onOptionsItemSelected(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.actionSearch -> startActivityForResult<ShopSearchActivity>(0)
                }
                when (item?.itemId) {
                    R.id.actionCards -> startActivityForResult<UserCardActivity>(0)
                }
                when (item?.itemId) {
                    R.id.actionPreferences -> startActivityForResult<ProfileActivity>(0)
                }
                when (item?.itemId) {
                    R.id.actionNewCard -> startActivityForResult<NewCardActivity>(0)
                }
                return super.onOptionsItemSelected(item)
            }
}


