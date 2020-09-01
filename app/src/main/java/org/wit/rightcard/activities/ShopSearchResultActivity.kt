package org.wit.rightcard.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_shop_search_result.*

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.items.UserCardBenefitsItem
import org.wit.rightcard.persistence.models.UserCardBenefitsModel
import org.wit.rightcard.persistence.interfaces.Callback
import org.wit.rightcard.persistence.stores.UserCardBenefitsStore


class ShopSearchResultActivity : AppCompatActivity(), AnkoLogger{

    val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search_result)
        val enteredText: String? = intent.getStringExtra("enteredText")

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = getString(R.string.toolbar_shop_result) +" '" + enteredText.toString() + "'"

        recyclerview_shop_search_result.adapter = adapter

        if (enteredText != null) {
            retrieveBenefits(enteredText)
            info(enteredText)
        }
    }

    private fun retrieveBenefits(enteredText: String){
        val userCardBenefitsStore = UserCardBenefitsStore()
        userCardBenefitsStore.getAll(enteredText, object: Callback<UserCardBenefitsModel> {
            override fun onCallback(list: List<UserCardBenefitsModel>) {
                if (list.isNotEmpty()) {
                for (benefit in list) {
                    if (!benefit.shop?.id.isNullOrEmpty()) {
                        findViewById<TextView>(R.id.no_benefits)?.visibility = View.GONE
                        recyclerview_shop_search_result.visibility = View.VISIBLE
                        adapter.add(UserCardBenefitsItem(benefit))
                        info("there are cards")
                    }
                }
                } else {
                    findViewById<TextView>(R.id.no_benefits)?.visibility = View.VISIBLE
                    recyclerview_shop_search_result.visibility = View.GONE
                    info("there are no cards")
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




