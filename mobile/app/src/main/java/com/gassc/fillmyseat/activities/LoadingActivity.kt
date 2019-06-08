package com.gassc.fillmyseat.activities

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gassc.fillmyseat.R
import com.gassc.fillmyseat.constants.FontConstant
import kotlinx.android.synthetic.main.activity_loading.*

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        //Custom Font
        tv_AppName.typeface = Typeface.createFromAsset(assets, FontConstant.Path.FONT_PROXIMA_NOVA_BOLD)

        // Delay to load
        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(3000)
                    val intent = Intent(baseContext, SignInActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}
