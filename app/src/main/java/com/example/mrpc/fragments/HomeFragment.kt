package com.example.mrpc.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mrpc.R
import com.example.mrpc.RakitHomeList
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moveTo_rakitHomeList_btn.setOnClickListener {
            val inten = Intent(activity,RakitHomeList::class.java)
            startActivity(inten)
        }
    }

}