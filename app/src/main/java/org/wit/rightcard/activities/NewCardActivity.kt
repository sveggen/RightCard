package org.wit.rightcard.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView

import com.google.firebase.auth.FirebaseAuth
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_card.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.activities.items.CardItem
import org.wit.rightcard.models.CardModel
import org.wit.rightcard.models.UserCardModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.stores.CardStore
import org.wit.rightcard.models.stores.UserCardStore
import org.wit.rightcard.helpers.*


class NewCardActivity : AppCompatActivity(), AnkoLogger {

    val adapter = GroupAdapter<ViewHolder>()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_card)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        recyclerview_n.adapter = adapter
        retrieveCards()
     }

    private fun retrieveCards(){
        val creditcard = CardStore()
        creditcard.getAll(object: Callback<CardModel> {
            override fun onCallback(list: List<CardModel>) {
                for (card in list) {
                        adapter.add(CardItem(card))
                }
            }
        })
        adapter.setOnItemClickListener { item, view ->
            val cardItem = item as CardItem
            val newUserCard =
                UserCardModel(
                    auth.uid + cardItem.creditcard.id, cardItem.creditcard.id, cardItem.creditcard.name,
                    "", auth.uid)

            if (cardItem.creditcard.id != null) {
                addCard(newUserCard)
            }
            finish()
            startActivity(intent)
        }
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

    private fun addCard(userCardModel: UserCardModel){
        val userCardStore = UserCardStore()
        userCardStore.create(userCardModel)
    }
}


