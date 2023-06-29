package gw.gobpo2005.rickandmorty.main_page.di

import androidx.room.Room
import gw.gobpo2005.rickandmorty.common.di.InjectionModule
import gw.gobpo2005.rickandmorty.main_page.api.RickAndMortyApi
import gw.gobpo2005.rickandmorty.main_page.db.database.AppDataBase
import gw.gobpo2005.rickandmorty.main_page.repository.remote.MainRemoteRepositoryImpl
import gw.gobpo2005.rickandmorty.main_page.repository.remote.MainRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import gw.gobpo2005.rickandmorty.main_page.interactor.MainInteractor
import gw.gobpo2005.rickandmorty.main_page.repository.MainLocalRepository
import gw.gobpo2005.rickandmorty.main_page.repository.MainLocalRepositoryImpl
import gw.gobpo2005.rickandmorty.main_page.repository.remote.NameRemoteRepositoryImpl
import gw.gobpo2005.rickandmorty.main_page.repository.remote.NameRepository
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import gw.gobpo2005.rickandmorty.main_page.ui.MainPageViewModel
import gw.gobpo2005.rickandmorty.utils.Constants

object MainPageModule : InjectionModule {
    override fun onCreate() = module {
        single {
            Room.databaseBuilder(get(), AppDataBase::class.java, Constants.DATA_BASE_NAME)
                .build()
        }
        single { get<Retrofit>().create(RickAndMortyApi::class.java) }
        singleOf(::MainRemoteRepositoryImpl) bind MainRepository::class
        single { NameRemoteRepositoryImpl(get()) } bind NameRepository::class
        single { get<AppDataBase>().getCharactersDao() }
        single { MainLocalRepositoryImpl(get()) } bind MainLocalRepository::class
        factoryOf(::MainInteractor)
        factoryOf(::MainPageViewModel)

    }
}