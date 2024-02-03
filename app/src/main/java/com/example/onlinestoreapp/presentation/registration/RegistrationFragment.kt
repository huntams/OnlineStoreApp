package com.example.onlinestoreapp.presentation.registration

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.base.PhoneTextWatcher
import com.example.onlinestoreapp.databinding.FragmentRegistrationBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val binding by viewBinding(FragmentRegistrationBinding::bind)
    private val viewModel by viewModels<RegistrationViewModel>()
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
            phoneTextInputEditText.addTextChangedListener(PhoneTextWatcher(phoneTextInputEditText))
            phoneTextInputEditText.addTextChangedListener {
                buttonEnable()
            }
            buttonEnter.setOnClickListener {
                viewModel.addUser(
                    nameTextInputEditText.text.toString(),
                    surnameTextInputEditText.text.toString(),
                    "+7 ${phoneTextInputEditText.text.toString()}"

                )
                findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToHome())
            }
        }
    }

    private fun buttonEnable() {
        with(binding) {
            buttonEnter.isEnabled =
                ((nameTextInputLayout.error.isNullOrEmpty() && nameTextInputEditText.text?.isNotEmpty() == true) &&
                        (surnameTextInputLayout.error.isNullOrEmpty() && surnameTextInputEditText.text?.isNotEmpty() == true)
                        && phoneTextInputEditText.text?.length == 13)
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