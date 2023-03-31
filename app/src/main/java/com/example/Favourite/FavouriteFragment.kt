package com.example.Favourite

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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.Category.CategoryFragment
import com.example.data.FavouriteMealResponse
import com.example.data.Meal
import com.example.foodreciepie.R
import com.example.foodreciepie.databinding.FragmentDescriptionBinding
import com.example.foodreciepie.databinding.FragmentFavouriteBinding
import com.example.home.HomeFragmentDirections
import com.example.utils.Network
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Time
import kotlin.math.log

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private lateinit var  binding: FragmentFavouriteBinding
    private val viewModel: FavouriteViewModel by viewModels()
    private lateinit var adapter: FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentFavouriteBinding.inflate(layoutInflater);

        val bottomNavigationView = binding.bottomNavigation1 as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    val action=FavouriteFragmentDirections.actionFavouriteFragmentToHomeFragment()
                    findNavController().navigate(action)
                }
                R.id.favourite-> {}
                R.id.category -> {}
            }
            true
        }

        return binding.root;
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val  itemTouchHelper=object:ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            )=true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val id=adapter.currentList[position].id
                val item=adapter.currentList[position]


                Toast.makeText(requireContext(),id.toString(),Toast.LENGTH_LONG).show()
                viewModel.deleteMeal(id);

                Snackbar.make(requireView(),"Meal Deleted",Snackbar.LENGTH_LONG).setAction(
                    "Undo",
                    View.OnClickListener{
                        //GetAllMeal()
                        viewModel.saveMeal(Meal(item.name,item.area,item.category,item.description,item.yt,item.image,item.id));
                        //GetAllMeal()
                        adapter.notifyDataSetChanged()

                    }
                ).show()

            }



        }

        ItemTouchHelper(itemTouchHelper).attachToRecyclerView( binding.mealRecyclerview1)
        adapter= FavouriteAdapter(){
            val action=FavouriteFragmentDirections.actionFavouriteFragmentToDescriptionFragment2(it.id)
            findNavController().navigate(action)
        }
        val layoutManager= GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false)
        binding.mealRecyclerview1.adapter=adapter
        binding.mealRecyclerview1.layoutManager=layoutManager
        GetAllMeal()

    }

    private fun GetAllMeal() {
        viewModel.getAllMeal()
        viewModel._mealLive.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Network.Success -> {
                    Log.d("Favourite Fragment", it.data!!.meal.toString())
                    (binding.mealRecyclerview1.adapter as FavouriteAdapter).submitList(it.data.meal)
                    binding.mealRecyclerview1.adapter=adapter

                }
                is Network.Error -> {
                    Log.d("Favourite Fragment", it.message.toString())
                }
                is Network.Loading -> {

                }

            }
        })
    }


}