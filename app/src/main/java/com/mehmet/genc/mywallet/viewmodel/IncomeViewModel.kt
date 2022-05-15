package com.mehmet.genc.mywallet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mehmet.genc.mywallet.entity.Income
import com.mehmet.genc.mywallet.entity.Payment
import com.mehmet.genc.mywallet.repo.IncomeRepository

class IncomeViewModel(application: Application) : AndroidViewModel(application) {
    var incomeList: MutableLiveData<List<Income>>
    var incomeRepo = IncomeRepository(application)

    init {
        getIncomes()
        incomeList = incomeRepo.getIncomes()
    }

    fun getIncomes() {
        incomeRepo.getAllIncomes()
    }

    fun addIncome(income: Income) {
        incomeRepo.addIncome(income)
    }

    fun updateIncome(income: Income) {
        incomeRepo.updateIncome(income)
    }

    fun deleteIncome(income: Income) {
        incomeRepo.deleteIncome(income)
    }

    fun searchIncome(keyword: String) {
        incomeRepo.searchIncome(keyword)
    }
}