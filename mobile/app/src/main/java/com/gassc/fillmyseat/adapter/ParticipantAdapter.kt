package com.gassc.fillmyseat.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.support.v7.widget.RecyclerView.Adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.gassc.fillmyseat.R
import com.gassc.fillmyseat.model.Participant
import kotlinx.android.synthetic.main.rv_layout_participants.view.*


class ParticipantAdapter() : Adapter<ParticipantAdapter.ViewHolder>() {

    //private var mParticipants = listOf<Participant>()
    private var mContext: Context? = null
    private var mParticipants: List<Participant>? = null
    private var mSelectedParticipants = mutableListOf<Participant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(mContext).inflate(com.gassc.fillmyseat.R.layout.rv_layout_participants, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mParticipants!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val participant = mParticipants!![position]

        holder.name.text = participant.name
        holder.checkbox.isChecked = false
        holder.checkbox.tag = participant

        holder.name.setOnClickListener {
            if (holder.checkbox.isChecked) {
                holder.checkbox.isChecked = false
                if (mSelectedParticipants.size > 0) {
                    mSelectedParticipants.remove(participant)
                }
            } else {// If checkbox is checked then add the Participant to the selected list
                holder.checkbox.isChecked = true
                mSelectedParticipants.add(participant)
            }
        }

        holder.checkbox.setOnClickListener {
            if (!holder.checkbox.isChecked) {
                if (mSelectedParticipants.size > 0) {
                    mSelectedParticipants.remove(participant)
                }
            } else {// If checkbox is checked then add the Participant to the selected list
                mSelectedParticipants.add(participant)
            }
        }

        if (mSelectedParticipants.size > 0) {
            if (mSelectedParticipants.contains(participant))
                holder.checkbox.isChecked = true
        }
    }

    fun filter(filteredPartipants: List<Participant>) {
        mParticipants = filteredPartipants
        notifyDataSetChanged()
    }

    fun getSelectedParticipants(): List<Participant> {
        return mSelectedParticipants
    }

    fun setContext(context: Context) {
        mContext = context
    }

    fun setParticipants(participants: List<Participant>) {
        mParticipants = participants
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var name: TextView = itemView.tv_nameParticipants
        internal var checkbox: CheckBox = itemView.cb_participants
    }
}