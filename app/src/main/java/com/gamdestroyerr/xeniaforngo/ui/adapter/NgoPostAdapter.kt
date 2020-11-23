package com.gamdestroyerr.xeniaforngo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.gamdestroyerr.xeniaforngo.R
import com.gamdestroyerr.xeniaforngo.databinding.ItemNgoPostBinding
import com.gamdestroyerr.xeniaforngo.model.NgoPost
import javax.inject.Inject

class NgoPostAdapter @Inject constructor(
        private val glide: RequestManager
) : RecyclerView.Adapter<NgoPostAdapter.NgoPostViewHolder>() {

    class NgoPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemNgoPostBinding = ItemNgoPostBinding.bind(itemView)

        val ivPostImage = itemNgoPostBinding.ivPostImage
        val tvPostAuthor = itemNgoPostBinding.tvPostAuthor
        val tvPostText = itemNgoPostBinding.tvPostText
        val tvApartmentText = itemNgoPostBinding.tvApartmentText
        val tvWingNoText = itemNgoPostBinding.tvWingNoText
        val tvFlatNoText = itemNgoPostBinding.tvFlatNoText
        val tvPhoneNoText = itemNgoPostBinding.tvPhoneNoText
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<NgoPost>() {
        override fun areContentsTheSame(oldItem: NgoPost, newItem: NgoPost): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areItemsTheSame(oldItem: NgoPost, newItem: NgoPost): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    var ngoPosts: List<NgoPost>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NgoPostViewHolder {
        return NgoPostViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_ngo_post,
                        parent,
                        false,
                )
        )
    }

    override fun onBindViewHolder(holder: NgoPostViewHolder, position: Int) {
        val ngoPost = ngoPosts[position]
        holder.apply {
            glide.load(ngoPost.imageUrl).into(ivPostImage)
            tvPostAuthor.text = ngoPost.authorUsername
            tvPostText.text = ngoPost.text
            tvApartmentText.text = ngoPost.apartmentName
            tvWingNoText.text = ngoPost.wingNo
            tvFlatNoText.text = ngoPost.flatNo
            tvPhoneNoText.text = ngoPost.phoneNumber
        }
    }

    override fun getItemCount(): Int {
        return ngoPosts.size
    }

}