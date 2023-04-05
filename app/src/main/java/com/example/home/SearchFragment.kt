package com.example.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Adapters.MostPopularAdapter
import com.example.foodreciepie.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding:FragmentSearchBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: MostPopularAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()

        binding.imgSearch.setOnClickListener {
            searchMeals()
        }

        observeLiveData()
        var searchJob:Job?=null
        binding.edSearchBox.addTextChangedListener{
            searchJob?.cancel()
            searchJob=lifecycleScope.launch {
                viewModel.searchMeal(it.toString())
            }
        }



    }

    private fun observeLiveData() {
        viewModel.searchListLive.observe(viewLifecycleOwner, Observer {
            (binding.rvSearchList.adapter as MostPopularAdapter).submitList(it.meals)
        })
    }

    private fun searchMeals() {
        val searchQuery=binding.edSearchBox.text.toString()
        Log.d("Search Fragment", " Search Query is as follows ${searchQuery} ")

        if(searchQuery.isNotEmpty()){
            viewModel.searchMeal(searchQuery)

            viewModel.searchListLive.observe(viewLifecycleOwner, Observer {
                Log.d("Search Fragment",  it.meals.toString())
                (binding.rvSearchList.adapter as MostPopularAdapter).submitList(it.meals)
            })

        }
    }

    private fun prepareRecyclerView() {
        adapter= MostPopularAdapter {
            val action=SearchFragmentDirections.actionSearchFragmentToDescriptionFragment2(it.idMeal)
            findNavController().navigate(action)
        }
        binding.rvSearchList.adapter=adapter
        binding.rvSearchList.layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
    }

}