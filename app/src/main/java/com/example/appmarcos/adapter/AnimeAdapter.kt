package com.example.appmarcos.adapter

import android.view.LayoutInflater
import android.view.TextView
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmarcos.R
import com.example.appmarcos.db.Anime

class AnimeAdapter(private val animeList: List<Anime>) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    inner class AnimeViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView = itemView.findViewById<TextView>(R.id.tvAnimeName)
        private val typeTextView = itemView.findViewById<TextView>(R.id.tvAnimeType)

        fun bind(anime: Anime) {
            nameTextView.text = anime.name
            typeTextView.text = anime.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_anime, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(animeList[position])
    }

    override fun getItemCount() = animeList.size
}
