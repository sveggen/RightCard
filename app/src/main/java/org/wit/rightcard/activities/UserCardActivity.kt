package org.wit.rightcard.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_user_card.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.items.UserCardItem
import org.wit.rightcard.persistence.models.UserCardModel
import org.wit.rightcard.persistence.interfaces.Callback
import org.wit.rightcard.persistence.stores.UserCardStore

/**
 * TODO
 *
 */
class UserCardActivity : AppCompatActivity(), AnkoLogger, AdapterView.OnItemSelectedListener {

    val adapter = GroupAdapter<ViewHolder>()
    val section = Section()
    val listItems = mutableListOf<UserCardItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_card)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = getString(R.string.toolbar_my_credit_cards)

        retrieveCards()
        recycleview_my_cards.adapter = adapter
        adapter.add(section)

        findViewById<Button>(R.id.editNickname)?.visibility = View.INVISIBLE

        //this works
        adapter.setOnItemClickListener { item, view ->
            val userCardItem = item as UserCardItem
            view.findViewById<Button>(R.id.deleteCreditCard)?.visibility = View.VISIBLE
            view.findViewById<Button>(R.id.deleteCreditCard)?.setOnClickListener {
                val id = userCardItem.userCreditcard.id
                if (id != null) {
                    deleteCard(id)
                }
                listItems.remove(item)
                if (listItems.isEmpty()){
                    findViewById<TextView>(R.id.no_user_cards)?.visibility = View.VISIBLE
                    recycleview_my_cards.visibility = View.GONE
                }
                section.update(listItems)
            }
        }
    }

    private fun retrieveCards() {
        val userCreditcard = UserCardStore()
        userCreditcard.get(object : Callback<UserCardModel> {
            override fun onCallback(list: List<UserCardModel>) {
                if (list.isNotEmpty()) {
                    for (card in list) {
                        findViewById<TextView>(R.id.no_user_cards)?.visibility = View.GONE
                        recycleview_my_cards.visibility = View.VISIBLE
                        listItems.add(UserCardItem(card))
                    }
                } else {
                    findViewById<TextView>(R.id.no_user_cards)?.visibility = View.VISIBLE
                    recycleview_my_cards.visibility = View.GONE
                }
                section.addAll(listItems)
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


    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    private fun deleteCard(creditCardId : String) {
            val userCardStore = UserCardStore()
            userCardStore.delete(creditCardId)
    }



}





