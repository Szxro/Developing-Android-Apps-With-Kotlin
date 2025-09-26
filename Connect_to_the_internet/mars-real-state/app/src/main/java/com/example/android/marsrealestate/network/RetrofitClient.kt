package com.example.android.marsrealestate.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient {
    companion object{
        private var INSTANCE: Retrofit? = null;

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private const val BASE_URL = "https://mars.udacity.com/";

        fun getInstance(): Retrofit? {
            synchronized(this){
                var instance = INSTANCE;

                if(instance == null){
                    instance = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(MoshiConverterFactory.create(moshi))
                        .build();

                    INSTANCE = instance;
                }

                return instance;
            }
        }
    }
}