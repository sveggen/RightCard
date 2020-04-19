package org.wit.rightcard.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.models.UserModel


class SignUpActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var auth: FirebaseAuth
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        //Init toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        //Database reference
        database = Firebase.database.reference

        //Init UserSignUp fun from button click
        btn_sign_up.setOnClickListener {
            userSignUp()
        }

    }
        private fun userSignUp() {
            if (username.text.toString().isEmpty()) {
                username.error = "Please enter an email address"
                username.requestFocus()
                return
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {
                username.error = "Please enter a valid email address"
                username.requestFocus()
                return
            }
            if (password.text.toString().isEmpty()) {
                password.error = "Please enter a password"
                password.requestFocus()
                return
            }

            auth.createUserWithEmailAndPassword(username.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        //   val user : FirebaseUser? = auth.currentUser
                        user?.sendEmailVerification()
                            ?.addOnCompleteListener{task ->
                                if (task.isSuccessful){
                                    createUserDB(database)
                                    startActivity(Intent(this,LoginActivity::class.java))
                                    finish()
                                }
                            }
                    } else {
                        Toast.makeText(
                            baseContext,
                            "Signing up failed. Please restart the app or try again later.",
                            Toast.LENGTH_SHORT).show()
                            finish()
                    }
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

    fun createUserDB(firebaseData: DatabaseReference) {
        val user: List<UserModel> = mutableListOf(
            UserModel(auth.currentUser!!.uid, auth.currentUser!!.email)
        )

        user.forEach {
            val key = firebaseData.child("users").push().key
            it.uuid = key
            if (key != null) {
                firebaseData.child("users").child(key).setValue(it)
            }
        }
    }
        }









