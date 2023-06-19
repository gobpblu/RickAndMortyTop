package gw.gobpo2005.rickandmorty.main_page.repository.local

import gw.gobpo2005.rickandmorty.main_page.model.Hero
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun getHeroes(): Flow<List<Hero>>

    suspend fun insertHeroesToDb(heroes: List<Hero>)
}