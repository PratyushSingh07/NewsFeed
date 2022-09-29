package com.example.newsfeed

/**
 * we will be using this class just to hold the Data that will be displayed on the screen
 * We will pass this class as the Type of an arrayList i.e ArrayList<News>
 * Thus we will be able to create a list of objects of this class
 */
data class NewsData(
    val title:String,
    val author:String,
    val url:String,
    val imageUrl:String
)