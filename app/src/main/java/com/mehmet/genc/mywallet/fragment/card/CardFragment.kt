package com.mehmet.genc.mywallet.fragment.card

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.huawei.hms.mlplugin.card.bcr.MLBcrCapture
import com.huawei.hms.mlplugin.card.bcr.MLBcrCaptureConfig
import com.huawei.hms.mlplugin.card.bcr.MLBcrCaptureFactory
import com.huawei.hms.mlplugin.card.bcr.MLBcrCaptureResult
import com.mehmet.genc.mywallet.R
import com.mehmet.genc.mywallet.adapter.CardAdapter
import com.mehmet.genc.mywallet.databinding.FragmentCardBinding
import com.mehmet.genc.mywallet.entity.Card
import com.mehmet.genc.mywallet.recognitionservice.BankCardRecognition
import com.mehmet.genc.mywallet.viewmodel.CardViewModel

class CardFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding
    private lateinit var viewModel: CardViewModel
    private lateinit var bankCardRecognition: BankCardRecognition

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_card, container, false)

        binding.cardFragment = this

        viewModel.cardList.observe(viewLifecycleOwner) {
            val adapter = CardAdapter(requireContext(), it, viewModel)
            binding.cardAdapter = adapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CardViewModel by viewModels()
        viewModel = tempViewModel
    }


    fun startCaptureActivity() {
        bankCardRecognition = BankCardRecognition(requireActivity(), viewModel)
        bankCardRecognition.startCaptureActivity()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCards()
    }
}