package gw.gobpo2005.rickandmorty.main_page.db.model

import gw.gobpo2005.rickandmorty.main_page.model.Hero
import gw.gobpo2005.rickandmorty.main_page.ui.model.HeroUi

object DataBaseConverter {
    fun fromDataBase(heroEntities: List<HeroEntity>) =
        heroEntities.map {
            HeroUi(
                name = it.name,
                status = it.status,
                species = it.species,
                gender = it.gender,
                image = it.image
            )
        }

    fun toDataBase(heroes: List<Hero>) =
        heroes.map {
            HeroEntity(
                id = it.id,
                name = it.name,
                status = it.status,
                species = it.species,
                gender = it.gender,
                image = it.image
            )
        }


}