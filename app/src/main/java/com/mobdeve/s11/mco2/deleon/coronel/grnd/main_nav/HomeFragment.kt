package com.mobdeve.s11.mco2.deleon.coronel.grnd.main_nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters.NewsAdapter
import com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters.PlansAdapter
import com.mobdeve.s11.mco2.deleon.coronel.grnd.dao.NewsDAO
import com.mobdeve.s11.mco2.deleon.coronel.grnd.dao.NewsDaoArrayList
import com.mobdeve.s11.mco2.deleon.coronel.grnd.dao.PlansDAO
import com.mobdeve.s11.mco2.deleon.coronel.grnd.dao.PlansDaoArrayList
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentHomeBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.NewsModel
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutPlanModel

class HomeFragment : Fragment() {

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var plansAdapter: PlansAdapter
    private lateinit var binding: FragmentHomeBinding
    var newsList = ArrayList<NewsModel?>()
    var plansList = ArrayList<WorkoutPlanModel?>()
    var newsDAO: NewsDAO = NewsDaoArrayList()
    var plansDAO: PlansDAO = PlansDaoArrayList()

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

        populateList()

        newsAdapter = NewsAdapter(newsList)
        plansAdapter = PlansAdapter(plansList)

        binding.newsView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.plansView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.newsView.adapter = newsAdapter
        binding.plansView.adapter = plansAdapter
    }

    fun populateList() {
        newsList = newsDAO.getNews()!!
        plansList = plansDAO.getPlans()!!
    }
}