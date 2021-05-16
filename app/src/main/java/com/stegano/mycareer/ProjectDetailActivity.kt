package com.stegano.mycareer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_project.*
import java.io.InputStreamReader

class ProjectDetailActivity : AppCompatActivity() {
    val TAG = "ProjectDetailActivity"

    lateinit var mAdView : AdView

    private lateinit var CLICKED_PROJECT_NAME: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_project)

        val getProjectName = intent.getStringExtra("clicked_project_name")
        if (getProjectName != null) {
            CLICKED_PROJECT_NAME = getProjectName
        } else {
            CLICKED_PROJECT_NAME = "잘못된 경로입니다."
        }

        val ab = supportActionBar
        ab?.title = CLICKED_PROJECT_NAME

        // project.json 파일을 읽어 각 뷰의 화면을 세팅
        val gson = getGson()
        setLayout(gson)

        // 애드몹 초기화화
        // 테스트 광고시 참고 사이트 https://developers.google.com/admob/android/test-ads?hl=ko
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
    private fun getGson() : ProjectJsonSet? {
        val gson: Gson = GsonBuilder().create()
        val inputStream = assets.open("project.json")
        val inputStreamReader = InputStreamReader(inputStream)
        val gsonData = gson.fromJson(inputStreamReader, ProjectGsonSet::class.java)

        for (data in gsonData.project) {
            if(data.projectName == CLICKED_PROJECT_NAME) {
                return data
            }
        }
        return null
    }

    private fun setLayout(data: ProjectJsonSet?) {
        Picasso.get()
            .load(Uri.parse(data?.imgUri))
            .fit()
            .centerCrop()
            .error(R.drawable.notebook)
            .into(detailProjectImage)

        detailProjectName.text = "프로젝트명 : ${data?.projectName}"
        detailDevelopmentPeriod.text = data?.developmentPeriod
        detailDevelopmentPersonnel.text = data?.developmentPersonnel
        detailSkill.text = data?.skill
        detailSummary.text = data?.summary

    }
}