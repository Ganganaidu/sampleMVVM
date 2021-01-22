package com.android.sampleporject.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.android.sampleporject.R
import com.android.sampleporject.base.view.BaseFragment
import com.android.sampleporject.databinding.MainFragmentBinding
import com.android.sampleporject.viewmodel.MainViewModel


class MainFragment() : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        binding.content.layoutManager = GridLayoutManager(context, 2)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.doSearch()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        val mSearchMenuItem = menu.findItem(R.id.search)
        val searchManager =
            activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = mSearchMenuItem.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        fromView(searchView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search ->
                return false
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fromView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.subject.onComplete()
                searchView.clearFocus()
                viewModel.doSearch()
                viewModel.searchLiveData.observe(viewLifecycleOwner, {
                    Log.d("data", "Test links ${it?.link}")
                    it.items?.let { response ->
                        binding.content.adapter = ImageListAdapter(response)
                    }
                })

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.subject.onNext(it) }
                return true
            }
        })
    }
}