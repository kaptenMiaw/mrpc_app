package com.example.mrpc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mrpc.fragments.FavFragment
import com.example.mrpc.fragments.HomeFragment
import com.example.mrpc.fragments.SettFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val favFragment = FavFragment()
        val settFragment = SettFragment()

        makeCurentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_ic -> makeCurentFragment(homeFragment)
                R.id.sett_ic -> makeCurentFragment(settFragment)
                R.id.fav_ic -> makeCurentFragment(favFragment)
            }
            true
        }
    }
    private fun makeCurentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_container, fragment)
            commit()
        }
}