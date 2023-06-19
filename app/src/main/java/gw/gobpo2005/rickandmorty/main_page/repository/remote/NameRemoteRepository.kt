package gw.gobpo2005.rickandmorty.main_page.repository.remote

import gw.gobpo2005.rickandmorty.main_page.api.RickAndMortyApi
import gw.gobpo2005.rickandmorty.main_page.model.CharacterData
import gw.gobpo2005.rickandmorty.main_page.model.converter.Converter
import timber.log.Timber

class NameRemoteRepository(
    private val api: RickAndMortyApi
) : NameRepository {

    override suspend fun getDataName(name: String): CharacterData {
        val response = api.getCharacterName(name)
        Timber.i("data --->>> $response")
        return Converter.fromNetwork(response)
    }
}