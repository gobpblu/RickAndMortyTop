package gw.gobpo2005.rickandmorty.main_page.model.converter

import gw.gobpo2005.rickandmorty.main_page.api.model.*
import gw.gobpo2005.rickandmorty.main_page.model.DataInfo
import gw.gobpo2005.rickandmorty.main_page.model.Hero
import gw.gobpo2005.rickandmorty.main_page.model.Location
import gw.gobpo2005.rickandmorty.main_page.model.Origin

object MainConverter {

    fun fromNetwork(response: CharacterDataResponse): List<Hero> {
        return response.results.map {result ->
            Hero(
                id =  result.id ?: 0,
                name =  result.name ?: "",
                status =  result.status ?: "",
                species =  result.species ?: "",
                gender =  result.gender ?: "",
                image =  result.image ?: "",

            )
        }
    }

    private fun fromNetwork(response: DataInfoResponse): DataInfo {
        return DataInfo(
            count = response.count ?: 0,
            pages = response.pages ?: 0,
            next = response.next ?: "",
            prev = response.prev ?: ""
        )
    }

    private fun fromNetworkList(response: List<HeroResponse>?): List<Hero> {
        return response?.map { data ->
            Hero(
                id = data.id ?: 0,
                name = data.name ?: "",
                status = data.status ?: "",
                species = data.species ?: "",
                gender = data.gender ?: "",
                image = data.image ?: "",
            )
        } ?: emptyList()
    }

    private fun fromNetworkOrigin(response: OriginResponse) = Origin(
        name = response.name ?: "",
        url = response.url ?: ""
    )

    private fun fromNetworkLocation(response: LocationResponse) = Location(
        name = response.name ?: "",
        url = response.url ?: ""
    )

}