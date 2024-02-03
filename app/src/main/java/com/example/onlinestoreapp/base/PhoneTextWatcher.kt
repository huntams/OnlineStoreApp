package com.example.onlinestoreapp.base

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText

class PhoneTextWatcher(private val editText: TextInputEditText): TextWatcher {
    private var isFormatting = false
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        if (isFormatting) {
            return
        }

        isFormatting = true

        val phone = s.toString()
        val formattedPhone = formatPhone(phone)

        if (phone != formattedPhone) {
            editText.setText(formattedPhone)
            editText.setSelection(formattedPhone.length)
        }

        isFormatting = false
    }

    private fun formatPhone(phone: String): String {
        var formattedPhone = ""

        for (i in phone.indices) {
            if (phone[i].isDigit()) {
                if (formattedPhone.length == 3) {
                    formattedPhone += " "
                } else if ( formattedPhone.length == 7 ||formattedPhone.length == 10) {
                    formattedPhone += "-"
                }
                formattedPhone += phone[i]
            }
        }

        return formattedPhone
    }
}