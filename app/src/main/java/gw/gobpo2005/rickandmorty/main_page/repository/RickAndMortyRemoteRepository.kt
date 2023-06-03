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
    override suspend fun getData(): CharacterData {
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        val response = api.getCharacterData()
        Timber.i("data --->>> $response")
        return Converter.fromNetwork(response)
    }
}