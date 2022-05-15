package com.mehmet.genc.mywallet.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mehmet.genc.mywallet.entity.Card
import com.mehmet.genc.mywallet.entity.Income
import com.mehmet.genc.mywallet.entity.Payment

@Database(entities = [Card::class, Payment::class, Income::class], version = 1)
abstract class MyWalletDatabase : RoomDatabase() {

    abstract fun cardDaoService() : CardDaoService
    abstract fun incomeDaoService() : IncomeDaoService
    abstract fun paymentDaoService() : PaymentDaoService

    companion object {
        var INSTANCE: MyWalletDatabase? = null

        fun connectDatabase(context: Context) : MyWalletDatabase? {
            if(INSTANCE == null) {
                synchronized(MyWalletDatabase::class) {
                    INSTANCE = Room
                        .databaseBuilder(context.applicationContext, MyWalletDatabase::class.java, "MyWallet.sqlite")
                        .createFromAsset("MyWallet.sqlite")
                        .build()
                }
            }
            return INSTANCE
        }
    }

}