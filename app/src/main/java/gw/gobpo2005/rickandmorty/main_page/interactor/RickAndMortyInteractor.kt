package gw.gobpo2005.rickandmorty.main_page.interactor

import gw.gobpo2005.rickandmorty.main_page.model.CharacterData
import gw.gobpo2005.rickandmorty.main_page.repository.RickAndMortyRemoteRepository

class RickAndMortyInteractor(
    private val remoteRepository: RickAndMortyRemoteRepository
) {
    suspend fun getData(): CharacterData {
        return remoteRepository.getData()
    }
}