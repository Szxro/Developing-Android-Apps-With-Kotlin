package com.example.aboutme.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.aboutme.model.MyName

class AboutMeViewModel : ViewModel() {
    var myName: MyName by mutableStateOf(MyName())
        private set;

    fun onNameChange(newNickName:String):Unit{
        myName = MyName(newNickName);
    }

    fun onNickNameChangeVisibility():Unit{
        val (currentNickName,currentNickNameVisibility) = myName;

        if(currentNickName.isEmpty()) return;

        myName = MyName(currentNickName,!currentNickNameVisibility);
    }
}