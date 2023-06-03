package gw.gobpo2005.rickandmorty.main_page.repository

import gw.gobpo2005.rickandmorty.main_page.model.CharacterData

interface RickAndMortyRepository {
    suspend fun getData(): CharacterData
}