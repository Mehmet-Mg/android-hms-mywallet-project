package com.mehmet.genc.mywallet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mehmet.genc.mywallet.entity.Payment
import com.mehmet.genc.mywallet.repo.PaymentRepository

class PaymentViewModel(application: Application) : AndroidViewModel(application) {
    var paymentList: MutableLiveData<List<Payment>>
    var paymentRepo = PaymentRepository(application)

    init {
        getPayments()
        paymentList = paymentRepo.getPayments()
    }

    fun getPayments() {
        paymentRepo.getAllPayments()
    }

    fun addPayment(payment: Payment) {
        paymentRepo.addPayment(payment)
    }

    fun updatePayment(payment: Payment) {
        paymentRepo.updatePayment(payment)
    }

    fun deletePayment(payment: Payment) {
        paymentRepo.deletePayment(payment)
    }

    fun searchPayment(keyword: String) {
        paymentRepo.searchPayment(keyword)
    }
}