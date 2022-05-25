package com.example.prestigehosteladmin.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.example.prestigehosteladmin.databinding.FragmentSignInBinding
import com.example.prestigehosteladmin.viewmodel.AuthViewModel
import kotlinx.coroutines.launch


/**
 *
 * create an instance of this fragment.
 */
class SignInFragment : Fragment() {


    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    // global variables
    private var email: String = ""
    private var password: String = ""
    private val viewModel: AuthViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.signInBtn.setOnClickListener {
            validateForm()
        }

    }

    private fun validateForm() {

        email = binding.emailInputSi.text.toString()
        password = binding.passwordInputSi.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "EnterFields", Toast.LENGTH_SHORT).show()
        } else {

            signUp(email, password)

        }

    }

    private fun signUp(email: String, password: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val currentUser = viewModel.signUp(email, password)
                Log.d("max", "mmmmmdksjlfljklfjnkjflkjelfleij $currentUser.toString()")
                if (currentUser != null) {
                    val action =
                        SignInFragmentDirections.actionSignInFragmentToDashboardFragment()
                    findNavController().navigate(action)
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_LONG).show()
            }


        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}