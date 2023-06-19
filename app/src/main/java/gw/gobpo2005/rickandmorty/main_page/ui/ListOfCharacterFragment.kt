package gw.gobpo2005.rickandmorty.main_page.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import gw.gobpo2005.rickandmorty.R
import gw.gobpo2005.rickandmorty.common.mvvm.BaseFragment
import gw.gobpo2005.rickandmorty.databinding.FragmentListOfCharacterBinding
import gw.gobpo2005.rickandmorty.main_page.model.Hero
import gw.gobpo2005.rickandmorty.main_page.ui.adapter.CharactersAdapter
import gw.gobpo2005.rickandmorty.utils.ui.EndlessScrollListener
import gw.gobpo2005.rickandmorty.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListOfCharacterFragment : BaseFragment(R.layout.fragment_list_of_character) {

    private val viewModel: MainPageViewModel by viewModel()
    private val binding: FragmentListOfCharacterBinding by viewBinding()
    private val adapter by lazy { CharactersAdapter() }

    private val layoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(requireContext())
    }
    private val scrollListener: EndlessScrollListener by lazy {
        EndlessScrollListener(layoutManager) {
            viewModel.changePage(it)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onAttachRecycler()
        binding.recyclerOfCharacter.adapter = adapter
        binding.recyclerOfCharacter.addOnScrollListener(scrollListener)
        binding.editTextEmail.doAfterTextChanged { nameOfCharacter ->
            if (nameOfCharacter.isNullOrEmpty()) {
                viewModel.getHeroes()
            } else {
                nameOfCharacter.toString().let { viewModel.getDataName(it) }
            }
        }
        setObserves()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDetachRecycler()
        activity?.finish()
    }


    private fun onDetachRecycler() {
        with(binding) {
            recyclerOfCharacter.doOnDetach {
                recyclerOfCharacter.layoutManager = null
            }
        }
    }

    private fun onAttachRecycler() {
        with(binding) {
            recyclerOfCharacter.doOnAttach {
                recyclerOfCharacter.layoutManager = layoutManager
            }
        }
    }

    private fun setObserves() {
        observe(viewModel.heroesData) { heroes ->
            adapter.clearAndSetData(heroes)
        }
        observe(viewModel.isLoading) { loading ->
            binding.progressBar.isVisible = loading
        }
        observeNullable(viewModel.characterDataName) { characterName ->
            scrollListener.reset()
            characterName?.result?.let { it -> clearAndShowData(it) }
        }
        observeNullable(viewModel.characterDataName) { characterName ->
            scrollListener.reset()
            characterName?.result?.let { it -> clearAndShowData(it) }
        }
    }

    private fun showData(data: List<Hero>) {
        adapter.setData(data)
    }

    private fun clearAndShowData(data: List<Hero>) {
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
