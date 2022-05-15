package com.mehmet.genc.mywallet.recognitionservice

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.huawei.agconnect.AGConnectOptionsBuilder
import com.huawei.hms.mlsdk.MLAnalyzerFactory
import com.huawei.hms.mlsdk.common.MLApplication
import com.huawei.hms.mlsdk.common.MLFrame
import com.huawei.hms.mlsdk.text.MLRemoteTextSetting
import com.mehmet.genc.mywallet.entity.Payment
import com.mehmet.genc.mywallet.viewmodel.PaymentViewModel
import java.io.IOException

class TextRecognition(var context: Context, var viewModel: PaymentViewModel) {

    init {
        MLApplication.getInstance().apiKey = AGConnectOptionsBuilder().build(context).getString("client/api_key")
    }

    fun analyzeText(bitmap: Bitmap) {
        val languageList: MutableList<String?> = ArrayList<String?>()
        languageList.add("tr")
        languageList.add("en")

        val setting = MLRemoteTextSetting.Factory()
            .setTextDensityScene(MLRemoteTextSetting.OCR_COMPACT_SCENE)
            .setLanguageList(languageList)
            .setBorderType(MLRemoteTextSetting.NGON)
            .create()

        val analyzer = MLAnalyzerFactory.getInstance().getRemoteTextAnalyzer(setting)
        val frame = MLFrame.fromBitmap(bitmap)

        val task = analyzer!!.asyncAnalyseFrame(frame)
        task.addOnSuccessListener {
            val items = it.stringValue
            viewModel.addPayment(
                Payment(
                    0,
                    "${items.lines().get(0)}",
                    getAmount(items),
                    getDate(items))
            )

        }.addOnFailureListener {
            Log.d("TextReco", "Başarısız")
        }

        if(analyzer!=null) {
            try {
                analyzer.stop()
            } catch (e: IOException) {

            }
        }
    }
    private fun getDate(str: String): String {
        val regex = Regex("""\d{2}[\\. /-]\d{2}[\\. /-]\d{4}""")
        val dateList: Sequence<MatchResult> = regex.findAll(str)
        var date: String = ""
        dateList.forEach {
            date = it.value.toString()
        }
        return date
    }

    private fun getAmount(str: String): String {
        val regex1 = Regex("\\*\\d*,\\d{2}")
        val list = ArrayList<Double>()
        val amount: Sequence<MatchResult> = regex1.findAll(str)
        var total: Double = 0.0

        amount.forEach {
            list.add(
                it.value
                .replace("*", "")
                .replace(",", ".")
                .toDouble())
        }

        try {
            val lastIndex = list.lastIndex
            total = list[lastIndex-1] - list[lastIndex]
            if(total == list[lastIndex-2]) {
                return total.toString()
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
            total = 0.0
        }
        return ""
    }
}