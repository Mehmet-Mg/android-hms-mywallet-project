package com.mehmet.genc.mywallet.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.mehmet.genc.mywallet.entity.Payment
import com.mehmet.genc.mywallet.room.MyWalletDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentRepository(var context: Context) {
    var paymentList: MutableLiveData<List<Payment>>
    var database: MyWalletDatabase

    init {
        paymentList = MutableLiveData()
        database = MyWalletDatabase.connectDatabase(context)!!
    }

    fun getPayments() : MutableLiveData<List<Payment>> {
        return paymentList
    }

    fun getAllPayments() {
        CoroutineScope(Dispatchers.Main).launch {
            paymentList.value = database.paymentDaoService().getPayments()
        }
    }

    fun addPayment(payment: Payment) {
        CoroutineScope(Dispatchers.Main).launch {
            database.paymentDaoService().addPayment(payment)
        }
        getAllPayments()
    }

    fun updatePayment(payment: Payment) {
        CoroutineScope(Dispatchers.Main).launch {
            database.paymentDaoService().updatePayment(payment)
        }
    }

    fun deletePayment(payment: Payment) {
        CoroutineScope(Dispatchers.Main).launch {
            database.paymentDaoService().deletePayment(payment)
        }
        getAllPayments()
    }

    fun searchPayment(keyword: String) {
        CoroutineScope(Dispatchers.Main).launch {
            paymentList.value = database.paymentDaoService().searchPayment(keyword)
        }
    }
}