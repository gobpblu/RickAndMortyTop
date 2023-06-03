package gw.gobpo2005.rickandmorty.main_page.model

import gw.gobpo2005.rickandmorty.main_page.api.model.*

object Converter {

    fun fromNetwork(response: CharacterDataResponse): CharacterData {
        return CharacterData(info = response.info?.let { fromNetwork(it) } ?: DataInfo(
            0, 0, "", ""
        ),
            result = fromNetworkList(response.result)
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

    private fun fromNetworkList(response: List<ResultDataResponse>?): List<ResultData> {
        return response?.map { data ->
            ResultData(
                id = data.id ?: 0,
                name = data.name ?: "",
                status = data.status ?: "",
                species = data.species ?: "",
                type = data.type ?: "",
                gender = data.gender ?: "",
                origin = data.origin?.let
                { fromNetworkOrigin(it) } ?: Origin("", ""),
                location = data.location?.let
                { fromNetworkLocation(it) } ?: Location("", ""),
                image = data.image ?: "",
                episode = data.episode,
                url = data.url ?: "",
                created = data.created ?: ""
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