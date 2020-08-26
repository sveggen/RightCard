package org.wit.rightcard.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_user_card.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.activities.items.UserCardItem
import org.wit.rightcard.models.ShopModel
import org.wit.rightcard.models.UserCardModel
import org.wit.rightcard.models.stores.ShopStore
import org.wit.rightcard.models.stores.UserCardStore

class UserCardActivity : AppCompatActivity(), AnkoLogger, AdapterView.OnItemSelectedListener {

    val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_card)
        setSupportActionBar(findViewById(R.id.toolbar))

        recycleview_my_cards.adapter = adapter
        retrieveCardsV2()

    }

    fun retrieveCardsV2(){
        val userCreditcard = UserCardStore()
        userCreditcard.readData234(object: UserCardStore.MyCallback {
            override fun onCallback(list: ArrayList<UserCardModel>) {
                for (card in list) {
                    adapter.add(
                        UserCardItem(card))
                }
            }
        })
        adapter.setOnItemClickListener { item, view ->
            val userCardItem = item as UserCardItem
            val usercarduuid =userCardItem.userCreditcard.uuid
            if (usercarduuid != null) {
                deleteCardV2(usercarduuid)
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
    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    private fun deleteCardV2(uuid: String){
        val userCardStore = UserCardStore()
        userCardStore.delete(uuid)
    }

    private fun deleteCard(uuid: String?){
        //delete user card from db
        val ref = FirebaseDatabase.getInstance().getReference("/usercreditcards/$uuid")
        ref.removeValue()
    }
    }





