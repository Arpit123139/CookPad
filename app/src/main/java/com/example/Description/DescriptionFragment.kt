package com.example.Description

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodreciepie.R
import com.example.foodreciepie.databinding.FragmentDescriptionBinding

class DescriptionFragment : Fragment() {

    private lateinit var  binding:FragmentDescriptionBinding
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

        val safeargs:DescriptionFragmentArgs by navArgs()

        binding.collapsingToolbar.title=safeargs.name
        binding.tvCategoryInfo.text=safeargs.categort
        binding.tvInstructions.text=safeargs.description
        binding.tvAreaInfo.text=safeargs.area
        //setting the color of the text to white when it is collapsed or expanded
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))


        //you tube button click handle

        binding.imgYoutube.setOnClickListener {

            val yt=safeargs.yt
            val intent=Intent(Intent.ACTION_VIEW, Uri.parse(yt))
            startActivity(intent)
        }
        Glide.with(view)
            .load(safeargs.image)
            .into(binding.imgMealDetail)
    }


}
