package com.example.Signup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.data.UserRequest
import com.example.foodreciepie.R
import com.example.foodreciepie.databinding.FragmentLoginTabBinding
import com.example.utils.Network
import com.example.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginTabFragment : Fragment() {

    private lateinit var binding: FragmentLoginTabBinding
    private val viewModel: UserViewModel by viewModels()

    @Inject
    lateinit var tokenManager: TokenManager

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

            val email1=view.findViewById<EditText>(R.id.email1)
            var email=email1.text.toString()
            val password1=view.findViewById<EditText>(R.id.password1)
            var password=password1.text.toString()
            signInUser(UserRequest(email,password))
        }

        super.onViewCreated(view, savedInstanceState)
    }

    fun signInUser(userRequest: UserRequest){
        viewModel.signIn(userRequest)

        viewModel.user_res.observe(viewLifecycleOwner, Observer {
            when(it){
                is Network.Success -> {

                    val token=it.data!!.token
                    tokenManager.saveToken(token)

                    val action=LoginTabFragmentDirections.actionLoginTabFragmentToHomeFragment2()
                    findNavController().navigate(action)
                }
                is Network.Loading -> {}
                is Network.Error -> {
                    Log.d("LoginFragment",  it.message.toString())
                }
            }
        })
    }



}