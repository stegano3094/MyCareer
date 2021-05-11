package com.stegano.mycareer

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {
    val TAG = "SplashActivity"
    var timer: TimerTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)  // 화면 꺼짐 방지

        // 스플래쉬 화면의 이미지에 애니메이션 처리함
        splashMainImage.startAnimation(AnimationUtils.loadAnimation(this@SplashActivity, R.anim.splash_animation))

        // 스플래쉬 화면에서 3초간격으로 네트워크를 확인함
        Toast.makeText(this@SplashActivity, "인터넷 \"확인 중\" 입니다.", Toast.LENGTH_SHORT).show()
        timer = Timer().schedule(3000, 3000) {
            when(checkNetwork()) {
                true -> {  // 인터넷에 연결되어 있는 경우
                    runOnUiThread {  // ui 변경이 있어서 Thread에서 처리함
                        setClose()  // 타이머 및 애니메이션 종료
                        Toast.makeText(
                            this@SplashActivity,
                            "인터넷 연결을 확인하였습니다.",
                            Toast.LENGTH_SHORT)
                            .show()
                        moveMainActivity()  // 메인 엑티비티로 전환
                    }
                }
                false -> { // 인터넷에 연결되어 있지 않은 경우 3초간 계속 확인
                    runOnUiThread {
                        setClose()  // 타이머 및 애니메이션 종료
                        Toast.makeText(
                            this@SplashActivity,
                            "인터넷이 연결되어 있지 않습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }
            }

        }

    }

    // 인터넷에 연결되어 있는지 확인하는 함수
    private fun checkNetwork(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        when(connectivityManager.activeNetworkInfo?.isConnected) {
            true -> {  // 인터넷에 연결되어 있는 경우
                Log.e(TAG, "checkNetwork: 인터넷에 연결되어 있습니다.")
                return true
            }
            null -> {  // 인터넷에 연결되어 있지 않은 경우
                Log.e(TAG, "checkNetwork: 인터넷에 연결되어 있지 않습니다.")
                return false
            }
        }

        return false
    }

    // 액티비티 화면 이동하는 함수
    private fun moveMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()  // 스플래쉬 화면이므로 이 화면은 스택에서 지움
    }

    // 타이머 및 애니메이션 종료
    private fun setClose() {
        splashMainImage.clearAnimation()
        timer?.cancel()
    }
}