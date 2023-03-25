package com.example.Description

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.data.Category
import com.example.data.Meal
import com.example.foodreciepie.R
import com.example.foodreciepie.databinding.FragmentDescriptionBinding
import com.example.home.HomeViewModel
import com.example.utils.Network
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescriptionFragment : Fragment() {

    private lateinit var  binding:FragmentDescriptionBinding
    private val viewModel: DescriptionViewModel by viewModels()
    private lateinit var image:String
    private lateinit var yt:String
    private lateinit var name:String
    private lateinit var area:String
    private lateinit var category: String
    private lateinit  var description:String
    private lateinit var id1:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_description, container, false)
        binding=FragmentDescriptionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         getData(view)

        val safeargs:DescriptionFragmentArgs by navArgs()
         binding.btnSave.setOnClickListener {

             saveMeal(Meal(name,area,category,description,yt,image,safeargs.id))
         }

    }

    private fun saveMeal(meal: Meal) {
        viewModel.saveMeal(meal);

        viewModel._mealSaveLive.observe(viewLifecycleOwner, Observer {
            when(it){
                is Network.Loading -> {
                    Toast.makeText(requireContext(),"Meal Saved",Toast.LENGTH_SHORT).show()
                    Log.d("DescriptionFragment", it.data.toString())

                }
                is Network.Success -> {}
                is Network.Error -> {
                    Log.d("DescriptionFragment", it.message.toString())
                }


            }
        })
    }

    fun getData(view: View){

        val safeargs:DescriptionFragmentArgs by navArgs()
        val id=safeargs.id
        viewModel.getMealById(id)
        viewModel._mealLive.observe(viewLifecycleOwner, Observer {

            yt=it.meal.get(0).yt
            image=it.meal.get(0).image
            name=it.meal.get(0).name
            area=it.meal.get(0).area
            category=it.meal.get(0).category
            description=it.meal.get(0).description
            binding.collapsingToolbar.title=it.meal.get(0).name
            binding.tvCategoryInfo.text=it.meal.get(0).category
            binding.tvInstructions.text=it.meal.get(0).description
            binding.tvAreaInfo.text=it.meal.get(0).area

            Glide.with(view)
                .load(image)
                .into(binding.imgMealDetail)
        })

        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))


        //you tube button click handle

        binding.imgYoutube.setOnClickListener {

            val intent=Intent(Intent.ACTION_VIEW, Uri.parse(yt))
            startActivity(intent)
        }

    }


}
