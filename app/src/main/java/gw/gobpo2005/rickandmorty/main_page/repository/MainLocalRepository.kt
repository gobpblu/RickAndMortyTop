package gw.gobpo2005.rickandmorty.main_page.repository

import gw.gobpo2005.rickandmorty.main_page.model.Hero
import gw.gobpo2005.rickandmorty.main_page.ui.model.HeroUi
import kotlinx.coroutines.flow.Flow

interface MainLocalRepository {

    suspend fun getHeroes(): Flow<List<HeroUi>>

    suspend fun insertHeroesToDb(heroes: List<Hero>)
}