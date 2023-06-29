package gw.gobpo2005.rickandmorty.main_page.ui.model

import gw.gobpo2005.rickandmorty.main_page.api.model.*
import gw.gobpo2005.rickandmorty.main_page.model.*

object MainConverterUi {

    fun fromNetwork(response: List<Hero>): List<HeroUi> {
        return response.map {result ->
            HeroUi(
                name =  result.name ,
                status =  result.status ,
                species =  result.species,
                gender =  result.gender ,
                image =  result.image ,

            )
        }
    }
}