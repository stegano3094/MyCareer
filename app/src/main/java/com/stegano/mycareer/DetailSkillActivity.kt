package com.stegano.mycareer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailSkillActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_project)

        val a = intent.getStringExtra("click_skill")
        val ab = supportActionBar
        ab?.title = a
    }
}