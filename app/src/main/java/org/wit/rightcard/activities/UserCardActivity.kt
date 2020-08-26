package org.wit.rightcard.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_user_card.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.activities.items.UserCardItem
import org.wit.rightcard.models.UserCardModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.stores.UserCardStore

class UserCardActivity : AppCompatActivity(), AnkoLogger, AdapterView.OnItemSelectedListener {

    val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_card)
        setSupportActionBar(findViewById(R.id.toolbar))

        recycleview_my_cards.adapter = adapter
        retrieveCards()
    }

    private fun retrieveCards(){
        val userCreditcard = UserCardStore()
        userCreditcard.getAll(object: Callback<UserCardModel> {
            override fun onCallback(list: List<UserCardModel>) {
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
                deleteCard(usercarduuid)
            }
            finish()
            startActivity(intent)
        }
        //tells the recycleview to use the adapter
        recycleview_my_cards.adapter = adapter
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

    private fun deleteCard(uuid: String){
        val userCardStore = UserCardStore()
        userCardStore.delete(uuid)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }
}





