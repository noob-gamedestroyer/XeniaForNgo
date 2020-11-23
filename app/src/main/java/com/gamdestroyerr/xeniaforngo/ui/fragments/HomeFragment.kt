package com.gamdestroyerr.xeniaforngo.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamdestroyerr.xeniaforngo.R
import com.gamdestroyerr.xeniaforngo.databinding.FragmentHomeBinding
import com.gamdestroyerr.xeniaforngo.viewmodels.BasePostViewModel
import com.gamdestroyerr.xeniaforngo.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BasePostFragment(R.layout.fragment_home) {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var navController: NavController

    override val progressBarPost: ProgressBar
        get() {
            homeBinding = FragmentHomeBinding.bind(requireView())
            return homeBinding.allPostProgressBar
        }
    override val basePostViewModel: BasePostViewModel
        get() {
            val vm : HomeViewModel by viewModels()
            return vm
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        homeBinding = FragmentHomeBinding.bind(view)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() = homeBinding.feedRecyclerView.apply {
        adapter = ngoPostAdapter
        layoutManager = LinearLayoutManager(requireContext())
        itemAnimator = null
    }
}