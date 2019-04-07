package com.katsiro.alexey.gia.utils.extensions

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

var EditText.string: String
    set(value) = setText(value)
    get() = text.toString()

var TextInputLayout.text: String
    get() = editText?.string ?: ""
    set(value) {
        editText?.string = value
    }