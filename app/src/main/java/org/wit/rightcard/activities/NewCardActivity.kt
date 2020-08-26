package org.wit.rightcard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_card.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.activities.items.CardItem
import org.wit.rightcard.activities.items.UserCardItem
import org.wit.rightcard.models.CardModel
import org.wit.rightcard.models.UserCardModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.stores.UserCardStore


class NewCardActivity : AppCompatActivity(), AnkoLogger {

    val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_card)
        setSupportActionBar(findViewById(R.id.toolbar))

        recyclerview_n.adapter = adapter
        retrieveCards()
     }

    private fun retrieveCards(){
        val dataref = FirebaseDatabase.getInstance().getReference("/creditcards")
        dataref.addListenerForSingleValueEvent(object: ValueEventListener{

            override fun onDataChange(dataSnap: DataSnapshot) {

                dataSnap.children.forEach{
                    info("CreditCards = "+ it.toString()) //logs the fetching of data from db
                    val creditcard = it.getValue(CardModel::class.java)
                    if (creditcard != null) {
                        //adds the creditcard object to the adapter
                        adapter.add(CardItem(creditcard))
                    }
                }
                adapter.setOnItemClickListener{item, view ->
                    val cardItem = item as CardItem

                    val creditcardname = cardItem.creditcard.name
                    val creditcarduuid = cardItem.creditcard.uuid
                    info("CREDITCARDUUID"+ creditcarduuid)
                    saveCardToDB(creditcarduuid, creditcardname)
                    val intent = Intent(view.context, UserCardActivity::class.java)
                    startActivity(intent)

                }
                //tells the recycleview to use the adapter
                recyclerview_n.adapter = adapter
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
    }

    fun retrieveCardsV2(){
        val userCreditcard = UserCardStore()
        userCreditcard.getAll(object: Callback<UserCardModel> {
            override fun onCallback(list: List<UserCardModel>) {
                for (card in list) {
                    adapter.add(
                        UserCardItem(card)
                    )
                }
            }
        })
        adapter.setOnItemClickListener { item, view ->
            val userCardItem = item as UserCardItem
            val usercarduuid =userCardItem.userCreditcard.uuid
            if (usercarduuid != null) {
              //  deleteCardV2(usercarduuid)
            }
            finish()
            startActivity(intent)
        }
        //tells the recycleview to use the adapter
        // recycleview_my_cards.adapter = adapter
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
    private fun saveCardToDB(creditcarduuid: String?, creditcardname: String?){
        val uid = FirebaseAuth.getInstance().uid
        val usercarduuid = uid+creditcarduuid
        val ref = FirebaseDatabase.getInstance().getReference("/usercreditcards/$usercarduuid")

        val userCreditCard = UserCardModel(usercarduuid, creditcarduuid,creditcardname," ", uid)

        ref.setValue(userCreditCard)
            .addOnSuccessListener {  }
    }
}


