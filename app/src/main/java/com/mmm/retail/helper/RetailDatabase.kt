package com.mmm.retail.helper

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

import com.mmm.retail.model.Product

@Database(entities = [Product::class], version = 1)
abstract class RetailDatabase : RoomDatabase() {
    abstract fun cartAccess(): CartAccess

   /* companion object {
        private var database: RetailDatabase? = null
        fun getInstant(context: Context): RetailDatabase? {
            if (database == null) {
                database = Room.databaseBuilder(context,
                        RetailDatabase::class.java, "Product").build()
            }
            return database
        }
    }*/

    companion object {
        @Volatile
        private var INSTANCE: RetailDatabase? = null

        fun getInstant(context: Context): RetailDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RetailDatabase::class.java,
                        "Product"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
