package gw.gobpo2005.rickandmorty.main_page.repository

import gw.gobpo2005.rickandmorty.main_page.db.dao.CharacterDao
import gw.gobpo2005.rickandmorty.main_page.db.model.DataBaseConverter
import gw.gobpo2005.rickandmorty.main_page.model.Hero
import gw.gobpo2005.rickandmorty.main_page.ui.model.HeroUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainLocalRepositoryImpl(
    private val characterDao: CharacterDao,
) : MainLocalRepository {
    override suspend fun getHeroes(): Flow<List<HeroUi>> {
        val heroes = characterDao.getAllCharacters()
        return heroes.map { DataBaseConverter.fromDataBase(it) }
    }

    override suspend fun insertHeroesToDb(heroes: List<Hero>) {
        val heroEntities = DataBaseConverter.toDataBase(heroes)
        characterDao.insertAll(heroEntities)
    }
}