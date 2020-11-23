package com.gamdestroyerr.xeniaforngo.repositories

import com.gamdestroyerr.xeniaforngo.model.NgoPost
import com.gamdestroyerr.xeniaforngo.model.NgoUser
import com.gamdestroyerr.xeniaforngo.util.Resource

interface MainRepository {

    suspend fun getNgoUsers(uids: List<String>): Resource<List<NgoUser>>

    suspend fun getNgoUser(uid: String): Resource<NgoUser>

    suspend fun getNgoPostForNgo(): Resource<List<NgoPost>>

}