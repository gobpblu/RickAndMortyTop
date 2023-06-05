package gw.gobpo2005.rickandmorty.main_page.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import gw.gobpo2005.rickandmorty.R
import gw.gobpo2005.rickandmorty.common.mvvm.BaseFragment
import gw.gobpo2005.rickandmorty.databinding.FragmentListOfCharacterBinding
import gw.gobpo2005.rickandmorty.main_page.model.ResultData
import gw.gobpo2005.rickandmorty.main_page.ui.adapter.CharactersAdapter
import gw.gobpo2005.rickandmorty.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListOfCharacterFragment : BaseFragment(R.layout.fragment_list_of_character) {

    private val viewModel: ManePageViewModel by viewModel()
    private val binding: FragmentListOfCharacterBinding by viewBinding()
    private val adapter by lazy { CharactersAdapter() }
    var page: Int = 1


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onAttachRecycler()
        binding.recyclerOfCharacter.adapter = adapter
        viewModel.getData(page)
        setOnClickListener()
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
                recyclerOfCharacter.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun setObserves() {
        observeNullable(viewModel.characterData) { character ->
            character?.result?.let { it -> showData(it) }
        }
        observe(viewModel.isLoading) { loading ->
            binding.progressBar.isVisible = loading
        }
    }

    private fun showData(data: List<ResultData>) {
        adapter.setData(data)
    }

    private fun setOnClickListener() {
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

}
