package gw.gobpo2005.rickandmorty.main_page.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import gw.gobpo2005.rickandmorty.R
import gw.gobpo2005.rickandmorty.common.mvvm.BaseFragment
import gw.gobpo2005.rickandmorty.databinding.FragmentListOfCharacterBinding
import gw.gobpo2005.rickandmorty.main_page.ui.adapter.CharactersAdapter
import gw.gobpo2005.rickandmorty.main_page.ui.model.HeroUi
import gw.gobpo2005.rickandmorty.utils.replace
import gw.gobpo2005.rickandmorty.utils.ui.EndlessScrollListener
import gw.gobpo2005.rickandmorty.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class ListOfCharacterFragment : BaseFragment(R.layout.fragment_list_of_character) {

    private val viewModel: MainPageViewModel by viewModel()
    private val binding: FragmentListOfCharacterBinding by viewBinding()
    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter { item ->
            replace(MoreInfoCharacterFragment.newInstance(item))
        }

    }

    private lateinit var layoutManager: LinearLayoutManager

    private val scrollListener: EndlessScrollListener by lazy {
        EndlessScrollListener(layoutManager) {
            viewModel.changePage(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            layoutManager = LinearLayoutManager(requireContext())


            recyclerOfCharacter.adapter = adapter
            recyclerOfCharacter.addOnScrollListener(scrollListener)
            swr.setOnRefreshListener {
                scrollListener.reset()
                viewModel.loadHero(1)
            }
            editTextEmail.doAfterTextChanged { nameOfCharacter ->
                    nameOfCharacter.toString().let { viewModel.getDataName(it) }
            }
            setObserves()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("__On destroy")
        activity?.finish()

    }

    override fun onPause() {
        super.onPause()
        binding.recyclerOfCharacter.layoutManager = null
        Timber.i("__On pause")
    }

    override fun onStart() {
        super.onStart()
        binding.recyclerOfCharacter.layoutManager = layoutManager
        Timber.i("__On start")
    }


    private fun setObserves() {
        with(viewModel) {
            observe(heroesData) { heroes ->
                adapter.clearAndSetData(heroes)
            }
            observe(isLoading) { loading ->
                binding.progressBar.isVisible = loading
                binding.swr.isRefreshing = loading
            }
            observe(characterDataName) { characterName ->
                scrollListener.reset()
                clearAndShowData(characterName)
            }
//        observeNullable(viewModel.characterDataName) { characterName ->
//            scrollListener.reset()
//            characterName?.result?.let { it -> clearAndShowData(it) }
//        }
        }
    }

    private fun showData(data: List<HeroUi>) {
        adapter.setData(data)
    }

    private fun clearAndShowData(data: List<HeroUi>) {
        adapter.clearAndSetData(data)
    }

    /*private fun setOnClickListener() {
        with(binding) {
            buttonNext.setOnClickListener {
                page++
                viewModel.getData(page)
                Toast.makeText(requireContext(), "Great!!!!!!$page", Toast.LENGTH_SHORT)
                    .show()

            }
            buttonPrevious.setOnClickListener {
                if (page == 1) {
                    Toast.makeText(
                        requireContext(),
                        "Error, this first page $page",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                if (page != 1) {
                    page--
                    viewModel.getData(page)

                    Toast.makeText(requireContext(), "Great!!!!!!$page", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
*/
}
