package org.wit.rightcard.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.btn_sign_up
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
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

        btn_user_exists.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
        private fun userSignUp() {
            if (signupusername.text.toString().isEmpty()) {
                signupusername.error = "Please enter an email address"
                signupusername.requestFocus()
                return
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(signupusername.text.toString()).matches()) {
                signupusername.error = "Please enter a valid email address"
                signupusername.requestFocus()
                return
            }
            if (signuppassword.text.toString().isEmpty()) {
                signuppassword.error = "Please enter a password"
                signuppassword.requestFocus()
                return
            }

            val userModel = UserModel(signupusername.text.toString(), signuppassword.text.toString())
            auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(userModel.email.toString(), userModel.password.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        info("User created")
                        startActivity(Intent(this,LoginActivity::class.java))
                        finish()
                    } else {
                        info("User not created")
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
        }









