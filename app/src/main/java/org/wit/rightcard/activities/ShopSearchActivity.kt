package org.wit.rightcard.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import org.wit.rightcard.models.stores.ShopStore

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.models.CardModel
import org.wit.rightcard.models.ShopModel
import org.wit.rightcard.models.UserCardModel
import org.wit.rightcard.models.interfaces.Store
import org.wit.rightcard.models.stores.CardStore
import org.wit.rightcard.models.stores.UserCardStore

class ShopSearchActivity : AppCompatActivity(), AnkoLogger{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search)
        setSupportActionBar(findViewById(R.id.toolbar))

        //autocomplete
        val autotextView = findViewById<AutoCompleteTextView>(R.id.autoTextView)
        val creditcards = resources.getStringArray(R.array.CreditCards)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, creditcards)
        autotextView.setAdapter(adapter)

        findViewById<Button>(R.id.btn)?.setOnClickListener {
            val enteredText = getString(R.string.submitted_lang) + " " + autotextView.getText()
            Toast.makeText(this@ShopSearchActivity, enteredText, Toast.LENGTH_SHORT).show()
        }

        val userCardModel = UserCardModel("1", "1", "1", "1", "1")
        val userCardStore = UserCardStore()
        userCardStore.update(userCardModel)

        val card = CardModel("1447", "Sparebanken Sør")
        CardStore().create(card)
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
