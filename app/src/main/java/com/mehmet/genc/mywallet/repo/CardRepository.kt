package com.mehmet.genc.mywallet.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.mehmet.genc.mywallet.entity.Card
import com.mehmet.genc.mywallet.room.MyWalletDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardRepository(var context: Context) {
    var cardList: MutableLiveData<List<Card>>
    var database: MyWalletDatabase

    init {
        cardList = MutableLiveData()
        database = MyWalletDatabase.connectDatabase(context)!!
    }

    fun getCards() : MutableLiveData<List<Card>> {
        return cardList
    }

    fun getAllCards() {
        CoroutineScope(Dispatchers.Main).launch {
            cardList.value = database.cardDaoService().getCards()
        }
    }

    fun addCard(card: Card) {
        CoroutineScope(Dispatchers.Main).launch {
            database.cardDaoService().addCard(card)
        }
    }

    fun updateCard(card: Card) {
        CoroutineScope(Dispatchers.Main).launch {
            database.cardDaoService().updateCard(card)
        }
    }

    fun deleteCard(card: Card) {
        CoroutineScope(Dispatchers.Main).launch {
            database.cardDaoService().deleteCard(card)
        }
        getAllCards()
    }

    fun searchCard(keyword: String) {
        CoroutineScope(Dispatchers.Main).launch {
            cardList.value = database.cardDaoService().searchCard(keyword)
        }
    }
}