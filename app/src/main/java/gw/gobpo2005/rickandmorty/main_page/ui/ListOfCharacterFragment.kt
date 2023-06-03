package gw.gobpo2005.rickandmorty.main_page.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.recyclerview.widget.LinearLayoutManager
import gw.gobpo2005.rickandmorty.utils.viewbinding.viewBinding
import gw.gobpo2005.rickandmorty.R
import gw.gobpo2005.rickandmorty.common.mvvm.BaseFragment
import gw.gobpo2005.rickandmorty.databinding.FragmentListOfCharacterBinding
import gw.gobpo2005.rickandmorty.main_page.model.ResultData
import gw.gobpo2005.rickandmorty.main_page.ui.adapter.CharactersAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListOfCharacterFragment : BaseFragment(R.layout.fragment_list_of_character) {

    private val viewModel: ManePageViewModel by viewModel()
    private val binding: FragmentListOfCharacterBinding by viewBinding()
    private val adapter by lazy { CharactersAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onAttachRecycler()
        binding.recyclerOfCharacter.adapter = adapter
//        viewModel.getData()
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
    }

    private fun showData(data: List<ResultData>) {
        adapter.setData(data)
    }

}
