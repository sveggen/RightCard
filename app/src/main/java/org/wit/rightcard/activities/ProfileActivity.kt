package org.wit.rightcard.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R


class ProfileActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var auth: FirebaseAuth
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        //Init toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        //Database reference
        database = Firebase.database.reference

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
        btn_delete_user.setOnClickListener{
            deleteUser()
        }

        val textView = findViewById<TextView>(R.id.text_email)
        textView.text = auth.currentUser?.email
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
        when (item?.itemId) {
            R.id.actionNewCard -> startActivityForResult<NewCreditCardActivity>(0)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun signOut() {
        auth.signOut()
        info("user signed out")
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

    private fun deleteUser(){
        //delete user in auth
        val user = FirebaseAuth.getInstance().currentUser
        user?.delete()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //sign user out
                    auth.signOut()
                    info("user deleted + signed out")
                    startActivity(Intent(this,SignUpActivity::class.java))
                    finish()
                }
            }
    }
}
