package org.wit.rightcard.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R


class CardActivity : AppCompatActivity(), AnkoLogger, AdapterView.OnItemSelectedListener {

    lateinit var database: DatabaseReference
  //  lateinit var adapter: CardAdapter
    //private var CreditCardModel: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        //Init toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        //Database reference
        database = Firebase.database.reference

        //spinner spinner spinner spinner spinner
        val spinner: Spinner = findViewById(R.id.spinner)
        // ArrayAdapter using the dummy string array
        ArrayAdapter.createFromResource(
            this,
            R.array.CreditCards,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Applies the adapter to the spinner
            spinner.adapter = adapter
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.actionSearch -> startActivityForResult<StoreSearchActivity>(0)
        }
        when (item?.itemId) {
            R.id.actionCards -> startActivityForResult<CardActivity>(0)
        }
        when (item?.itemId) {
            R.id.actionPreferences -> startActivityForResult<LoginActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //do something
    }

    override fun onItemSelected(parent:AdapterView<*>, view:View, pos:Int, id:Long) {
      val spinneritem = parent.getItemAtPosition(pos)
        //get id of item
        //save id to user-json
        //display user json in recycleview
    }
}



