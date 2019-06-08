package com.gassc.fillmyseat.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.support.v7.widget.RecyclerView.Adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.gassc.fillmyseat.R
import com.gassc.fillmyseat.activities.EventDetailsActivity
import com.gassc.fillmyseat.model.Event
import kotlinx.android.synthetic.main.rv_layout_events.view.*
import android.app.Activity




class EventAdapter(private var context: Context, events: List<Event>) : Adapter<EventAdapter.ViewHolder>() {

    private var mEvents: List<Event> = events

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(com.gassc.fillmyseat.R.layout.rv_layout_events, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mEvents.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = mEvents[position]

        holder.name.text = event.name + " - " + event.codigo
        holder.date.text = event.date + ", " + event.timeStart
        holder.location.text = event.location

        holder.parentLayout.setOnClickListener {
            val intent = Intent(context, EventDetailsActivity::class.java)
            intent.putExtra("infoEvent", event)
            context.startActivity(intent)
            (context as Activity).overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

//            context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

            //   val intent = Intent(activity!!.baseContext, ThirdActivity::class.java)
            //            intent.putExtra("infoEmprestimo", item)
            //            activity!!.startActivity(intent)
            //            this.activity!!.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var name: TextView = itemView.tv_nameEvent
        internal var date: TextView = itemView.tv_date
        internal var location: TextView = itemView.tv_location
        internal var parentLayout: LinearLayout = itemView.parentLayout
    }
}