package com.mehmet.genc.mywallet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mehmet.genc.mywallet.entity.Card
import com.mehmet.genc.mywallet.entity.Income
import com.mehmet.genc.mywallet.entity.Payment
import com.mehmet.genc.mywallet.repo.CardRepository
import com.mehmet.genc.mywallet.repo.IncomeRepository
import com.mehmet.genc.mywallet.repo.PaymentRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val paymentList: MutableLiveData<List<Payment>>
    val cardList: MutableLiveData<List<Card>>

    val cardRepo = CardRepository(application)
    val paymentRepo = PaymentRepository(application)

    init {
        getCards()
        getPayments()
        paymentList = paymentRepo.getPayments()
        cardList = cardRepo.getCards()
    }

    fun getCards() {
        cardRepo.getAllCards()
    }

    fun getPayments() {
        paymentRepo.getAllPayments()
    }
}