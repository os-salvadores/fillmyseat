package com.gassc.fillmyseat.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gassc.fillmyseat.R
import com.gassc.fillmyseat.adapter.ParticipantAdapter
import com.gassc.fillmyseat.model.Participant
import kotlinx.android.synthetic.main.fragment_event_participants.view.*


class EventParticipantsFragment : Fragment() {

    private var mParticipants = mutableListOf<Participant>()
    private var mAdapter = ParticipantAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fill Participants
        mParticipants = participants().toMutableList()

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_event_participants, container, false)

        mAdapter.setContext(activity!!.applicationContext)
        mAdapter.setParticipants(mParticipants.sortedWith(compareBy { it.name }))

        // RecyclerView Participants
        view.rv_participants.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
            isFocusable = false
        }

        // Search Functionality
        view.et_search.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(text: Editable?) {
                filterParticipants(text.toString(), mAdapter)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        return view
    }

    fun getSelectedParticipants() : List<Participant>{
        return mAdapter.getSelectedParticipants()
    }

    private fun filterParticipants(text: String, adapter: ParticipantAdapter){
        val filteredParticipants = mutableListOf<Participant>()

        for(participant in mParticipants){
            if(participant.name.contains(text,true)){
                filteredParticipants.add(participant)
            }
        }
        adapter.filter(filteredParticipants)
    }


    private fun participants(): List<Participant> {
        return listOf(
            Participant(1,"Chris Evans",false),
            Participant(2,"Elizabeth Olsen", false),
            Participant(3,"Elsa Pataky", false),
            Participant(4,"Karen Gillan", false),
            Participant(5,"Keanu Reeves", false),

            Participant(6,"Mena Massoud",false),
            Participant(7,"Naomi Scott", false),
            Participant(8,"Will Smith", false),
            Participant(9,"Sophie Turner", false),
            Participant(10,"James McAvoy", false),

            Participant(11,"Michael Fassbender",false),
            Participant(12,"Jennifer Lawrence", false),
            Participant(13,"Jessica Chastain", false),
            Participant(14,"Taron Egerton", false),
            Participant(15,"Richard Madden", false),

            Participant(16,"Scarlett Johansson", false))
    }


}
