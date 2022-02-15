package com.mobdeve.s11.mco2.deleon.coronel.grnd.main_nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters.NewsAdapter
import com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters.PlansAdapter
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentHomeBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.NewsModel
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutPlanModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var dbref: DatabaseReference
    private lateinit var newsRecyclerview: RecyclerView
    private lateinit var plansRecyclerView: RecyclerView
    var newsList = ArrayList<NewsModel?>()
    var plansList = ArrayList<WorkoutPlanModel?>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        newsRecyclerview = binding.newsView
        plansRecyclerView = binding.plansView
        newsRecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        plansRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        getNewsData()
        getPlansData()
    }

    private fun getNewsData() {
        dbref = FirebaseDatabase.getInstance().getReference("News")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (newsSnapshot in snapshot.children) {
                    val news = NewsModel(newsSnapshot.child("id").value.toString().toInt(),
                                        newsSnapshot.child("image").value.toString(),
                                        newsSnapshot.child("headline").value.toString())
                    newsList.add(news)
                    newsRecyclerview.adapter = NewsAdapter(newsList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun getPlansData() {
        dbref = FirebaseDatabase.getInstance().getReference("Plans")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (planSnapshot in snapshot.children) {
                    val plan = WorkoutPlanModel(planSnapshot.child("image").value.toString())
                    plansList.add(plan)
                    plansRecyclerView.adapter = PlansAdapter(plansList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}