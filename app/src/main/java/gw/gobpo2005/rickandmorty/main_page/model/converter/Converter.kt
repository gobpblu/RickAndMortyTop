package gw.gobpo2005.rickandmorty.main_page.model.converter

import gw.gobpo2005.rickandmorty.main_page.api.model.*
import gw.gobpo2005.rickandmorty.main_page.model.*

object Converter {

    fun fromNetwork(response: CharacterDataResponse): CharacterData {
        return CharacterData(info = response.info?.let { fromNetwork(it) } ?: DataInfo(
            0, 0, "", ""
        ),
            result = fromNetworkList(response.results)
        )
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