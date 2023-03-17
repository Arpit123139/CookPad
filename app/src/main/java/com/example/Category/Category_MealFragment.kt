package com.example.Category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.MealResponse
import com.example.foodreciepie.databinding.FragmentCategoryMealBinding
import com.example.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Category_MealFragment : Fragment() {

    private lateinit var binding:FragmentCategoryMealBinding
    private val viewModel: Category_Meal_ViewModel by viewModels()
    private val safeArgs:Category_MealFragmentArgs by navArgs()
    private var list:MealResponse?=null
    private lateinit var image:String
    private lateinit var name:String
    private lateinit var description:String
    private lateinit var category:String
    private lateinit var area:String
    private lateinit var yt:String

    private lateinit var adapter: Category_Meal_Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentCategoryMealBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        getMealByCategory(view)
        super.onViewCreated(view, savedInstanceState)

        binding.tvCategoryName.text=safeArgs.category
    }

    private fun getMealByCategory(view:View) {

        adapter= Category_Meal_Adapter(){
            val action= Category_MealFragmentDirections.actionCategoryMealFragmentToDescriptionFragment2(it.idMeal);
            findNavController().navigate(action)
        }

        val myList=binding.mealRecyclerview
        myList.adapter=adapter

        val layoutManager=GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
        myList.layoutManager=layoutManager

        viewModel.getMealByCategory(safeArgs.category)

        viewModel.itemLive.observe(viewLifecycleOwner, Observer {

            binding.tvCategoryCount.text=it.meals.size.toString()
            (myList.adapter as Category_Meal_Adapter).submitList(it.meals)

            Log.d("Meal By Category", it.meals.toString())
        })
    }




}