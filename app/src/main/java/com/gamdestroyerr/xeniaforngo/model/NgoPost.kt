package com.gamdestroyerr.xeniaforngo.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class NgoPost(
        val id: String = "",
        val authorUid: String = "",
        val authorUsername: String = "",
        @get:Exclude var authorProfilePicture: String = "",
        val ngo: String = "",
        val text: String = "",
        val imageUrl: String = "",
        val date: Long = 0L,
        var apartmentName: String = "",
        var wingNo: String = "",
        var flatNo: String = "",
        var phoneNumber: String = "",
)