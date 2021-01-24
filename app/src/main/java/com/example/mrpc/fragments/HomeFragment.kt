package com.example.mrpc.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.mrpc.R
import com.example.mrpc.RakitHomeList
import com.example.mrpc.adapter.SliderAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var currentPage = 0
    private var numPages = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moveTo_rakitHomeList_btn.setOnClickListener {
            val inten = Intent(activity,RakitHomeList::class.java)
            startActivity(inten)
        }

        val imgList = listOf(
            "https://wallpapercave.com/wp/wp2901698.jpg",
            "https://i.pinimg.com/originals/ad/ce/7d/adce7d386946f35cf0c28e628d24a30d.jpg",
            "https://c4.wallpaperflare.com/wallpaper/320/142/500/akali-league-of-legends-akali-league-of-legends-league-of-legends-k-da-video-games-hd-wallpaper-preview.jpg",
            "https://wallpapercave.com/wp/wp2298417.jpg"
        )
        createSlider(imgList)
    }

    private fun createSlider(string: List<String>) {

        vpSlider.adapter = activity?.let { SliderAdapter(it,string) }
        indicator.setViewPager(vpSlider)
        val density = resources.displayMetrics.density
        //Set circle indicator radius
        indicator.radius = 5 * density
        numPages = string.size
        // Auto getData of viewpager
        val update = Runnable {
            if (currentPage === numPages) {
                currentPage = 0
            }
            vpSlider.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post(update)
            }
        }, 5000, 5000)
        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(pos: Int) {}
        })
    }

}