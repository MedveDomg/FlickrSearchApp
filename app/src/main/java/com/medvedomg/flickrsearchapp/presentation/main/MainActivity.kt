package com.medvedomg.flickrsearchapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.medvedomg.flickrsearchapp.databinding.ActivityMainBinding
import com.medvedomg.flickrsearchapp.presentation.spots.SearchFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.rootContainer)
    }

    override fun onStart() {
        super.onStart()
        viewModel.navigationActionLiveData.observe(this, Observer {
            supportFragmentManager.commit {
                val id = binding.rootContainer.id
                replace(id, SearchFragment())
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
