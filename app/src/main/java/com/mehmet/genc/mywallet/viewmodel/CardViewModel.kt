package com.mehmet.genc.mywallet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mehmet.genc.mywallet.entity.Card
import com.mehmet.genc.mywallet.entity.Payment
import com.mehmet.genc.mywallet.repo.CardRepository

class CardViewModel(application: Application) : AndroidViewModel(application) {
    var cardList: MutableLiveData<List<Card>>
    var cardRepo = CardRepository(application)

    init {
        getCards()
        cardList = cardRepo.getCards()
    }

    fun getCards() {
        cardRepo.getAllCards()
    }

    fun addCard(card: Card) {
        cardRepo.addCard(card)
    }

    fun updateCard(card: Card) {
        cardRepo.updateCard(card)
    }

    fun deleteCard(card: Card) {
        cardRepo.deleteCard(card)
        getCards()
    }

    fun searchCard(keyword: String) {
        cardRepo.searchCard(keyword)
    }
}