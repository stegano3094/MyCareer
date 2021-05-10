package com.stegano.mycareer

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        checkNetwork()
    }

    // 인터넷에 연결되어 있는지 확인하는 함수
    private fun checkNetwork() {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        when(connectivityManager.activeNetworkInfo?.isConnected) {
            true -> {  // 인터넷에 연결되어 있는 경우
                println("인터넷에 연결되어 있습니다.")
            }
            null -> {  // 인터넷에 연결되어 있지 않은 경우
                println("인터넷에 연결되어 있지 않습니다.")
            }
        }
    }

    // 액티비티 화면 이동하는 함수
    private fun moveMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()  // 스플래쉬 화면이므로 이 화면은 스택에서 지움
    }
}