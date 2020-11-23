package com.gamdestroyerr.xeniaforngo.repositories

import com.gamdestroyerr.xeniaforngo.util.Resource
import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun register(
            email: String,
            username: String,
            password: String,
            phoneNumber: String,
            ngo: String,
            regNumber: String,
            address: String,
    ): Resource<AuthResult>

    suspend fun login(email: String, password: String): Resource<AuthResult>
}