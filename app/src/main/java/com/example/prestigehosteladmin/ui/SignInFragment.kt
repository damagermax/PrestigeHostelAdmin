package com.example.prestigehosteladmin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.prestigehosteladmin.databinding.FragmentSignInBinding
import com.example.prestigehosteladmin.utils.toastMessage
import com.example.prestigehosteladmin.viewmodel.AuthViewModel


/**
 *
 * create an instance of this fragment.
 */
class SignInFragment : Fragment() {


    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!


    private lateinit var authViewModel: AuthViewModel

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


        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.signInBtn.setOnClickListener {
            validateForm()
        }

        binding.signUpTx.setOnClickListener {
            val action = SignInFragmentDirections.actionSignInFragmentToFragmentSignUp()
            findNavController().navigate(action)
        }

    }

    private fun validateForm() {

        val email = binding.emailInputSi.text.toString()
        val password = binding.passwordInputSi.text.toString()

        when {
            email.isEmpty() -> {

                requireContext().toastMessage("Enter your email")
                return

            }
            password.isEmpty() -> {

                requireContext().toastMessage("Enter your password")
                return

            }
            else -> {

                signInUser(email, password)

            }
        }

    }

    private fun signInUser(email: String, password: String) {

        authViewModel.signInUser(email, password).observe(viewLifecycleOwner) { authUiState ->
            authUiState.isSuccess.let { isSuccess ->
                if (isSuccess) {

                    val action = SignInFragmentDirections.actionSignInFragmentToDashboardFragment()
                    findNavController().navigate(action)

                    authUiState.message.let { message ->
                        if (message != null) {
                            requireContext().toastMessage(message)
                        }

                    }

                }

            }

            authUiState.exception?.let { exception ->
                exception.localizedMessage.let { message ->
                    if (message != null) {
                        requireContext().toastMessage(message)
                    }

                }

            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}