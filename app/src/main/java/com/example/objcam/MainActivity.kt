package com.example.objcam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.objcam.Barcode.BarcodeActivity
import com.example.objcam.FaceDetect.FaceDetectActivity
import com.example.objcam.ImageLabeler.ImageLableingActivity
import com.example.objcam.ImageLabeler.textrecog.TextRecognitionActivity
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {

            startActivity(Intent(this,CameraActivity::class.java))

        }


        barcode.setOnClickListener {
            startActivity(Intent(this,BarcodeActivity::class.java))
        }


        FaceDetect.setOnClickListener {
            startActivity(Intent(this,FaceDetectActivity::class.java))
        }

        Labeler.setOnClickListener {
            startActivity(Intent(this,ImageLableingActivity::class.java))
        }

        Text.setOnClickListener {
            startActivity(Intent(this,TextRecognitionActivity::class.java))
        }

    }

}