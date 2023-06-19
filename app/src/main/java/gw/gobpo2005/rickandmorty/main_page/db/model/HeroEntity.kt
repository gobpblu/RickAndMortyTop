package gw.gobpo2005.rickandmorty.main_page.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import gw.gobpo2005.rickandmorty.main_page.model.Hero


@Entity(
    tableName = "characters"
)
data class HeroEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "species")
    val species: String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "image")
    val image: String
) {

    fun toCharacter() = Hero(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )
}