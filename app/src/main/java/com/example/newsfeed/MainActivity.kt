package com.example.newsfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

        val swipeRefreshLayout= findViewById<SwipeRefreshLayout>(R.id.swipeRefrersh)
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { // This method gets called when user pull for refresh,

            val handler = Handler()
            handler.postDelayed(Runnable {
                if (swipeRefreshLayout.isRefreshing()) {

                    swipeRefreshLayout.setRefreshing(false)
                }
            }, 3000)
            fetchData()
            adapter.updateNews(newsItems)
        })
    }
    val newsItems:ArrayList<NewsData> = ArrayList()
    private fun fetchData(){// fetch news from the api
        val url:String="https://newsdata.io/api/1/news?apikey=pub_11801064179dcda1c1b71fbd4ef66f66bf793&q=India"
        val jsonObjectRequest=JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener{
                val newJsonArray=it.getJSONArray("results")//json obj m article ko target krega
                val newArray=ArrayList<NewsData>()
                for(i in 0 until newJsonArray.length()){
                    val newJsonObject=newJsonArray.getJSONObject(i);//article k under trav krega
                      val news=NewsData(
                        newJsonObject.getString("title"),
                        newJsonObject.getString("creator"),
                        newJsonObject.getString("link"),
                        newJsonObject.getString("image_url")
                    )
                    newArray.add(news)
                    newsItems.add(news)
                }
                adapter.updateNews(newArray)//ye q hai?
            },
            {

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
    fun onItemClicked(item:NewsData){

    }
}