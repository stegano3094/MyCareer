package com.stegano.mycareer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add1 -> {
                    Toast.makeText(applicationContext, "메뉴1를 클릭하셨습니다.", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.add2 -> {
                    Toast.makeText(applicationContext, "메뉴2를 클릭하셨습니다.", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.notice -> {
                    Toast.makeText(applicationContext, "공지사항을 클릭하셨습니다.", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        button.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailProjectActivity::class.java)
            startActivity(intent)
        }
    }
}