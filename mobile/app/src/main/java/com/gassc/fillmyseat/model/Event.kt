package com.gassc.fillmyseat.model

import java.io.Serializable

data class Event (
    var codigo: String,
    var name: String,
    var date: String,
    var timeStart: String,
    var timeEnd: String,
    var location: String
) : Serializable