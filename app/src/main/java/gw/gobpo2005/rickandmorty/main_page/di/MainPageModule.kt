package gw.gobpo2005.rickandmorty.main_page.di

import gw.gobpo2005.rickandmorty.common.di.InjectionModule
import gw.gobpo2005.rickandmorty.main_page.api.RickAndMortyApi
import gw.gobpo2005.rickandmorty.main_page.repository.RickAndMortyRemoteRepository
import gw.gobpo2005.rickandmorty.main_page.repository.RickAndMortyRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import gw.gobpo2005.rickandmorty.main_page.interactor.RickAndMortyInteractor
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import gw.gobpo2005.rickandmorty.main_page.ui.MainPageViewModel

object MainPageModule : InjectionModule {
    override fun onCreate() = module {
        single { get<Retrofit>().create(RickAndMortyApi::class.java) }
        single<RickAndMortyRepository> { RickAndMortyRemoteRepository(get()) }
        singleOf(::RickAndMortyRemoteRepository) bind RickAndMortyRepository::class
        factoryOf(::RickAndMortyInteractor)
        factoryOf(::MainPageViewModel)

    }
}