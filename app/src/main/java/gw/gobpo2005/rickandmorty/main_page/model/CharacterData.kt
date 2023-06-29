package gw.gobpo2005.rickandmorty.main_page.model

import android.os.Parcelable
import gw.gobpo2005.rickandmorty.main_page.ui.model.HeroUi
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterData(
    val info : DataInfo,
    val result: List<Hero>
) : Parcelable