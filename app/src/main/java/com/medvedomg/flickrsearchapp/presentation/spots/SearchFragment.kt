package com.medvedomg.flickrsearchapp.presentation.spots

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.medvedomg.flickrsearchapp.R
import com.medvedomg.flickrsearchapp.databinding.FragmentSearchBinding
import com.medvedomg.flickrsearchapp.presentation.image_details.ImageDetailsFragment
import com.medvedomg.flickrsearchapp.presentation.util.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private val viewModel by viewModel<SearchViewModel>()

    private lateinit var binding: FragmentSearchBinding

    private val adapter by lazy {
        ImageAdapter { spotModel ->
            requireActivity().supportFragmentManager.commit {
                val tag = ImageDetailsFragment::class.simpleName
                replace(
                    R.id.rootContainer,
                    ImageDetailsFragment.newInstance(spotModel),
                    tag
                )
                addToBackStack(tag)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.recyclerView) {
            adapter = this@SearchFragment.adapter
            val lm = LinearLayoutManager(requireContext())
            layoutManager = lm
            val dividerItemDecoration = DividerItemDecoration(
                context,
                lm.orientation
            )
            addItemDecoration(dividerItemDecoration)
        }
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.loadData(newText.toString())
                return false
            }
        })
        viewModel.viewStateLiveData.observe(viewLifecycleOwner) {
            setState(it)
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = "Search"
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun setState(state: ViewState<List<ImageModel>>) {
        with(binding) {

            when (state) {
                is ViewState.Success -> {
                    loader.visibility = View.GONE
                    tvError.visibility = View.GONE
                    adapter.setData(state.data)
                    recyclerView.visibility = View.VISIBLE
                }
                is ViewState.Error -> {
                    loader.visibility = View.GONE
                    tvError.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    tvError.text = state.errorMessage
                }
                is ViewState.Loading -> {
                    loader.visibility = View.VISIBLE
                    tvError.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    adapter.setData(emptyList())
                }
            }
        }
    }
}