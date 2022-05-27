package com.example.prestigehosteladmin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.prestigehosteladmin.R
import com.example.prestigehosteladmin.databinding.FragmentSignUpBinding
import com.example.prestigehosteladmin.utils.toastMessage
import com.example.prestigehosteladmin.viewmodel.AuthViewModel
import com.shencoder.loadingdialog.LoadingDialog

/*
* created by maxwell takyi on 21 april 2022
* */
class FragmentSignUp : Fragment(R.layout.fragment_sign_up) {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private lateinit var authViewModel: AuthViewModel
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildLoadingDialog()

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.signUpBtn.setOnClickListener {
            validateForm()
        }

        binding.signInTx.setOnClickListener {
            val action = FragmentSignUpDirections.actionFragmentSignUpToSignInFragment()
            findNavController().navigate(action)
        }


    }

    private fun validateForm() {
        val email = binding.emailInput.text.toString()
        val password = binding.signUpPasswordInput.text.toString()
        val confirmPassword = binding.signUpRepeatPasswordInput.text.toString()

        when {
            email.isEmpty() -> {
                requireContext().toastMessage("Enter your email")
                return
            }
            password.isEmpty() -> {
                requireContext().toastMessage("Enter your password")
                return
            }
            confirmPassword.isEmpty() -> {
                requireContext().toastMessage("Confirm your password")
                return
            }
            password != confirmPassword -> {
                requireContext().toastMessage("Password do not match")
                return
            }
            else -> {
                signUp(email, password)
                loadingDialog.show()
            }
        }
    }

    private fun buildLoadingDialog() {
        loadingDialog = LoadingDialog.createDefault(requireContext())
        loadingDialog.setHintText("Signing Up...")
        loadingDialog.showHintText(true)
        loadingDialog.setCancelable(false)

    }

    private fun signUp(email: String, password: String) {

        authViewModel.signUpUser(email, password).observe(viewLifecycleOwner) { authUiState ->

            authUiState.isSuccess.let { isSuccess ->
                if (isSuccess) {

                    loadingDialog.dismiss()

                    val action = FragmentSignUpDirections.actionFragmentSignUpToDashboardFragment()
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

                        loadingDialog.dismiss()
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