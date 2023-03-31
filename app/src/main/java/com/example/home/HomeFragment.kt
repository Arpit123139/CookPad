package com.example.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.Adapters.CategoryAdapter
import com.example.Adapters.MostPopularAdapter
import com.example.Category.CategoryFragment
import com.example.Favourite.FavouriteFragment
import com.example.foodreciepie.R
import com.example.foodreciepie.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding:FragmentHomeBinding
    private lateinit var adapter: MostPopularAdapter
    private lateinit var adapter1: CategoryAdapter
    private lateinit var image:String
    private lateinit var name:String
    private lateinit var id:String
    private lateinit var description:String
    private lateinit var category:String
    private lateinit var area:String
    private lateinit var yt:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(layoutInflater)

        val bottomNavigationView = binding.bottomNavigation as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {}
                R.id.favourite-> {
                    val action=HomeFragmentDirections.actionHomeFragmentToFavouriteFragment()
                    findNavController().navigate(action)

                }
                R.id.category -> {
                    parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, CategoryFragment())
                        .commit();
                }
            }
            true
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRandomMeal(view)
        getPopularMeal(view)
        getCategoryList(view)


        binding.imgRandomMeal.setOnClickListener {

            val action=HomeFragmentDirections.actionHomeFragmentToDescriptionFragment2(id);
            findNavController().navigate(action)
        }

    }

    fun getRandomMeal(view:View){

        viewModel.getMeal()
        viewModel.mealLive.observe(viewLifecycleOwner, Observer {
            Log.d("ArpitFragment","URL "+it.meal.get(0).image)

            image=it.meal.get(0).image
            name=it.meal.get(0).name
            description=it.meal.get(0).description
            category=it.meal.get(0).category
            area=it.meal.get(0).area
            yt=it.meal.get(0).yt
            id=it.meal.get(0).id

            Glide.with(view)
                .load(it.meal.get(0).image)
                .into(view.findViewById(com.example.foodreciepie.R.id.img_random_meal))


            if(it.meal.get(0)?.image?.length!=0){
                // Log.d("ArpitFragment","IF CONDITION  "+it)
                view.findViewById<ProgressBar>(com.example.foodreciepie.R.id.pb).visibility=View.INVISIBLE
            }

        })
    }

    fun getPopularMeal(view: View){

        adapter= MostPopularAdapter() {
            Toast.makeText(requireContext(),it.idMeal.toString(),Toast.LENGTH_LONG).show()
            val action=HomeFragmentDirections.actionHomeFragmentToDescriptionFragment2(it.idMeal);
            findNavController().navigate(action)

        }

        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val myList = binding.recViewMealsPopular
        myList.layoutManager = layoutManager
        myList.adapter= adapter

        viewModel.getPopularMeal()
        viewModel.popularMealLive.observe(viewLifecycleOwner,Observer{
            Log.d("ARPIT LIVE DATA", "${it.meals}")

            (myList.adapter as MostPopularAdapter).submitList(it.meals)
        })

    }



    fun getCategoryList(view:View){

        val catList=binding.recyclerView
        adapter1= CategoryAdapter(){
            Toast.makeText(requireContext(),"Category clicked ${it.strCategory}",Toast.LENGTH_LONG).show()
            val action=HomeFragmentDirections.actionHomeFragmentToCategoryMealFragment(it.strCategory);
            findNavController().navigate(action)
        }
        val layoutManager=GridLayoutManager(requireContext(),3,GridLayoutManager.VERTICAL,false);

        catList.adapter=adapter1
        catList.layoutManager=layoutManager

        viewModel.getCategoryList()
        viewModel.categoryListLive.observe(viewLifecycleOwner, Observer{

            (catList.adapter as CategoryAdapter).submitList(it.categories)
        })
    }

}