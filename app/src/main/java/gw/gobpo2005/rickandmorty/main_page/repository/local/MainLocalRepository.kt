package gw.gobpo2005.rickandmorty.main_page.repository.local

import gw.gobpo2005.rickandmorty.main_page.db.dao.CharacterDao
import gw.gobpo2005.rickandmorty.main_page.db.model.DataBaseConverter
import gw.gobpo2005.rickandmorty.main_page.model.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainLocalRepository(
    private val characterDao: CharacterDao,
) : LocalRepository {
    override suspend fun getHeroes(): Flow<List<Hero>> {
        val heroes = characterDao.getAllCharacters()
        return heroes.map { DataBaseConverter.fromDataBase(it) }
    }

    override suspend fun insertHeroesToDb(heroes: List<Hero>) {
        val heroEntities = DataBaseConverter.toDataBase(heroes)
        characterDao.insertAll(heroEntities)
    }
}