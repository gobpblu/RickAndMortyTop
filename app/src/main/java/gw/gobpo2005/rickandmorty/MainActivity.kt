package gw.gobpo2005.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import gw.gobpo2005.rickandmorty.databinding.ActivityMain2Binding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMain2Binding by lazy {
        ActivityMain2Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        replace(ListOfCharacterFragment(), containerId = R.id.container2)
    }
}