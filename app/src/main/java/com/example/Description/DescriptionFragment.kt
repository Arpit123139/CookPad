package com.example.Description

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.data.Meal
import com.example.foodreciepie.R
import com.example.foodreciepie.databinding.FragmentDescriptionBinding
import com.example.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescriptionFragment : Fragment() {

    private lateinit var  binding:FragmentDescriptionBinding
    private val viewModel: DescriptionViewModel by viewModels()
    private lateinit var image:String
    private lateinit var yt:String

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

//        binding.btnSave.setOnClickListener {
//                viewModel.saveMeal(Meal(safeargs.image,safeargs.name,safeargs.description,safeargs.categort,safeargs.area,safeargs.yt))
//        }

    }

    fun getData(view: View){

        val safeargs:DescriptionFragmentArgs by navArgs()
        val id=safeargs.id
        viewModel.getMealById(id)
        viewModel._mealLive.observe(viewLifecycleOwner, Observer {

            yt=it.meal.get(0).yt
            image=it.meal.get(0).image
            binding.collapsingToolbar.title=it.meal.get(0).name
            binding.tvCategoryInfo.text=it.meal.get(0).category
            binding.tvInstructions.text=it.meal.get(0).description
            binding.tvAreaInfo.text=it.meal.get(0).area

            Glide.with(view)
                .load(image)
                .into(binding.imgMealDetail)
        })

//        binding.collapsingToolbar.title=safeargs.name
//        binding.tvCategoryInfo.text=safeargs.categort
//        binding.tvInstructions.text=safeargs.description
//        binding.tvAreaInfo.text=safeargs.area
        //setting the color of the text to white when it is collapsed or expanded
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))


        //you tube button click handle

        binding.imgYoutube.setOnClickListener {

            //val yt=safeargs.yt
            val intent=Intent(Intent.ACTION_VIEW, Uri.parse(yt))
            startActivity(intent)
        }

    }


}
