package com.mehmet.genc.mywallet.room

import androidx.room.*
import com.mehmet.genc.mywallet.entity.Card

@Dao
interface CardDaoService {
    @Query("Select * From card")
    suspend fun getCards() : List<Card>

    @Insert
    suspend fun addCard(card: Card)

    @Update
    suspend fun updateCard(card: Card)

    @Delete
    suspend fun deleteCard(card: Card)

    @Query("SELECT * FROM card WHERE name like '%' || :keyword || '%'")
    suspend fun searchCard(keyword: String) : List<Card>
}