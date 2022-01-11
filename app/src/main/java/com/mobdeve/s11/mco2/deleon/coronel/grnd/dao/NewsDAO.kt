package com.mobdeve.s11.mco2.deleon.coronel.grnd.dao

import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.NewsModel
import java.util.ArrayList

interface NewsDAO {
    fun addNews(news: NewsModel?): Long
    fun getNews(): ArrayList<NewsModel?>?
    fun getNews(newsId: Int): NewsModel?
    fun updateNews(news: NewsModel?): Int
    fun deleteNews(newsId: Int): Int
}