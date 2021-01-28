package com.example.objcam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.objcam.Barcode.BarcodeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            startActivity(Intent(this,CameraActivity::class.java))
        }


        barcode.setOnClickListener {
            startActivity(Intent(this,BarcodeActivity::class.java))
        }

    }
}