package com.dazhou.blind.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
    }

    private fun initAdapter() {
        val roomRecyclerView: RecyclerView = findViewById(R.id.roomRecyclerView)
        roomRecyclerView.layoutManager = GridLayoutManager(this, 2)
        val giftAdapter = TestSVGAAdapter(this)
        roomRecyclerView.adapter = giftAdapter
    }

}