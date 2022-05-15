package com.mehmet.genc.mywallet.room

import androidx.room.*
import com.mehmet.genc.mywallet.entity.Income

@Dao
interface IncomeDaoService {
    @Query("Select * from income")
    suspend fun getIncomes() : List<Income>

    @Insert
    suspend fun addIncome(income: Income)

    @Update
    suspend fun updateIncome(income: Income)

    @Delete
    suspend fun deleteIncome(income: Income)

    @Query("SELECT * FROM income WHERE name like '%' || :keyword || '%'")
    suspend fun searchIncome(keyword: String) : List<Income>
}