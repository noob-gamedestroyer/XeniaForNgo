package com.gamdestroyerr.xeniaforngo.repositories

import com.gamdestroyerr.xeniaforngo.model.NgoUser
import com.gamdestroyerr.xeniaforngo.util.Resource
import com.gamdestroyerr.xeniaforngo.util.safeCall
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DefaultAuthRepository: AuthRepository {

    private val auth = FirebaseAuth.getInstance()
    private val ngoUsers = FirebaseFirestore.getInstance().collection("ngoUsers")

    override suspend fun register(
            email: String,
            username: String,
            password: String,
            phoneNumber: String,
            ngo: String,
            regNumber: String,
            address: String,
    ): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val uid = result.user?.uid!!
                val ngoUser = NgoUser(
                        uid,
                        username,
                        phoneNumber = phoneNumber,
                        ngo = ngo,
                        regNumber = regNumber,
                        address = address,
                )
                ngoUsers.document(uid).set(ngoUser).await()
                Resource.Success(result)
            }
        }
    }

    override suspend fun login(
            email: String,
            password: String
    ): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                Resource.Success(result)
            }
        }
    }

}