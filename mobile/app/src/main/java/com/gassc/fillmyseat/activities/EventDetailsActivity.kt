package com.gassc.fillmyseat.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.gassc.fillmyseat.R
import com.gassc.fillmyseat.model.Event
import kotlinx.android.synthetic.main.activity_event_details.*

class EventDetailsActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        setToolbarConfiguration()

        val eventDetail = intent.getSerializableExtra("infoEvent") as Event

        // Event Name.
        tv_eventName.text = eventDetail.name + " - " + eventDetail.codigo

        // Event Location.
        tv_location.text = eventDetail.location

        // Date. [start + end]
        tv_date.text = eventDetail.date + ", " + eventDetail.timeStart + " - " + eventDetail.timeEnd
    }

    override fun onStart() {
        super.onStart()

        bt_checkIn.setOnClickListener {
            Toast.makeText(this,"Check In realizado com sucesso!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun setToolbarConfiguration() {
        setSupportActionBar(tb_myEvents)
        supportActionBar!!.title = getString(R.string.tv_titleMyEvents)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
