package com.example.objcam.ImageLabeler

import androidx.core.content.ContextCompat
import com.example.objcam.BaseLensActivity

class ImageLableingActivity : BaseLensActivity() {
    override val imageAnalyzer=ImageLabelAnalyzer()

    override fun startScanner() {
        startImageLabeling()
    }

    private fun startImageLabeling() {
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
    }
}