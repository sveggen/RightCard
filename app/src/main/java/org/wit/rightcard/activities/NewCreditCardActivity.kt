package org.wit.rightcard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_credit_card.*
import kotlinx.android.synthetic.main.card_listing_newcard.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.models.CreditCardModel

class NewCreditCardActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_credit_card)
        setSupportActionBar(findViewById(R.id.toolbar))

        val adapter = GroupAdapter<ViewHolder>()

        recyclerview_n.adapter = adapter
        retrieveCards()
     }

    private fun retrieveCards(){
        val dataref = FirebaseDatabase.getInstance().getReference("/creditcards")
        dataref.addListenerForSingleValueEvent(object: ValueEventListener{

            override fun onDataChange(dataSnap: DataSnapshot) {
            val adapter = GroupAdapter<ViewHolder>()

                dataSnap.children.forEach{
                    info("CreditCards = "+ it.toString()) //logs the fetching of data from db
                    val creditcard = it.getValue(CreditCardModel::class.java)
                    if (creditcard != null) {
                        //adds the creditcard object to the adapter
                        adapter.add(CardItem(creditcard))
                    }
                }
                adapter.setOnItemClickListener{item, view ->
                    val cardItem = item as CardItem

                 //   val intent = Intent(view.context, CardActivity::class.java)
                   // intent.putExtra(cardItem.creditcard.name)
                    //startActivity(intent)

                }
                //tells the recycleview to use the adapter
                recyclerview_n.adapter = adapter
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
    }

    class CardItem(val creditcard: CreditCardModel): Item<ViewHolder>(){
        override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.creditcard_new_creditcard.text=creditcard.name
        }
        override fun getLayout(): Int {
            return R.layout.card_listing_newcard
        }
    }
            override fun onCreateOptionsMenu(menu: Menu?): Boolean {
                menuInflater.inflate(R.menu.menu_main, menu)
                return super.onCreateOptionsMenu(menu)
            }

            override fun onOptionsItemSelected(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.actionSearch -> startActivityForResult<StoreSearchActivity>(0)
                }
                when (item?.itemId) {
                    R.id.actionCards -> startActivityForResult<CardActivity>(0)
                }
                when (item?.itemId) {
                    R.id.actionPreferences -> startActivityForResult<ProfileActivity>(0)
                }
                when (item?.itemId) {
                    R.id.actionNewCard -> startActivityForResult<NewCreditCardActivity>(0)
                }
                return super.onOptionsItemSelected(item)
            }
        }
