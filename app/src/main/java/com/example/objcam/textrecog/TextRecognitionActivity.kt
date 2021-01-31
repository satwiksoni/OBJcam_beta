package com.example.objcam.ImageLabeler.textrecog

import androidx.camera.core.ImageAnalysis
import androidx.core.content.ContextCompat
import com.example.objcam.BaseLensActivity

class TextRecognitionActivity : BaseLensActivity() {
    override val imageAnalyzer = TextAnalyzer()

    override fun startScanner() {
        startTextRecognition()
    }

    private fun startTextRecognition() {
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
    }

}