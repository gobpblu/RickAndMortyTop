package gw.gobpo2005.rickandmorty.main_page.api.model

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("name")
    val name : String?,
    @SerializedName("url")
    val url : String?
)