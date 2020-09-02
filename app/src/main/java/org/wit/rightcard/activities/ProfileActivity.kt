package org.wit.rightcard.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R

/**
 * Handles the profile page and displays logged-in users email,
 * and handles the deletion of account as well as logging out.
 */
class ProfileActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        //Init toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = getString(R.string.toolbar_profile)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        btn_sign_out.setOnClickListener{
            signOut()
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

    /**
     * Signs user out and sends user to Login-view.
     */
    private fun signOut() {
        auth.signOut()
        info("user signed out")
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

    /**
     * Deletes user from Firebase Authentication,
     * user is then logged out and sent to SignUp-view.
     */
    private fun deleteUser(){
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
