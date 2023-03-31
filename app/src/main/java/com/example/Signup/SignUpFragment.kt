package com.example.Signup

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.data.UserRequest
import com.example.foodreciepie.R
import com.example.foodreciepie.databinding.FragmentSignUpBinding
import com.example.utils.Network
import com.example.utils.TokenManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: UserViewModel by viewModels()

    @Inject
    lateinit var tokenManager: TokenManager

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSignUpBinding.inflate(layoutInflater)
        val view =inflater.inflate(R.layout.fragment_sign_up, container, false)
       // title = "KotlinApp"


        val fabFb=view.findViewById<FloatingActionButton>(R.id.fab_fb2)
        val fabGoogle=view.findViewById<FloatingActionButton>(R.id.fab_google2)
        val fabTwitter=view.findViewById<FloatingActionButton>(R.id.fab_twitter2)

        fabFb.setTranslationY(300f)
        fabGoogle.setTranslationY(300f)
        fabTwitter.setTranslationY(300f)

        fabFb.setAlpha(0f)
        fabGoogle.setAlpha(0f)
        fabTwitter.setAlpha(0f)


        fabFb.animate().translationY(0f).alpha(1f).setDuration(1000).setStartDelay(400).start()
        fabGoogle.animate().translationY(0f).alpha(1f).setDuration(1000).setStartDelay(600).start()
        fabTwitter.animate().translationY(0f).alpha(1f).setDuration(1000).setStartDelay(800).start()

//        if(tokenManager.getToken()!=null){
//            val action=SignUpFragmentDirections.actionSignUpFragmentToHomeFragment()
//            findNavController().navigate(action)
//        }
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val btn=view.findViewById<Button>(R.id.signup_btn)
        btn.setOnClickListener {

            val email1=view.findViewById<EditText>(R.id.email)
            var email=email1.text.toString()
            val password1=view.findViewById<EditText>(R.id.password)
            var password=password1.text.toString()
            signUpUser(UserRequest(email,password))

        }

        val login=view.findViewById<TextView>(R.id.login)
        login.setOnClickListener {
            val action=SignUpFragmentDirections.actionSignUpFragmentToLoginTabFragment()
            findNavController().navigate(action)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    fun signUpUser(userRequest: UserRequest){
        viewModel.signUp(userRequest)

        viewModel.user_res.observe(viewLifecycleOwner, Observer {
            when(it){
                is Network.Success -> {

                    val token=it.data!!.token
                    tokenManager.saveToken(token)

                    val action=SignUpFragmentDirections.actionSignUpFragmentToHomeFragment()
                    findNavController().navigate(action)
                }
                is Network.Loading -> {}
                is Network.Error -> {
                    Log.d("SignUpFragment",  it.message.toString())
                }
            }
        })
    }


}