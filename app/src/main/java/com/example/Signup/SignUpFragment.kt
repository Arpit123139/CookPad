package com.example.Signup

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.foodreciepie.R
import com.example.foodreciepie.databinding.FragmentSignUpBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout


class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentSignUpBinding
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

        val btn=view.findViewById<Button>(R.id.signup_btn)
        btn.setOnClickListener {
            val action=SignUpFragmentDirections.actionSignUpFragmentToHomeFragment()
            findNavController().navigate(action)
        }

        val login=view.findViewById<TextView>(R.id.login)
        login.setOnClickListener {
            val action=SignUpFragmentDirections.actionSignUpFragmentToLoginTabFragment()
            findNavController().navigate(action)
        }


        return view;
    }


}