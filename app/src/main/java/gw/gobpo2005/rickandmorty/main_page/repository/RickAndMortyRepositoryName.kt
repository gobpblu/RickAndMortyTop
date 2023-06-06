package gw.gobpo2005.rickandmorty.main_page.repository

import gw.gobpo2005.rickandmorty.main_page.model.CharacterData

interface RickAndMortyRepositoryName {
    suspend fun getDataName(name : String): CharacterData
}