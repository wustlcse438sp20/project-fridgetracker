package com.example.fridgetracker.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ReceiptImage(
    var url: String = ""
)