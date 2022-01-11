package com.mobdeve.s11.mco2.deleon.coronel.grnd.dao

import com.mobdeve.s11.mco2.deleon.coronel.grnd.R
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.NewsModel
import java.util.ArrayList

class NewsDaoArrayList : NewsDAO {
    var newsList = ArrayList<NewsModel?>()

    init{
        //News and Updates contents
        newsList.add(NewsModel(1, R.drawable.news1,"BREAKING NEWS 1"))
        newsList.add(NewsModel(2, R.drawable.news2,"BREAKING NEWS 2"))
        newsList.add(NewsModel(3, R.drawable.news3,"BREAKING NEWS 3"))
    }

    override fun addNews(news: NewsModel?): Long {
        newsList.add(news!!)

        return 1L
    }

    override fun getNews(): ArrayList<NewsModel?> = newsList

    override fun getNews(newsId: Int): NewsModel? {
        TODO("Not yet implemented")
    }

    override fun updateNews(news: NewsModel?): Int {
        TODO("Not yet implemented")
    }

    override fun deleteNews(newsId: Int): Int {
        TODO("Not yet implemented")
    }

}