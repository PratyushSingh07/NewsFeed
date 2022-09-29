package com.example.newsfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var adapter:NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager=LinearLayoutManager(this)//shows ki vertical form mai list show hoga
        fetchData()
        adapter = NewsAdapter()
        recyclerView.adapter=adapter//recycler view k saath adapter attach ho gaya hai
    }
    private fun fetchData(){// fetch news from the api
        val url:String="https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=929449b69a9e4f6ba3940b01105edb68"
        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener{
                val newJsonArray=it.getJSONArray("article")//json obj m article ko target krega
                val newArray=ArrayList<NewsData>()
                for(i in 0 until newJsonArray.length()){
                    val newJsonObject=newJsonArray.getJSONObject(i);//article k under trav krega
                    val news=NewsData(
                        newJsonObject.getString("title"),
                        newJsonObject.getString("author"),
                        newJsonObject.getString("url"),
                        newJsonObject.getString("urlToImage")
                    )
                    newArray.add(news);
                }
                adapter.updateNews(newArray)
            },
            { _ ->

            })


            @Throws(AuthFailureError::class)
              fun getHeaders(): Map<String, String>? {
                val headers = HashMap<String, String>()
                //headers.put("Content-Type", "application/json");
                headers["key"] = "Value"
                return headers
            }

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}