package org.wit.rightcard.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_add_card.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R

class AddCardActivity : AppCompatActivity(), AnkoLogger, AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)
        //Init toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

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
        //Log to console when card is added
        btnAddCard.setOnClickListener(){
            info("Card added")
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

        return super.onOptionsItemSelected(item)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(parent:AdapterView<*>, view:View, pos:Int, id:Long) {
      val spinneritem = parent.getItemAtPosition(pos)
        //get id of item
        //save id to users to json
    }

}
