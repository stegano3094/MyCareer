package com.stegano.mycareer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ProjectDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_project)

        val a = intent.getStringExtra("clicked_project_name")
        val ab = supportActionBar
        ab?.title = a
    }
}