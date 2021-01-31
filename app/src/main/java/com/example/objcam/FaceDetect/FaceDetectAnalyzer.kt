package com.example.objcam.FaceDetect

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceContour
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions

class FaceDetectAnalyzer :ImageAnalysis.Analyzer{
    val detector=FaceDetection.getClient(
        FaceDetectorOptions.Builder()
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
            .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
            .build()
    )


    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        Log.d("FACE","image  analysed")
        imageProxy.image?.let {
            val inputImage= InputImage.fromMediaImage(it
                ,imageProxy.imageInfo.rotationDegrees)

            detector.process(inputImage).addOnSuccessListener {faces->
                Log.d("FACE","Faces = ${faces.size} ")

                faces.forEach{

                    Log.d("FACE","""
                     lefteye  ${it.leftEyeOpenProbability}
                     righteye ${it.rightEyeOpenProbability}
                     smile ${it.smilingProbability}
                    """.trimIndent())
                    val leftEyeContour = it.getContour(FaceContour.LEFT_EYE)?.points
                    val upperLipBottomContour = it.getContour(FaceContour.UPPER_LIP_BOTTOM)?.points




                }

            }.addOnFailureListener {
                Log.e("FACE","FAIL")

            }.addOnCompleteListener {
                imageProxy.close()
            }

        } ?: imageProxy.close()

    }
}