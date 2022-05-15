package com.mehmet.genc.mywallet.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.mehmet.genc.mywallet.entity.Income
import com.mehmet.genc.mywallet.room.MyWalletDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncomeRepository(var context: Context) {
    var incomeList: MutableLiveData<List<Income>>
    var database: MyWalletDatabase

    init {
        incomeList = MutableLiveData()
        database = MyWalletDatabase.connectDatabase(context)!!
    }

    fun getIncomes() : MutableLiveData<List<Income>> {
        return incomeList
    }

    fun getAllIncomes() {
        CoroutineScope(Dispatchers.Main).launch {
            incomeList.value = database.incomeDaoService().getIncomes()
        }
    }

    fun addIncome(income: Income) {
        CoroutineScope(Dispatchers.Main).launch {
            database.incomeDaoService().addIncome(income)
        }
    }

    fun updateIncome(income: Income) {
        CoroutineScope(Dispatchers.Main).launch {
            database.incomeDaoService().updateIncome(income)
        }
    }

    fun deleteIncome(income: Income) {
        CoroutineScope(Dispatchers.Main).launch {
            database.incomeDaoService().deleteIncome(income)
        }
        getAllIncomes()
    }

    fun searchIncome(keyword: String) {
        CoroutineScope(Dispatchers.Main).launch {
            incomeList.value = database.incomeDaoService().searchIncome(keyword)
        }
    }
}