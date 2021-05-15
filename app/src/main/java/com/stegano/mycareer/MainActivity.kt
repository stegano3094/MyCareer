package com.stegano.mycareer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*
import java.io.InputStream
import java.io.InputStreamReader

/**
 * 개발 기간 : 20210510 ~ 2020515
 */

class MainActivity : AppCompatActivity() {
    val TAG: String = "MainActivity"
    var lastTimeBackPressed = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 커스텀 앱바
        CustomTopAppBar()

        // career.json 파일을 읽어 Career로 표시함
        career_textview.text = stringFromJson("career.json")

        // 기술 스택 중 언어를 리사이클러뷰에 넣음
        RecyclerViewLanguage()

        // 기술 스택 중 언어를 리사이클러뷰에 넣음
        RecyclerViewDatabase()

        // project.json 파일을 읽어 리사이클러뷰에 프로젝트를 표시함
        RecyclerViewproject()

        // 깃허브 링크 연결
        val link = github_text_link.text.toString()
        github_text_link.setOnClickListener {
            val intentForGithub = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(intentForGithub)
        }
    }

    // 커스텀 앱바
    private fun CustomTopAppBar() {
        topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.notice -> {
                    val intent = Intent(this@MainActivity, NoticeActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    // 기술 스택 중 언어를 리사이클러뷰에 넣음
    private fun RecyclerViewLanguage() : Unit {
        val recyclerviewLanguageList = findViewById<RecyclerView>(R.id.recyclerview_language_list)
        recyclerviewLanguageList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)  // 가로 스크롤
        val languageSkillsAdapter = SkillsAdapter(this, listOf(
            SkillData(R.drawable.c_language, "C언어"),
            SkillData(R.drawable.c_plus_language, "C++"),
            SkillData(R.drawable.java_language, "자바"),
            SkillData(R.drawable.kotlin_language, "코틀린"),
            SkillData(R.drawable.python_language, "파이썬")
        ))
        recyclerviewLanguageList.adapter = languageSkillsAdapter
    }

    // 기술 스택 중 언어를 리사이클러뷰에 넣음
    private fun RecyclerViewDatabase() {
        val recyclerviewDatabaseList = findViewById<RecyclerView>(R.id.recyclerview_database_list)
        recyclerviewDatabaseList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)  // 가로 스크롤
        val databaseSkillsAdapter = SkillsAdapter(this, listOf(
            SkillData(R.drawable.sqlite, "SQLite"),
            SkillData(R.drawable.firebase_realtime_database, "파이어베이스"),
            SkillData(R.drawable.room, "Room")
        ))
        recyclerviewDatabaseList.adapter = databaseSkillsAdapter
    }

    // project.json 파일을 읽어 리사이클러뷰에 프로젝트를 표시함
    private fun RecyclerViewproject() {
        val projectList = projectListFromProjectJson("project.json")
        Log.e(TAG, "projectList : ${projectList}")
        val recyclerviewProjectList = findViewById<RecyclerView>(R.id.recyclerview_project_list)
        recyclerviewProjectList.layoutManager = LinearLayoutManager(this)
        val projectAdapter = ProjectAdapter(this, projectList)
        recyclerviewProjectList.adapter = projectAdapter
    }

    // Json 형식받아 Gson 형식으로 변환하는 함수, revised in 20210514
    private fun stringFromJson(jsonFile: String) : String {
        val gson: Gson = GsonBuilder().create()
        val inputStream: InputStream = assets.open(jsonFile)
        val reader: InputStreamReader = InputStreamReader(inputStream)
        val gsonData = gson.fromJson(reader, CareerGsonSet::class.java)  // Json 데이터(reader)를 Gson으로 변경
        var data = ""

        for(detailData in gsonData.my_career) {
            data += "${detailData.date}\n\t\t${detailData.content}\n\n"
        }
        return data
    }

    // Json 형식받아 Gson 형식으로 변환하고 projectList로 반환하는 함수, revised in 20210514
    private fun projectListFromProjectJson(jsonFile: String) : MutableList<ProjectData> {
        val gson: Gson = GsonBuilder().create()
        val inputStream: InputStream = assets.open(jsonFile)
        val reader: InputStreamReader = InputStreamReader(inputStream)
        val gsonData = gson.fromJson(reader, ProjectGsonSet::class.java)  // Json 데이터(reader)를 Gson으로 변경
//        Log.e(TAG, inputStream.read().toString())
//        Log.e(TAG, reader.toString())

        val projectList = mutableListOf<ProjectData>()
        for(detailData in gsonData.project) {
//            Log.e(TAG, detailData.toString())
            projectList.add(ProjectData(detailData.projectName, detailData.skill, detailData.imgUri))
        }

        return projectList
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