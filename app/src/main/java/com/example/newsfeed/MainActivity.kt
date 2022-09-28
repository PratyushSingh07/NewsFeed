package com.example.newsfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager=LinearLayoutManager(this)//shows ki vertical form mai list show hoga
        val list=fetchData()
        val adapter:NewsAdapter= NewsAdapter(list)
        recyclerView.adapter=adapter//recycler view k saath adapter attach ho gaya hai
    }
    private fun fetchData():ArrayList<String>{
        var list=ArrayList<String>()
        for(i in 0 until 100)list.add("item $i")
        return list
    }
}