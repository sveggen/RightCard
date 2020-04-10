package org.wit.rightcard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.btn_sign_up
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        //Init toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        btn_sign_out.setOnClickListener{
            signOut()
        }
        btn_sign_up.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        btn_log_in.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
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
            R.id.actionPreferences -> startActivityForResult<ProfileActivity>(0)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun signOut() {
        auth.signOut()
    }

}
