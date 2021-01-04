package com.example.poijudata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface PoijuService {
    @GET("sse/latest")
    fun getCurrentPoijuData(): Call<Poijut>
}

class MainActivity : AppCompatActivity() {

    var BaseUrl = "https://meri.digitraffic.fi/api/v1/"
    private lateinit var mRecyclerView: RecyclerView
    private var mAdapter: CustomAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lista = arrayOf("gggg","hjkjk")

        mRecyclerView = findViewById(R.id.recycleView_main)
        mAdapter = CustomAdapter(lista)
        mRecyclerView.setAdapter(mAdapter)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))

        getCurrentData()
    }

    private fun getCurrentData() {
        val textView = findViewById<TextView>(R.id.textView)
        val textView2 = findViewById<TextView>(R.id.textView2)

        val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())   // json -> object
                .build()
//Request
        val service = retrofit.create(PoijuService::class.java)
        val call = service.getCurrentPoijuData( /* parametrit */)

        // Response
        call.enqueue(object : Callback<Poijut> {
            override fun onResponse(call: Call<Poijut>, response: Response<Poijut>) {
                if (response.code() == 200) {    // = HTTP OK
                    // tässä portsResponse on tyyppiä Ports
                    val poijuResponse = response.body()!!
                    textView.text = poijuResponse!!.dataUpdatedTime
                    textView2.text = poijuResponse!!.features[0].properties.windWaveDir.toString()
                   
                }
            }
                override fun onFailure(call: Call<Poijut>, t: Throwable) {
                    textView.text = t.message
                }
            })
        }
    }