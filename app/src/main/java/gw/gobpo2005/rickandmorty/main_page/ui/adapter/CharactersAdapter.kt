package gw.gobpo2005.rickandmorty.main_page.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gw.gobpo2005.rickandmorty.R
import gw.gobpo2005.rickandmorty.main_page.model.Hero

class CharactersAdapter : RecyclerView.Adapter<CharacterViewHolder>() {
    private val data = mutableListOf<Hero>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_characters, parent, false)
        return CharacterViewHolder(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val listItem = data[position]
        holder.onBind(listItem)
    }

    fun setData(items: List<Hero>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun clearAndSetData(items: List<Hero>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }
}