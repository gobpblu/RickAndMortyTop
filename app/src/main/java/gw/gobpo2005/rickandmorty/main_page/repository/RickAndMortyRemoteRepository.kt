package gw.gobpo2005.rickandmorty.main_page.repository

import gw.gobpo2005.rickandmorty.main_page.api.RickAndMortyApi
import gw.gobpo2005.rickandmorty.main_page.model.CharacterData
import gw.gobpo2005.rickandmorty.main_page.model.Converter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class RickAndMortyRemoteRepository(
    private val api: RickAndMortyApi
) : RickAndMortyRepository {
    override suspend fun getData(page : Int): CharacterData {
        val response = api.getCharacterData(page)
        Timber.i("data --->>> $response")
        return Converter.fromNetwork(response)
    }
}