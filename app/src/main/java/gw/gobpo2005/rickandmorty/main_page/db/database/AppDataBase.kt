package gw.gobpo2005.rickandmorty.main_page.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import gw.gobpo2005.rickandmorty.main_page.db.dao.CharacterDao
import gw.gobpo2005.rickandmorty.main_page.db.model.HeroEntity
import gw.gobpo2005.rickandmorty.main_page.model.CharacterData

@Database(version = 1, entities = [HeroEntity::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun getCharactersDao(): CharacterDao
}