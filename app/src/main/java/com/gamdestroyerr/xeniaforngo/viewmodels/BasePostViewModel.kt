package com.gamdestroyerr.xeniaforngo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gamdestroyerr.xeniaforngo.model.NgoPost
import com.gamdestroyerr.xeniaforngo.repositories.MainRepository
import com.gamdestroyerr.xeniaforngo.util.Event
import com.gamdestroyerr.xeniaforngo.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

abstract class BasePostViewModel(
        private val repository: MainRepository,
        private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel(){

    abstract val posts: LiveData<Event<Resource<List<NgoPost>>>>

    abstract fun getNgoPosts(uid: String = "")

}