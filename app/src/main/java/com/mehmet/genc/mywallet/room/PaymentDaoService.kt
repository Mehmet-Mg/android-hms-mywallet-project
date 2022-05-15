package com.mehmet.genc.mywallet.room

import androidx.room.*
import com.mehmet.genc.mywallet.entity.Payment

@Dao
interface PaymentDaoService {
    @Query("Select * From payment")
    suspend fun getPayments() : List<Payment>

    @Insert
    suspend fun addPayment(payment: Payment)

    @Update
    suspend fun updatePayment(payment: Payment)

    @Delete
    suspend fun deletePayment(payment: Payment)

    @Query("SELECT * FROM payment WHERE name like '%' || :keyword || '%'")
    suspend fun searchPayment(keyword: String) : List<Payment>
}