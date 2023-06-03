package gw.gobpo2005.rickandmorty.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import gw.gobpo2005.rickandmorty.R
import gw.gobpo2005.rickandmorty.databinding.ActivityMainBinding
import gw.gobpo2005.rickandmorty.main_page.ui.ListOfCharacterFragment
import gw.gobpo2005.rickandmorty.utils.replace

class RootActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replace(ListOfCharacterFragment(), containerId = R.id.container)
    }
}