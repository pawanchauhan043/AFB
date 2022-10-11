package com.example.afb

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.afb.data.Meme
import com.example.afb.databinding.RvItemBinding


class AfbAdapter(private val items: MutableList<Meme>) :
    RecyclerView.Adapter<AfbAdapter.AfbViewHolder>() {

    class AfbViewHolder(private val view: RvItemBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(meme: Meme) {
            Glide.with(view.root.context)
                .load(meme.url)
                .placeholder(R.drawable.btn_dialog)
                .error(R.drawable.btn_dialog)
                .centerCrop()
                .into(view.memeImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AfbViewHolder {

        return AfbViewHolder(
            RvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AfbViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun addMemes(memes: List<Meme>) {
        this.items.addAll(memes)
        notifyDataSetChanged()
    }

}
