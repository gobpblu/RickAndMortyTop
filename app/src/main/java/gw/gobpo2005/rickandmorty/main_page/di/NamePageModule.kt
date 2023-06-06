package gw.gobpo2005.rickandmorty.main_page.di

import gw.gobpo2005.rickandmorty.main_page.ui.ManePageViewModel
import gw.gobpo2005.rickandmorty.common.di.InjectionModule
import gw.gobpo2005.rickandmorty.main_page.api.RickAndMortyApi
import gw.gobpo2005.rickandmorty.main_page.interactor.RickAndMortyInteractor
import gw.gobpo2005.rickandmorty.main_page.repository.RickAndMortyRemoteRepositoryName
import gw.gobpo2005.rickandmorty.main_page.repository.RickAndMortyRepositoryName
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

object NamePageModule : InjectionModule {
    override fun onCreate() = module {
        single { get<Retrofit>().create(RickAndMortyApi::class.java) }
        single<RickAndMortyRepositoryName> { RickAndMortyRemoteRepositoryName(get()) }
        singleOf(::RickAndMortyRemoteRepositoryName) bind RickAndMortyRepositoryName::class
        factoryOf(::RickAndMortyInteractor)
        factoryOf(::ManePageViewModel)

    }
}