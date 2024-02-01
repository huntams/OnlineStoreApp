package com.example.onlinestoreapp.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFav.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileToFavouriteFragment())
        }
        binding.buttonProfile.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileToRegistrationFragment())
        }
    }
}