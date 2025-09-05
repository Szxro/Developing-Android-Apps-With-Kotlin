package com.example.button.viewmodel

import androidx.lifecycle.ViewModel
import com.example.button.model.TextContent

class ButtonViewModel: ViewModel() {
    private val _textContent: TextContent = TextContent();

    public fun getTextContent():String{
        return _textContent.text;
    }
}