package com.mehmet.genc.mywallet.fragment.receipt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mehmet.genc.mywallet.R
import com.mehmet.genc.mywallet.databinding.FragmentReceiptDetailBinding
import com.mehmet.genc.mywallet.databinding.ReceiptViewHolderBinding
import com.mehmet.genc.mywallet.entity.Payment
import com.mehmet.genc.mywallet.viewmodel.PaymentViewModel

class ReceiptDetailFragment : Fragment() {
    private lateinit var binding: FragmentReceiptDetailBinding
    private lateinit var viewModel: PaymentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_receipt_detail, container, false)

        binding.receiptDetailFragment = this
        val bundle: ReceiptDetailFragmentArgs by navArgs()
        val payment = bundle.payment

        binding.payment = payment

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: PaymentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun updatePayment(paymentId: Int, paymentName: String, paymentAmount: String, paymentDate: String) {
        viewModel.updatePayment(Payment(paymentId, paymentName, paymentAmount, paymentDate))
        Toast.makeText(requireContext(), this.getText(R.string.payment_updated), Toast.LENGTH_SHORT).show()
    }

}