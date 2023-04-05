package com.example.BottomSheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.Description.DescriptionViewModel
import com.example.Signup.SignUpFragmentDirections
import com.example.foodreciepie.R
import com.example.foodreciepie.databinding.FragmentMealBottomSheetBinding
import com.example.home.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealBottomSheetFragment : BottomSheetDialogFragment() {


    private lateinit var binding: FragmentMealBottomSheetBinding
    private val viewModel: DescriptionViewModel by viewModels()
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentMealBottomSheetBinding.inflate(layoutInflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val b = arguments
        viewModel.getMealById(b!!.getString("MEAL_ID").toString())

        viewModel._mealLive.observe(viewLifecycleOwner, Observer {
            binding.tvMealCategory.text=it.meal.get(0).category
            binding.tvMealCountry.text=it.meal.get(0).area
            binding.tvMealNameInBtmsheet.text=it.meal.get(0).name

            Glide.with(view)
                .load(it.meal.get(0).image)
                .into(binding.imgCategory)
        })

        binding.view.setOnClickListener{


            findNavController().navigate(R.id.signUpFragment)       //navigating up to the signUp Fragment in order to find the navController because this is a child Fragment which does not have a NavController
            val action=SignUpFragmentDirections.actionSignUpFragmentToDescriptionFragment2(b!!.getString("MEAL_ID").toString())

            findNavController().navigate(action)

        }
        super.onViewCreated(view, savedInstanceState)
    }


}