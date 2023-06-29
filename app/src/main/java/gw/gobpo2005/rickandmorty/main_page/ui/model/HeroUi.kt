package gw.gobpo2005.rickandmorty.main_page.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroUi(
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
) : Parcelable