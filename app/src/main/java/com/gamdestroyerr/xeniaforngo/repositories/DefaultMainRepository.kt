package com.gamdestroyerr.xeniaforngo.repositories

import android.os.Bundle
import android.util.Log
import com.gamdestroyerr.xeniaforngo.model.NgoPost
import com.gamdestroyerr.xeniaforngo.model.NgoUser
import com.gamdestroyerr.xeniaforngo.util.Resource
import com.gamdestroyerr.xeniaforngo.util.safeCall
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class DefaultMainRepository : MainRepository {

    private val auth = FirebaseAuth.getInstance()
    private val fireStore = FirebaseFirestore.getInstance()
    private val ngoUsers = fireStore.collection("ngoUsers")
    private val ngoPosts = fireStore.collection("ngoPost")


    override suspend fun getNgoUsers(uids: List<String>) = withContext(Dispatchers.IO) {
        safeCall {
            val ngoUserList = ngoUsers.whereIn("uid", uids).orderBy("username").get().await()
                    .toObjects(NgoUser::class.java)
            Resource.Success(ngoUserList)
        }
    }

    override suspend fun getNgoUser(uid: String) = withContext(Dispatchers.IO) {
        safeCall {
            val ngoUser = ngoUsers.document(uid).get().await().toObject(NgoUser::class.java)
                    ?: throw IllegalStateException()
            Resource.Success(ngoUser)
        }
    }

    override suspend fun getNgoPostForNgo() = withContext(Dispatchers.IO) {
        safeCall {
            val docRef = ngoUsers.document(auth.uid!!.toString())
            var ngo = ""

            docRef.get().addOnSuccessListener {
                ngo = it?.getString("ngo").toString()
            }.addOnFailureListener {
                Log.d("TAG", "ID:TAG\t" + it.message.toString())
            }.await()

            val post = ngoPosts.whereEqualTo("ngo", ngo)
                    .orderBy("date", Query.Direction.DESCENDING)
                    .get()
                    .await()
                    .toObjects(NgoPost::class.java)
                    .onEach {

                    }

            Log.d("TAG", post.toString())
            Resource.Success(post)
        }
    }
}