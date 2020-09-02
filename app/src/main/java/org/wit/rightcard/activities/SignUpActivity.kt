package org.wit.rightcard.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.btn_sign_up
import org.jetbrains.anko.AnkoLogger
import org.wit.rightcard.R
import org.wit.rightcard.persistence.models.UserModel

/**
 * Handles the sign up page, and handles the creation of a new user.
 */
class SignUpActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        //Init toolbar
        setSupportActionBar(findViewById(R.id.signed_out_toolbar))
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
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
            // checks if password contains: One uppercase + one lowercase letter
            // + one number and is between 6 and 16 characters long
            if (!"^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,16}".toRegex().matches(signuppassword.text.toString())){
                signuppassword.error = "Please enter a password that has:\nOne uppercase and " +
                        "lowercase letter, and one number and is at least 6 characters long. "
                signuppassword.requestFocus()
                return
            }

            val userModel = UserModel(signupusername.text.toString(), signuppassword.text.toString())
            auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(userModel.email.toString(), userModel.password.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this,LoginActivity::class.java))
                        finish()
                    } else {
                        signupusername.error = "User already exists"
                        signupusername.requestFocus()
                    }
                }
        }

            override fun onCreateOptionsMenu(menu: Menu?): Boolean {
                menuInflater.inflate(R.menu.signed_out_menu, menu)
                return super.onCreateOptionsMenu(menu)
            }
        }