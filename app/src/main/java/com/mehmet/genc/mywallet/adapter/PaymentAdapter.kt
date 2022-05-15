package com.mehmet.genc.mywallet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mehmet.genc.mywallet.databinding.ReceiptViewHolderBinding
import com.mehmet.genc.mywallet.entity.Payment
import com.mehmet.genc.mywallet.fragment.receipt.ReceiptFragmentDirections
import com.mehmet.genc.mywallet.viewmodel.PaymentViewModel

class PaymentAdapter(var context: Context, var paymentList: List<Payment>, var viewModel: PaymentViewModel)
    : RecyclerView.Adapter<PaymentAdapter.CardViewHolder>() {

    inner class CardViewHolder(binding: ReceiptViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ReceiptViewHolderBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ReceiptViewHolderBinding.inflate(layoutInflater, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val payment = paymentList[position]
        val mHolder = holder.binding
        mHolder.payment = payment

        mHolder.deleteButton.setOnClickListener {
            Snackbar.make(it, "Are you sure?", Snackbar.LENGTH_SHORT).setAction("Yes"){
                viewModel.deletePayment(payment)
            }.show()
        }

        mHolder.paymentCardView.setOnClickListener {
            val action = ReceiptFragmentDirections.actionReceiptFragmentToReceiptDetailFragment(payment)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = paymentList.size
}