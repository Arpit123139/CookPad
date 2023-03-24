package com.example.Signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.foodreciepie.R
import com.example.foodreciepie.databinding.FragmentLoginTabBinding


class LoginTabFragment : Fragment() {

    private lateinit var binding: FragmentLoginTabBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentLoginTabBinding.inflate(layoutInflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.loginBtn.setOnClickListener{
            val action=LoginTabFragmentDirections.actionLoginTabFragmentToHomeFragment2()
            findNavController().navigate(action)
            Toast.makeText(requireContext(),"Button Clicked",Toast.LENGTH_LONG).show()
        }

        super.onViewCreated(view, savedInstanceState)
    }


}