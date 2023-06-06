package gw.gobpo2005.rickandmorty.main_page.repository

import gw.gobpo2005.rickandmorty.main_page.api.RickAndMortyApi
import gw.gobpo2005.rickandmorty.main_page.model.CharacterData
import gw.gobpo2005.rickandmorty.main_page.model.Converter
import timber.log.Timber

class RickAndMortyRemoteRepositoryName(
    private val api: RickAndMortyApi
) : RickAndMortyRepositoryName {

    override suspend fun getDataName(name: String): CharacterData {
        val response = api.getCharacterName(name)
        Timber.i("data --->>> $response")
        return Converter.fromNetwork(response)
    }
}