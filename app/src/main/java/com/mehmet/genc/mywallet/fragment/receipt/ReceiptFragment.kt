package com.mehmet.genc.mywallet.fragment.receipt

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.mehmet.genc.mywallet.R
import com.mehmet.genc.mywallet.adapter.PaymentAdapter
import com.mehmet.genc.mywallet.databinding.FragmentReceiptBinding
import com.mehmet.genc.mywallet.recognitionservice.TextRecognition
import com.mehmet.genc.mywallet.viewmodel.PaymentViewModel

class ReceiptFragment : Fragment() {
    private val PICK_IMAGE_REQUEST = 1001
    private val CAPTURE_IMAGE_REQUEST = 1002

    private lateinit var binding: FragmentReceiptBinding
    private lateinit var viewModel: PaymentViewModel
    private lateinit var bitmap: Bitmap
    private lateinit var textRecognition: TextRecognition

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_receipt, container, false)

        binding.receiptFragment = this

        viewModel.paymentList.observe(viewLifecycleOwner) {
            val adapter = PaymentAdapter(requireContext(), it, viewModel)
            binding.paymentAdapter = adapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: PaymentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun requestBitmap() {
        Intent(Intent.ACTION_GET_CONTENT).also {
            it.type = "image/*"
            startActivityForResult(it, PICK_IMAGE_REQUEST)
        }
    }

    fun captureBitmap() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(intent, CAPTURE_IMAGE_REQUEST)
        } catch (e: ActivityNotFoundException) {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        textRecognition = TextRecognition(requireContext(), viewModel)

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data.also {
                bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, it)
                if (bitmap != null) {
                    textRecognition.analyzeText(bitmap)
                }
            }
        }
        if(requestCode == CAPTURE_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val bitmap = data?.extras?.get("data") as Bitmap
            if(bitmap != null) {
                textRecognition.analyzeText(bitmap)
            }
        }

    }
}