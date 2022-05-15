package com.mehmet.genc.mywallet.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "card")
data class Card(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")            @NotNull val id: Int,
    @ColumnInfo(name = "name")          @NotNull val name: String,
    @ColumnInfo(name = "number")        @NotNull val number: String,
    @ColumnInfo(name = "organization")  @NotNull val organization: String,
    @ColumnInfo(name = "expiry_date")   @NotNull val expiry_date: String,
) : BaseEntity