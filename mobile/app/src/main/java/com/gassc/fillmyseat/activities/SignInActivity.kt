package com.gassc.fillmyseat.activities

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gassc.fillmyseat.R
import com.gassc.fillmyseat.constants.FontConstant
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // To Do...
        // Check if user is still logged
        // If logged then go to HomeScreen else go to Sign In Screen

        //Custom Font
        tv_title.typeface = Typeface.createFromAsset(assets, FontConstant.Path.FONT_PROXIMA_NOVA_BOLD)
    }

    override fun onStart() {
        super.onStart()

        // Forgot Password.
        tv_forgotPass.setOnClickListener {
            Toast.makeText(this,"Clicked: Esqueci minha senha.", Toast.LENGTH_SHORT).show()
        }

        // Sign In.
        bt_login.setOnClickListener {
            // To Do...
            // Input validation
            // Authentication
            val intent = Intent(this, HomeEventsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        // Sign Up.
        tv_register.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

    }
}
