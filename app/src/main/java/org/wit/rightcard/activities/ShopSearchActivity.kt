package org.wit.rightcard.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.activities.items.CardItem
import org.wit.rightcard.models.*
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.stores.*


class ShopSearchActivity : AppCompatActivity(), AnkoLogger{

    val shops = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search)
        setSupportActionBar(findViewById(R.id.toolbar))

        retrieveShops()

        //autocomplete
        val autotextView = findViewById<AutoCompleteTextView>(R.id.autoTextView)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, shops)
        autotextView.setAdapter(adapter)

        findViewById<Button>(R.id.btn)?.setOnClickListener {
            val enteredText = getString(R.string.submitted_shop) + " " + autotextView.getText()
            Toast.makeText(this, enteredText, Toast.LENGTH_SHORT).show()
        }

        hentAlle()
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

    fun createBenefit(){
        val benefitModel = BenefitModel("", "10% off on all groceries", "Grocery", "10%")
        BenefitStore().create(benefitModel)

    }

    fun createCardBenefit(){
        val shopid = "51672797-153b-4cea-be57-5bb7431c538a"
        val benefitid = "40708937-0c2a-408a-8003-8f7fa9dec039"
        val creditcardid = "706c0cff-387b-4f3b-97d8-3f07ed4a304d"
        val cardBenefitModel = CardBenefitsModel(shopid + benefitid + creditcardid, shopid, benefitid, creditcardid)
        CardBenefitsStore().create(cardBenefitModel)
    }

    fun createStore(){
        val shopModel = ShopModel("", "SAS")
        ShopStore().create(shopModel)
    }

    fun hentAlle(){
        val cardBenefitsStore = CardBenefitsStore()
        cardBenefitsStore.getAllEverything("Rema 1000", object: Callback<UserCardBenefitsModel> {
            override fun onCallback(list: List<UserCardBenefitsModel>) {
                info("kort")
                info(list)
                for (card in list) {
                    info("kort hver for seg")
                    info(card)
                }
            }
        })
    }

    private fun retrieveShops(){
        val shopStore = ShopStore()
        shopStore.getAll(object: Callback<ShopModel> {
            override fun onCallback(list: List<ShopModel>) {
                for (shop in list) {
                    shops.add(shop.name.toString())
                }
            }
        })
    }

}
