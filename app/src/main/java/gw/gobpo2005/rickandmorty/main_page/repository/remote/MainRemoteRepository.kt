package gw.gobpo2005.rickandmorty.main_page.repository.remote

import gw.gobpo2005.rickandmorty.main_page.api.RickAndMortyApi
import gw.gobpo2005.rickandmorty.main_page.model.Hero
import gw.gobpo2005.rickandmorty.main_page.model.converter.MainConverter

class MainRemoteRepository(
    private val api: RickAndMortyApi,
) : MainRepository {
    override suspend fun getData(page: Int): List<Hero> {
        val data = api.getCharacterData(page)
        return MainConverter.fromNetwork(data)
    }
}