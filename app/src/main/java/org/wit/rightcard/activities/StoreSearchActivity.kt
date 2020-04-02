package org.wit.rightcard.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.main.MainApp

class StoreSearchActivity : AppCompatActivity(), AnkoLogger{

 //   lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_search)
  //      app = application as MainApp

        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.actionCards -> startActivityForResult<AddCardActivity>(0)
        }

        return super.onOptionsItemSelected(item)
    }
}


