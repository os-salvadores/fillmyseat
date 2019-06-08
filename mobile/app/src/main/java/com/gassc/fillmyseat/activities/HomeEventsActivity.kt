package com.gassc.fillmyseat.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.gassc.fillmyseat.R
import com.gassc.fillmyseat.adapter.EventAdapter
import com.gassc.fillmyseat.constants.FontConstant
import com.gassc.fillmyseat.model.Event
import com.gassc.fillmyseat.utility.SharedPreferenceUtility
import kotlinx.android.synthetic.main.activity_home_events.*

class HomeEventsActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_events)

        tv_welcomeUser.text = resources.getString(R.string.tv_welcomeUser) + " Gabriel" + "!"

        //Custom Font
        tv_titleMyEvents.typeface = Typeface.createFromAsset(assets, FontConstant.Path.FONT_PROXIMA_NOVA_BOLD)

        // Check if user has events
        // If he does than show the list of events else show message of no events found
        val preference = SharedPreferenceUtility(this)

        if(preference.isEmptyEvent()){ // Show noEvents art
            ll_noEventContainer.visibility = View.VISIBLE
            rv_events.visibility = View.GONE
        }else{ // Show events list
            ll_noEventContainer.visibility = View.GONE
            rv_events.visibility = View.VISIBLE

            rv_events.apply {
                layoutManager = LinearLayoutManager(this@HomeEventsActivity)
                adapter = EventAdapter(this@HomeEventsActivity, events())
                isFocusable = false
            }
        }
    }

    override fun onStart() {
        super.onStart()

        bt_clearPreferences.setOnClickListener {
            // Clear events.
            val preference = SharedPreferenceUtility(this)
            preference.clearAll()

            // Refresh screen.
            val intent = Intent(this, HomeEventsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        fab_newEvent.setOnClickListener {
            val intent = Intent(this, CreateEventActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    private fun events() : List<Event>{
        return listOf(
            Event("LPE01|0008", "Design de App", "15 JUN", "09:00", "17:00", "UCB - R01B"),
            Event("LPE01|0009", "Projeto de App", "8 JUN", "09:00", "17:00", "UCB - C002")
        )
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
