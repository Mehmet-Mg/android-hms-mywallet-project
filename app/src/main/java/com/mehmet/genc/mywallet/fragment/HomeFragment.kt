package com.mehmet.genc.mywallet.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.huawei.hms.mlplugin.card.bcr.MLBcrCapture
import com.huawei.hms.mlplugin.card.bcr.MLBcrCaptureConfig
import com.huawei.hms.mlplugin.card.bcr.MLBcrCaptureFactory
import com.huawei.hms.mlplugin.card.bcr.MLBcrCaptureResult
import com.mehmet.genc.mywallet.R
import com.mehmet.genc.mywallet.databinding.FragmentHomeBinding
import com.mehmet.genc.mywallet.entity.Card
import com.mehmet.genc.mywallet.entity.Income
import com.mehmet.genc.mywallet.viewmodel.CardViewModel
import com.mehmet.genc.mywallet.viewmodel.IncomeViewModel
import com.mehmet.genc.mywallet.viewmodel.PaymentViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: IncomeViewModel
    var sa: Int = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewModel.incomeList.observe(viewLifecycleOwner) {
            for (i in it) {
                Log.d("Deeneme", "${i.id}  ${i.name}")
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: IncomeViewModel by viewModels()
        viewModel = tempViewModel

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardViewCards.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cardFragment)
        }

        binding.cardViewPayments.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_receiptFragment)
        }
    }
}