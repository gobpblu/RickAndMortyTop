package gw.gobpo2005.rickandmorty.main_page.repository.remote

import gw.gobpo2005.rickandmorty.main_page.model.Hero

interface MainRepository {
    suspend fun getData(page : Int): List<Hero>
}