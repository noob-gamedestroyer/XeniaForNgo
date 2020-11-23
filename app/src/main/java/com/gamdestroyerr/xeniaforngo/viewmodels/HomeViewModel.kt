package com.gamdestroyerr.xeniaforngo.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gamdestroyerr.xeniaforngo.model.NgoPost
import com.gamdestroyerr.xeniaforngo.repositories.MainRepository
import com.gamdestroyerr.xeniaforngo.util.Event
import com.gamdestroyerr.xeniaforngo.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
        private val repository: MainRepository,
        private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BasePostViewModel(repository, dispatcher)  {

    private val _posts = MutableLiveData<Event<Resource<List<NgoPost>>>>()
    override val posts: LiveData<Event<Resource<List<NgoPost>>>>
        get() = _posts

    init {
        getNgoPosts()
    }

    override fun getNgoPosts(uid: String) {
        _posts.postValue(Event(Resource.Loading()))
        viewModelScope.launch(dispatcher) {
            val result = repository.getNgoPostForNgo()
            _posts.postValue(Event(result))
        }
    }
}