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
import com.example.objcam.R
import kotlinx.android.synthetic.main.activity_barcode.*
import kotlinx.android.synthetic.main.activity_camera.previewView
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class BarcodeActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        val CAMERA_PERM_CODE = 422
    }


     val imageAnalyzer=BarcodeAnalyzer()
lateinit var imageAnalysis: ImageAnalysis

    private fun askCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
            CAMERA_PERM_CODE)
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(
            Runnable {
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(previewBarcode.getSurfaceProvider())
                    }
                imageAnalysis=ImageAnalysis.Builder()
                    .build()

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)
                } catch (ex: Exception) {
                    Log.e("CAM", "Error bindind camera", ex)
                }

            },
            ContextCompat.getMainExecutor(this)

        )

    }

    private fun scanBarcode() {

         imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)

        askCameraPermission()
        btnStartScanner.setOnClickListener {
            scanBarcode()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Permission Error")
                    .setMessage("Camera Permission not provided")
                    .setPositiveButton("OK") { _, _ -> finish() }
                    .setCancelable(false)
                    .show()
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}




//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_barcode)
//
//
