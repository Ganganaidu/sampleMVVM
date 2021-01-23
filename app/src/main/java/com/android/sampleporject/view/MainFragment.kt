package com.android.sampleporject.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.android.sampleporject.base.view.BaseFragment
import com.android.sampleporject.databinding.FragmentMainBinding
import com.android.sampleporject.model.Item
import com.android.sampleporject.view.adapters.ImageListAdapter
import com.android.sampleporject.view.adapters.OnItemClickListener
import com.android.sampleporject.viewmodel.MainViewModel

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        binding.recycleView.layoutManager = GridLayoutManager(context, 2)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.doSearch()
        observeData()
    }

    private fun observeData() {
        viewModel.searchLiveData.observe(viewLifecycleOwner, {
            it.items?.let { response ->
                if (response.isNotEmpty()) {
                    setAdapter(response)
                } else {
                    showMessage(true)
                }
            } ?: kotlin.run {
                showMessage(true)
            }
        })
        viewModel.loadingLiveObservable.observe(viewLifecycleOwner, {
            showProgressBar(it)
        })
    }

    private fun setAdapter(data: List<Item>) {
        binding.recycleView.adapter = ImageListAdapter(data, object : OnItemClickListener {
            override fun onItemClicked(item: Item) {
                Intent(activity, DetailActivity::class.java).apply {
                    putExtra("item", item)
                    startActivity(this)
                }
            }
        })
    }

    private fun showProgressBar(visibility: Boolean) {
        if (visibility) {
            binding.progressBar.visibility = View.VISIBLE
            binding.recycleView.visibility = View.GONE
            showMessage(false)
        } else {
            binding.progressBar.visibility = View.GONE
            binding.recycleView.visibility = View.VISIBLE
        }
    }

    private fun showMessage(visibility: Boolean) {
        if (visibility) {
            binding.progressBar.visibility = View.GONE
            binding.messageView.visibility = View.VISIBLE
            binding.recycleView.visibility = View.GONE
        } else {
            binding.messageView.visibility = View.GONE
        }
    }
}