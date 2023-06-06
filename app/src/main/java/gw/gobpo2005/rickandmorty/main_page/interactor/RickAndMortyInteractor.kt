package gw.gobpo2005.rickandmorty.main_page.interactor

import gw.gobpo2005.rickandmorty.main_page.model.CharacterData
import gw.gobpo2005.rickandmorty.main_page.repository.RickAndMortyRemoteRepository
import gw.gobpo2005.rickandmorty.main_page.repository.RickAndMortyRemoteRepositoryName

class RickAndMortyInteractor(
    private val remoteRepository: RickAndMortyRemoteRepository,
    private val remoteRepositoryName: RickAndMortyRemoteRepositoryName
) {
    suspend fun getData(page: Int): CharacterData {
        return remoteRepository.getData(page)
    }

    suspend fun getDataName(name: String): CharacterData {
        return remoteRepositoryName.getDataName(name)
    }
}