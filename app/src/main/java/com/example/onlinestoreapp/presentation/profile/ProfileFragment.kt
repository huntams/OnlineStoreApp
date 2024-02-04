package com.example.onlinestoreapp.presentation.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.data.model.ResultLoader
import com.example.onlinestoreapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel by viewModels<ProfileViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUser()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            binding.buttonProfile.setAllText("${it?.name} ${it?.surname}", "${it?.phone}")
        }
        viewModel.productsLiveData.observe(viewLifecycleOwner) {
            binding.buttonFavourite.setAllText(
                getString(R.string.favourite),
                "${it.size} ${viewModel.wordDeclension(it.size, getString(R.string.product))}"
            )
        }
        binding.buttonFavourite.setStartImage(R.drawable.ic_heart_default_24)
        binding.buttonFavourite.setEndImage(R.drawable.ic_right_arrow_24)
        binding.buttonFavourite.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileToFavouriteFragment())
        }
        binding.buttonProfile.setOnClickListener {
            viewModel.deleteDB()
        }
        viewModel.deleteLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultLoader.Failure -> {
                    Toast.makeText(requireContext(), result.throwable.message, Toast.LENGTH_LONG)
                        .show()
                    binding.buttonProfile.isEnabled = true
                    binding.buttonFavourite.isEnabled = true
                }

                is ResultLoader.Loading -> {
                    binding.buttonProfile.isEnabled = false
                    binding.buttonFavourite.isEnabled = false
                }

                is ResultLoader.Success -> {
                    findNavController().navigate(ProfileFragmentDirections.actionProfileToRegistrationFragment())
                }

                else -> {

                }
            }


        }
    }
}