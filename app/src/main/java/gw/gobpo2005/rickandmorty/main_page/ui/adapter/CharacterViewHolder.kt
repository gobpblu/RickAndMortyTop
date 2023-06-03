package gw.gobpo2005.rickandmorty.main_page.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gw.gobpo2005.rickandmorty.databinding.ItemCharactersBinding
import gw.gobpo2005.rickandmorty.main_page.model.ResultData

class CharacterViewHolder(
    private val binding: ItemCharactersBinding
) : RecyclerView.ViewHolder(binding.root) {
    constructor(
        parent: ViewGroup
    ) : this(
        ItemCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun onBind(item: ResultData) {
        with(binding) {
            nameOfCharacter.text = item.name
        }
    }

}