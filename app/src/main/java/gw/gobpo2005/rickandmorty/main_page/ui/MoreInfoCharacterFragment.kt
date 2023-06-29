package gw.gobpo2005.rickandmorty.main_page.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import coil.load
import coil.transform.CircleCropTransformation
import gw.gobpo2005.rickandmorty.R
import gw.gobpo2005.rickandmorty.common.mvvm.BaseFragment
import gw.gobpo2005.rickandmorty.databinding.FragmentAdditinalyInfoHeroBinding
import gw.gobpo2005.rickandmorty.main_page.model.Hero
import gw.gobpo2005.rickandmorty.main_page.ui.model.HeroUi
import gw.gobpo2005.rickandmorty.utils.Constants
import gw.gobpo2005.rickandmorty.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoreInfoCharacterFragment : BaseFragment(R.layout.fragment_additinaly_info_hero) {


    companion object {
        fun newInstance(data: HeroUi) = MoreInfoCharacterFragment().apply {
            arguments = bundleOf(Constants.LIST_OF_CHARACTER to data)
        }
    }

    private val binding: FragmentAdditinalyInfoHeroBinding by viewBinding()

    private val data: HeroUi? by lazy {
        arguments?.getParcelable(Constants.LIST_OF_CHARACTER)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            with(binding){
                buttonBack.setOnClickListener {
                    requireActivity().supportFragmentManager.popBackStack()
                }
                nameOfCharacter.text = data?.name
                statusOfCharacter.text = data?.status
                speciesOfCharacter.text = data?.species
                locationOfCharacter.text = data?.name

                imageViewAvatar.load(data?.image) {
                    crossfade(true)
                    placeholder(R.drawable.ic_rick_and_morty_avatar)
                    transformations(CircleCropTransformation())
                }
            }
    }
}

