package org.wit.rightcard.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_shop_search_result.*

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.activities.items.UserCardBenefitsItem
import org.wit.rightcard.models.UserCardBenefitsModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.stores.UserCardBenefitsStore


class ShopSearchResultActivity : AppCompatActivity(), AnkoLogger{

    val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search_result)
        val enteredText: String? = intent.getStringExtra("enteredText")

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Search Result for '" + enteredText.toString() + "'"

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
                for (benefit in list) {
                    info(benefit)
                    adapter.add(UserCardBenefitsItem(benefit))
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




