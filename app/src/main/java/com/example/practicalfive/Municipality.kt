package com.example.practicalfive

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Municipality : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_municipality)

        val tvName: TextView = findViewById(R.id.tv_muni_name)
        val ivPic: ImageView = findViewById(R.id.iv_muni_pic)
        val tvInfo: TextView = findViewById(R.id.tv_info_text)
        val btnVisit: Button = findViewById(R.id.btn_visit_site)

        // 获取传递过来的数据，和MainActivity共用同一个key
        val rawName = intent.getStringExtra(MainActivity.EXTRA_MUNICIPALITY_NAME)
        val processor = TextProcessor()
        val muniName = processor.processMunicipality(rawName)

        // 二次校验，非法数据直接关闭页面
        if (muniName == null) {
            Toast.makeText(this, "Bad municipality data, return to main screen", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // 读取字符串数组
        val muniDesc = resources.getStringArray(R.array.municipalities_array)

        // 预先声明四个变量，不再使用Triple
        var colorResId: Int = 0
        var imgResId: Int = 0
        var infoText: String = ""
        var targetUrl: String = ""

        when (muniName) {
            "Beijing" -> {
                colorResId = R.color.municipality_beijing
                imgResId = R.drawable.beijing
                infoText = muniDesc[0]
                targetUrl = "https://english.beijing.gov.cn/"
            }
            "Shanghai" -> {
                colorResId = R.color.municipality_shanghai
                imgResId = R.drawable.shanghai
                infoText = muniDesc[1]
                targetUrl = "https://english.shanghai.gov.cn/"
            }
            "Chongqing" -> {
                colorResId = R.color.municipality_chongqing
                imgResId = R.drawable.chongqing
                infoText = muniDesc[2]
                targetUrl = "https://english.cq.gov.cn/"
            }
            "Tianjin" -> {
                colorResId = R.color.municipality_tianjin
                imgResId = R.drawable.tianjin
                infoText = muniDesc[3]
                targetUrl = "https://en.tj.gov.cn/"
            }
            else -> {
                Toast.makeText(this, "Unknown municipality", Toast.LENGTH_SHORT).show()
                finish()
                return
            }
        }

        // 动态设置UI
        tvName.text = muniName
        tvName.setBackgroundColor(resources.getColor(colorResId))
        tvInfo.text = infoText
        tvInfo.setTextColor(resources.getColor(colorResId))
        ivPic.setImageResource(imgResId)
        btnVisit.backgroundTintList = resources.getColorStateList(colorResId)

        // 隐式Intent访问网页，判断有无浏览器
        btnVisit.setOnClickListener {
            val uri = Uri.parse(targetUrl)
            val webIntent = Intent(Intent.ACTION_VIEW, uri)
            if (webIntent.resolveActivity(packageManager) != null) {
                startActivity(webIntent)
            } else {
                Toast.makeText(this, "No browser app available", Toast.LENGTH_SHORT).show()
            }
        }
    }
}