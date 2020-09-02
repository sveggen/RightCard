package org.wit.rightcard.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_shop_search.*

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.items.UserCardBenefitsItem
import org.wit.rightcard.persistence.interfaces.Callback
import org.wit.rightcard.persistence.models.BenefitModel
import org.wit.rightcard.persistence.models.CardBenefitsModel
import org.wit.rightcard.persistence.models.ShopModel
import org.wit.rightcard.persistence.models.UserCardBenefitsModel
import org.wit.rightcard.persistence.stores.*

/**
 *  Handles the view for searching and returning benefits/discounts.
 */
class ShopSearchActivity : AppCompatActivity(), AnkoLogger{

    private val resultAdapter = GroupAdapter<ViewHolder>()
    val shops = ArrayList<String>()
    val searchButton = R.id.shop_search_btn

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_search)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = getString(R.string.toolbar_shop_search)
        findViewById<Button>(searchButton)?.visibility = View.INVISIBLE

        //populates shops-array with shop-names
        retrieveShops()

        //adds shops-array to adapter
        val autotextView = findViewById<AutoCompleteTextView>(R.id.autoTextView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, shops)
        autotextView.setAdapter(adapter)

         // Hides and displays search button based on input in EditText-field.
        findViewById<AutoCompleteTextView>(R.id.autoTextView).setOnItemClickListener { _, _, _, _ ->
            findViewById<Button>(searchButton)?.visibility = View.VISIBLE
        }
        findViewById<AutoCompleteTextView>(R.id.autoTextView).addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
                findViewById<Button>(searchButton)?.visibility = View.INVISIBLE
            }

            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                findViewById<Button>(searchButton)?.visibility = View.INVISIBLE
            }
        })

        /**
         * Handles search button-event, hides the keyboard and
         * calls the retrieveBenefits()-function.
         */

        findViewById<Button>(searchButton)?.setOnClickListener {
            val enteredText = autotextView.text.toString()
            recyclerview_shop_search_result.adapter = resultAdapter
            if (enteredText != null) {
                supportActionBar?.title = getString(R.string.toolbar_shop_result) +" '" + enteredText + "'"
                findViewById<Button>(searchButton)?.visibility = View.INVISIBLE
                resultAdapter.clear()
                retrieveBenefits(enteredText)
                //hides the keyboard
                val view = this.currentFocus
                view?.let { v ->
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
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
        val creditcardid = "720f3839-fd89-416c-9537-83a28e5899d1"
        val cardBenefitModel = CardBenefitsModel(shopid + benefitid + creditcardid, shopid, benefitid, creditcardid)
        CardBenefitsStore().create(cardBenefitModel)
    }

    fun createStore(){
        val shopModel = ShopModel("", "SAS")
        ShopStore().create(shopModel)
    }

    /**
     * Retrieves all the shops that exists in the database and adds them to the
     * shop-adapter.
     */
    private fun retrieveShops(){
        val shopStore = ShopStore()
        shopStore.get(object: Callback<ShopModel> {
            override fun onCallback(list: List<ShopModel>) {
                for (shop in list) {
                    shops.add(shop.name.toString())
                }
            }
        })
    }

    /**
     * Retrieves all the benefits of owned cards for a specific shop based on the input String.
     * And adds them to the resultAdapter. If list is empty a TextView will be shown instead.
     * @param enteredText shopname
     */
    private fun retrieveBenefits(enteredText: String){
        val userCardBenefitsStore = UserCardBenefitsStore()
        userCardBenefitsStore.getAll(enteredText, object: Callback<UserCardBenefitsModel> {
            override fun onCallback(list: List<UserCardBenefitsModel>) {
                if (list.isNotEmpty()) {
                    for (benefit in list) {
                        if (!benefit.shop?.id.isNullOrEmpty()) {
                            findViewById<TextView>(R.id.no_benefits)?.visibility = View.GONE
                            recyclerview_shop_search_result.visibility = View.VISIBLE
                            resultAdapter.add(UserCardBenefitsItem(benefit))
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
}
