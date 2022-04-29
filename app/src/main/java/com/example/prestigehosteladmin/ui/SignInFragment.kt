package com.example.prestigehosteladmin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.prestigehosteladmin.R
import com.example.prestigehosteladmin.databinding.FragmentSignInBinding
import com.example.prestigehosteladmin.viewmodel.AuthViewModel


/**
 *
 * create an instance of this fragment.
 */
class SignInFragment : Fragment() {

    private lateinit var authViewModel:AuthViewModel

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    // global variables
    private var email:String=""
    private var password:String=""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        authViewModel=ViewModelProvider(this).get(AuthViewModel::class.java)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.signInBtn.setOnClickListener {
            validateForm()
        }

    }

    private fun validateForm(){

        email=binding.emailInputSi.text.toString()
        password=binding.passwordInputSi.text.toString()

        if (email.isEmpty()||password.isEmpty()){
            Toast.makeText(requireContext(),"EnterFields",Toast.LENGTH_SHORT).show()
        }else {
            authViewModel.signUp(email, password)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
       _binding=null
    }


}