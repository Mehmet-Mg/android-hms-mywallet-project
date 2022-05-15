package com.mehmet.genc.mywallet.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "payment")
data class Payment(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")        @NotNull val id: Int,
    @ColumnInfo(name = "name")      @NotNull val name: String,
    @ColumnInfo(name = "amount")    @NotNull val amount: String,
    @ColumnInfo(name = "date")      @NotNull val date: String,
) : BaseEntity