package com.medvedomg.flickrsearchapp.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import com.medvedomg.flickrsearchapp.R
import com.medvedomg.flickrsearchapp.databinding.FragmentSearchBinding
import com.medvedomg.flickrsearchapp.presentation.image_details.ImageDetailsFragment
import com.medvedomg.flickrsearchapp.presentation.util.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private val viewModel by viewModel<SearchViewModel>()

    private lateinit var binding: FragmentSearchBinding

    private val adapter by lazy {
        ImageAdapter { imageModel ->
            requireActivity().supportFragmentManager.commit {
                val tag = ImageDetailsFragment::class.simpleName
                replace(
                    R.id.rootContainer,
                    ImageDetailsFragment.newInstance(imageModel),
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
            val lm = GridLayoutManager(requireContext(), 2)
            layoutManager = lm
        }
        with(binding.search) {
            isSubmitButtonEnabled = true
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.loadData(query.toString())
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.orEmpty().isNotBlank()) {
                        viewModel.startLoading()
                    }
                    return false
                }
            })
        }
        viewModel.viewStateLiveData.observe(viewLifecycleOwner) {
            setState(it)
        }
    }

    private fun setState(state: ViewState<List<ImageModel>>) {
        with(binding) {
            when (state) {
                is ViewState.Success -> {
                    search.setQuery("", false)
                    search.clearFocus()
                    loader.visibility = View.GONE
                    tvError.visibility = View.GONE
                    adapter.setData(state.data)
                    recyclerView.visibility = View.VISIBLE
                }
                is ViewState.Error -> {
                    search.setQuery("", false)
                    search.clearFocus()
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