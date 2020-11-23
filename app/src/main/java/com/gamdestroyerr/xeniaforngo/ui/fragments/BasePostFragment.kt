package com.gamdestroyerr.xeniaforngo.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.gamdestroyerr.xeniaforngo.R
import com.gamdestroyerr.xeniaforngo.ui.adapter.NgoPostAdapter
import com.gamdestroyerr.xeniaforngo.util.EventObserver
import com.gamdestroyerr.xeniaforngo.util.snackBar
import com.gamdestroyerr.xeniaforngo.viewmodels.BasePostViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

abstract class BasePostFragment(
        layoutId: Int
) : Fragment(layoutId) {

    @Inject
    lateinit var glide: RequestManager

    @Inject
    lateinit var ngoPostAdapter: NgoPostAdapter

    protected abstract val progressBarPost: ProgressBar

    protected abstract val basePostViewModel: BasePostViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObserver()

    }

    private fun subscribeToObserver() {
        basePostViewModel.posts.observe(viewLifecycleOwner, EventObserver(
                onError = {
                    progressBarPost.isVisible = false
                    Log.d("TAG", it)
                    snackBar(it)
                },
                onLoading = {
                    progressBarPost.isVisible = true
                },
        ) { postList ->
            progressBarPost.isVisible = false
            ngoPostAdapter.ngoPosts = postList
        })
    }
}