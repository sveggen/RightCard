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
import org.wit.rightcard.models.BenefitModel
import org.wit.rightcard.models.CardBenefitsModel
import org.wit.rightcard.models.CardModel
import org.wit.rightcard.models.ShopModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.stores.BenefitStore
import org.wit.rightcard.models.stores.CardBenefitsStore
import org.wit.rightcard.models.stores.ShopStore
import org.wit.rightcard.models.stores.UserCardStore


class ShopSearchActivity : AppCompatActivity(), AnkoLogger{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search)
        setSupportActionBar(findViewById(R.id.toolbar))

        //autocomplete
        val autotextView = findViewById<AutoCompleteTextView>(R.id.autoTextView)
        val shops = resources.getStringArray(R.array.Shops)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, shops)
        autotextView.setAdapter(adapter)

        findViewById<Button>(R.id.btn)?.setOnClickListener {
            val enteredText = getString(R.string.submitted_shop) + " " + autotextView.getText()
            Toast.makeText(this, enteredText, Toast.LENGTH_SHORT).show()
        }

        createCardBenefit()
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
}
