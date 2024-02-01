package com.example.onlinestoreapp.presentation.registration

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.FragmentRegistrationBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val binding by viewBinding(FragmentRegistrationBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            nameTextInputEditText.addTextChangedListener {
                errorHandle(nameTextInputEditText, nameTextInputLayout)
                buttonEnable()
            }
            surnameTextInputEditText.addTextChangedListener {
                errorHandle(surnameTextInputEditText, surnameTextInputLayout)
                buttonEnable()
            }
            phoneTextInputEditText.addTextChangedListener {
                buttonEnable()
            }
            buttonEnter.setOnClickListener {
                findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToCatalog2())
            }
        }
    }

    private fun buttonEnable() {
        with(binding) {
            buttonEnter.isEnabled =
                ((nameTextInputLayout.error.isNullOrEmpty() && nameTextInputEditText.text?.isEmpty() == false) &&
                        (surnameTextInputLayout.error.isNullOrEmpty() && surnameTextInputEditText.text?.isEmpty() == false)
                        && phoneTextInputEditText.length() == 10)
        }

    }

    private fun errorHandle(view: TextInputEditText, layout: TextInputLayout) {
        if (!isContainsCyrillic(view.text.toString())) {
            layout.error = getString(R.string.error)
        } else {
            layout.error = null
        }


    }
}


private fun isContainsCyrillic(str: String) =
    str.replace("[а-яА-Я]".toRegex(), "").isEmpty()