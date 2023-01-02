package com.harshul.storeapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.harshul.storeapp.data.models.Product

@Database(entities = [Product::class], version = 1)
abstract class FakerDB : RoomDatabase() {

    abstract fun getFakerDAO() : FakerDAO

}