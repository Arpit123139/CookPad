package com.example.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.Adapters.MostPopularAdapter
import com.example.foodreciepie.R
import com.example.foodreciepie.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.observeOn

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding:FragmentHomeBinding
    private lateinit var adapter: MostPopularAdapter
    private lateinit var image:String
    private lateinit var name:String
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRandomMeal(view)

        binding.imgRandomMeal.setOnClickListener {

            val action=HomeFragmentDirections.actionHomeFragmentToDescriptionFragment2(image,name,description,category,area,yt);
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

            Glide.with(view)
                .load(it.meal.get(0).image)
                .into(view.findViewById(R.id.img_random_meal))


            if(it.meal.get(0)?.image?.length!=0){
                // Log.d("ArpitFragment","IF CONDITION  "+it)
                view.findViewById<ProgressBar>(R.id.pb).visibility=View.INVISIBLE
            }

        })
    }


}