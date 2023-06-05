package gw.gobpo2005.rickandmorty.main_page.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import gw.gobpo2005.rickandmorty.R
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
            statusOfCharacter.text = item.status
            speciesOfCharacter.text = item.species
            locationOfCharacter.text = item.location.name

            imageViewAvatar.load(item.image) {
                crossfade(true)
                placeholder(R.drawable.ic_rick_and_morty_avatar)
                transformations(CircleCropTransformation())
            }
        }
    }


}