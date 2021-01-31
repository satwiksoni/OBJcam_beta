package com.example.objcam.Barcode

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.example.objcam.BaseLensActivity
import com.example.objcam.R
import kotlinx.android.synthetic.main.activity_camera.previewView
import kotlinx.android.synthetic.main.activity_lens.*
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class BarcodeActivity : BaseLensActivity() {

    companion object {
        @JvmStatic
        val CAMERA_PERM_CODE = 422
    }


     override val imageAnalyzer=BarcodeAnalyzer()
    override fun startScanner() {
        scanBarcode()
    }


    private fun scanBarcode() {

         imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
    }

}




//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_barcode)
//
//
