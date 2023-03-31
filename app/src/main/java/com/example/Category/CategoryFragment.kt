package com.example.Category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.Adapters.CategoryAdapter
import com.example.Favourite.FavouriteFragmentDirections
import com.example.foodreciepie.R
import com.example.foodreciepie.databinding.FragmentCategoryBinding
import com.example.home.HomeFragmentDirections
import com.example.home.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoryFragment : Fragment() {


    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding:FragmentCategoryBinding
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentCategoryBinding.inflate(layoutInflater)

        val bottomNavigationView = binding.bottomNavigation2 as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    val action= CategoryFragmentDirections.actionCategoryFragmentToHomeFragment()
                    findNavController().navigate(action)
                }
                R.id.favourite-> {
                    val action=CategoryFragmentDirections.actionCategoryFragmentToFavouriteFragment()
                    findNavController().navigate(action)
                }
                R.id.category -> {}
            }
            true
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.d("Category Fragment", " List")
        adapter= CategoryAdapter(){
            Toast.makeText(requireContext(),"Category clicked ${it.strCategory}", Toast.LENGTH_LONG).show()
            val action= CategoryFragmentDirections.actionCategoryFragmentToCategoryMealFragment(it.strCategory)
            findNavController().navigate(action)
        }
        val layoutManager= GridLayoutManager(requireContext(),3, GridLayoutManager.VERTICAL,false);
        binding.listView.adapter=adapter
        binding.listView.layoutManager=layoutManager

        viewModel.getCategoryList()
        viewModel.categoryListLive.observe(viewLifecycleOwner, Observer {
            Log.d("Category Fragment", " List ${it.categories.toString()}")
            (binding.listView.adapter as CategoryAdapter).submitList(it.categories)
        })
        super.onViewCreated(view, savedInstanceState)
    }


}