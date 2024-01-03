package com.example.currencyapp.service


import com.example.currencyapp.model.CurrencyModel
import retrofit2.http.GET

interface CurrencyAPI {
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData():retrofit2.Call<List<CurrencyModel>>

}