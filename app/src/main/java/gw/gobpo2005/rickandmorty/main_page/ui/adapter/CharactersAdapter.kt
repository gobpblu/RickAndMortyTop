package gw.gobpo2005.rickandmorty.main_page.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gw.gobpo2005.rickandmorty.R
import gw.gobpo2005.rickandmorty.main_page.model.CharacterData
import gw.gobpo2005.rickandmorty.main_page.model.Hero
import gw.gobpo2005.rickandmorty.main_page.ui.model.HeroUi
import timber.log.Timber

class CharactersAdapter(
    private val clickOnCharacter: (HeroUi) -> Unit,
) : RecyclerView.Adapter<CharacterViewHolder>() {
    private val data = mutableListOf<HeroUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_characters, parent, false)
        return CharacterViewHolder(parent, clickOnCharacter)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val listItem = data[position]
        holder.onBind(listItem)
    }

    fun setData(items: List<HeroUi>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun clearAndSetData(items: List<HeroUi>) {
        data.clear()
        Timber.i("__adapter was cleared")
        data.addAll(items)
        notifyDataSetChanged()
    }
}