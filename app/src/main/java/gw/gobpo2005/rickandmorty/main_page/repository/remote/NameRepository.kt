package gw.gobpo2005.rickandmorty.main_page.repository.remote

import gw.gobpo2005.rickandmorty.main_page.model.CharacterData

interface NameRepository {
    suspend fun getDataName(name : String): CharacterData
}