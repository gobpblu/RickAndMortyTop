package gw.gobpo2005.rickandmorty.main_page.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

import androidx.room.Query
import gw.gobpo2005.rickandmorty.main_page.db.model.HeroEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query(value = "SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<HeroEntity>>


    @Insert(entity = HeroEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(characters: List<HeroEntity>)
}
