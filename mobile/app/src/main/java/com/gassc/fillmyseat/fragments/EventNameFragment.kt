package com.gassc.fillmyseat.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gassc.fillmyseat.R
import kotlinx.android.synthetic.main.fragment_event_name.view.*

class EventNameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_event_name, container, false)
        return view
    }


}
