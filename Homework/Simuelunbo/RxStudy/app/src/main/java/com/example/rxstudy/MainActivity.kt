package com.example.rxstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvExampleList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvExampleList = findViewById(R.id.rv_example_list)
        rvExampleList.apply {
            adapter = MainAdapter(this@MainActivity)
        }
    }
}