package gw.gobpo2005.rickandmorty.utils.ui

sealed class PagingState {
    object Idle : PagingState()
    object Loading : PagingState()
    object InitialLoading : PagingState()
    object Refreshing : PagingState()
    class Error(val e: Throwable) : PagingState()

    fun isLoading(): Boolean {
        return this is Loading || this is Refreshing || this is InitialLoading
    }
}
