package gw.gobpo2005.rickandmorty.main_page.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataInfo(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
) : Parcelable