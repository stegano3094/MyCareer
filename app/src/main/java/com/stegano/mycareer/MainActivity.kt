package com.stegano.mycareer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    val TAG: String = "MainActivity"
    var lastTimeBackPressed = 0L

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
                    val intent = Intent(this@MainActivity, NoticeActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        button.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailProjectActivity::class.java)
            startActivity(intent)
        }

        val aaa = gsonFromJson("sampling_project.json")

        val recyclerviewSkillList = findViewById<RecyclerView>(R.id.recyclerview_skill_list)
        recyclerviewSkillList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)  // 가로 스크롤
        val adapter = ProjectRecyclerViewAdapter(this, listOf(
            ProjectData(R.drawable.c_language, "C언어"),
            ProjectData(R.drawable.c_plus_language, "C++"),
            ProjectData(R.drawable.java_language, "자바"),
            ProjectData(R.drawable.kotlin_language, "코틀린"),
            ProjectData(R.drawable.python_language, "파이썬")
        ))
        recyclerviewSkillList.adapter = adapter
    }


    // Json 형식을 Gson 형식으로 변환하는 함수
    private fun gsonFromJson(jsonFile: String) : String? {
        val gson: Gson = GsonBuilder().create()
        val inputStream: InputStream = assets.open(jsonFile)
        val reader: InputStreamReader = InputStreamReader(inputStream)
        val gsonData = gson.fromJson(reader, GsonDataSetting::class.java)  // Json 데이터(reader)를 Gson으로 변경

        Log.e(TAG, inputStream.read().toString())
        Log.e(TAG, reader.toString())

        for(detailData in gsonData.sample_project) {
            Log.e(TAG, detailData.toString())
            return detailData.name
        }

        return null
    }

    // 뒤로가기 버튼에 대한 함수
    override fun onBackPressed() {
        val finishBackPressedTime = 1500L  // 1.5초
        if(System.currentTimeMillis() - lastTimeBackPressed < finishBackPressedTime) {
            finish()
            return
        }

        lastTimeBackPressed = System.currentTimeMillis()
        Toast.makeText(this, "'뒤로'버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
    }
}