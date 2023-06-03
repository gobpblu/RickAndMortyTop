package gw.gobpo2005.rickandmorty.main_page.api

import gw.gobpo2005.rickandmorty.main_page.api.model.CharacterDataResponse
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("api/character")
    suspend fun getCharacterData(): CharacterDataResponse
}