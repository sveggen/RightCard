package org.wit.rightcard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.Menu

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.btn_sign_up
import org.jetbrains.anko.AnkoLogger
import org.wit.rightcard.R
import org.wit.rightcard.persistence.models.UserModel

/**
 * Handles the login process.
 */
class LoginActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Init toolbar
        setSupportActionBar(findViewById(R.id.signed_out_toolbar))

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        btn_sign_up.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        btn_log_in.setOnClickListener{
            login()
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    /**
     * Logs user in if they provide the correct username and password.
     */
    private fun login() {
        if (loginusername.text.toString().isEmpty()) {
            loginusername.error = "Please enter an email address"
            loginusername.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(loginusername.text.toString()).matches()) {
            loginusername.error = "Please enter a valid email address"
            loginusername.requestFocus()
            return
        }
        if (loginpassword.text.toString().isEmpty()) {
            loginpassword.error = "Please enter a password"
            loginpassword.requestFocus()
            return
        }

        val userModel = UserModel(loginusername.text.toString(), loginpassword.text.toString())
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(userModel.email.toString(), userModel.password.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser? = auth.currentUser
                    updateUI(firebaseUser)
                } else {
                    updateUI(null)
                    loginpassword.error = "Login failed: Wrong username or password."
                    loginpassword.requestFocus()
                }
            }
    }

    /**
     * Start UserCardActivity if user is authenticated.
     */
     private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
                startActivity(Intent(this, UserCardActivity::class.java))
                finish()
        }
     }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.signed_out_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
