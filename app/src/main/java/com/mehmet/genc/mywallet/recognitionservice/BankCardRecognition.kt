package com.mehmet.genc.mywallet.recognitionservice

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import com.mehmet.genc.mywallet.R
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.huawei.hms.mlplugin.card.bcr.MLBcrCapture
import com.huawei.hms.mlplugin.card.bcr.MLBcrCaptureConfig
import com.huawei.hms.mlplugin.card.bcr.MLBcrCaptureFactory
import com.huawei.hms.mlplugin.card.bcr.MLBcrCaptureResult
import com.mehmet.genc.mywallet.entity.Card
import com.mehmet.genc.mywallet.viewmodel.CardViewModel

class BankCardRecognition(var activity: Activity, var viewModel: CardViewModel) {


    private val callback: MLBcrCapture.Callback = object : MLBcrCapture.Callback {
        override fun onSuccess(bankCardResult: MLBcrCaptureResult?) {
            viewModel.addCard(
                Card(
                0,
                "New Card",
                "${bankCardResult?.number}",
                "${bankCardResult?.organization}",
                "${bankCardResult?.expire}")
            )
            Toast.makeText(activity.applicationContext, activity.applicationContext.getText(R.string.successful), Toast.LENGTH_SHORT).show()
        }

        override fun onCanceled() {}

        override fun onFailure(p0: Int, p1: Bitmap?) {
            Toast.makeText(activity.applicationContext, activity.applicationContext.getText(R.string.failure), Toast.LENGTH_SHORT).show()
        }

        override fun onDenied() {}

    }

    fun startCaptureActivity() {

        val config = MLBcrCaptureConfig.Factory()
            .setResultType(MLBcrCaptureConfig.RESULT_ALL)
            .setOrientation(MLBcrCaptureConfig.ORIENTATION_AUTO)
            .create()
        val bankCardCapture = MLBcrCaptureFactory.getInstance().getBcrCapture(config)
        bankCardCapture.captureFrame(activity, callback)
    }

}