package gw.gobpo2005.rickandmorty.main_page.api.model

import com.google.gson.annotations.SerializedName

data class CharacterDataResponse(
    @SerializedName("info")
    val info : DataInfoResponse?,
    @SerializedName("results")
    val results: List<HeroResponse>
)