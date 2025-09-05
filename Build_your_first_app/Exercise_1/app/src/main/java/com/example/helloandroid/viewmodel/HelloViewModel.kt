package com.example.helloandroid.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.helloandroid.model.HelloAndroid;

class HelloViewModel : ViewModel() {
    private val _hello: HelloAndroid = HelloAndroid();

    public fun gretting():String {
        return _hello.text;
    }
}