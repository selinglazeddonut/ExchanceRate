package com.example.currencyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapp.R
import com.example.currencyapp.adapter.RecyclerViewAdapter
import com.example.currencyapp.databinding.ActivityMainBinding
import com.example.currencyapp.model.CurrencyModel
import com.example.currencyapp.service.CurrencyAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {

    private lateinit var binding: ActivityMainBinding
    private val BASE_URL="https://raw.githubusercontent.com/"
    private var currencyModels:ArrayList<CurrencyModel>?=null
    private var recyclerViewAdapter : RecyclerViewAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root

        setTheme(R.style.Theme_SplashCurrencyAPP)
        //İsimlendirme çok önemli yoksa üst üste geçiyor ekranlar!
        setContentView(view)

        //RecyclerView

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        loadData()

    }

    private fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyAPI::class.java)

        val call = retrofit.getData()

        call.enqueue(object: Callback<List<CurrencyModel>> {
            override fun onFailure(call: Call<List<CurrencyModel>>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(
                call: Call<List<CurrencyModel>>,
                response: Response<List<CurrencyModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        currencyModels = ArrayList(it)
                        currencyModels?.let {
                            recyclerViewAdapter = RecyclerViewAdapter(it,this@MainActivity)
                            binding.recyclerView.adapter = recyclerViewAdapter
                        }
                    }
                }
            }
        })



    }

    override fun onItemClick(currencyModel: CurrencyModel) {
        Toast.makeText(applicationContext,"Clicked on: ${currencyModel.currency}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()

}  }