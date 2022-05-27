package com.example.prestigehosteladmin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.prestigehosteladmin.R
import com.example.prestigehosteladmin.databinding.FragmentResetPasswordBinding
import com.example.prestigehosteladmin.utils.toastMessage
import com.example.prestigehosteladmin.viewmodel.AuthViewModel


class ResetPasswordFragment : Fragment() {


    private var _binding:FragmentResetPasswordBinding?=null
    private val binding get() = _binding!!

    private lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentResetPasswordBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel=ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.sendResetPasswordEmailBtn.setOnClickListener {
            sendResetPasswordEmail()
        }
    }

    private fun sendResetPasswordEmail(){
        val email=binding.emailInputResetPassword.text.toString()
        if (email.isEmpty()){
            requireContext().toastMessage("Enter your email")

        }else{
            authViewModel.sendPasswordResetEmail(email).observe(viewLifecycleOwner){authState->

                authState.isSuccess.let {isSuccess->
                    if (isSuccess){
                        requireContext().toastMessage("Email sent")
                    }

                }


                authState.exception?.let { exception ->
                val message=exception.localizedMessage
                    if (message != null) {
                        requireContext().toastMessage(message)
                    }

                }

            }
        }

    }


}