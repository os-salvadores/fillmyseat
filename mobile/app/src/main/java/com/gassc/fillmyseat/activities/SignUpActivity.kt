package com.gassc.fillmyseat.activities

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gassc.fillmyseat.R
import com.gassc.fillmyseat.constants.FontConstant
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //Custom Font
        tv_titleCreateAccount.typeface = Typeface.createFromAsset(assets, FontConstant.Path.FONT_PROXIMA_NOVA_BOLD)
    }

    override fun onStart() {
        super.onStart()

        // Create Register
        bt_register.setOnClickListener {
            val intent = Intent(this, HomeEventsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

}
