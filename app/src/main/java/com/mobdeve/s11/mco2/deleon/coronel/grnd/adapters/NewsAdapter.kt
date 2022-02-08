package com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.NewsRowBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.NewsModel
import java.io.File

class NewsAdapter( private val newsList: ArrayList<NewsModel?>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    inner class ViewHolder(private val itemBinding: NewsRowBinding): RecyclerView.ViewHolder(itemBinding.root) {

        val image = itemBinding.newsImage
        val headline = itemBinding.newsHeader

//        fun bindItem(item: NewsModel, position: Int) {
//            itemBinding.newsImage.setImageResource(item.image)
//            itemBinding.newsHeader.text = item.headline
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = NewsRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = newsList[position]

        val storageRef = FirebaseStorage.getInstance().reference.child("News/${currentItem!!.image}.jpeg")
        val localfile = File.createTempFile("tempImage", "jpeg")
        storageRef.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            holder.image.setImageBitmap(bitmap)

            holder.headline.text = currentItem!!.headline
        }
    }
}