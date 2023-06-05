package gw.gobpo2005.rickandmorty.main_page.api

import gw.gobpo2005.rickandmorty.main_page.api.model.CharacterDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("api/character")
    suspend fun getCharacterData(
        @Query("page") page: Int
    ): CharacterDataResponse
}