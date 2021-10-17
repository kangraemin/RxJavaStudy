package com.example.rxjavalecture.exercise.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rxjavalecture.exercise.data.local.token.LocalTokenDao
import com.example.rxjavalecture.exercise.data.local.token.LocalTokenItem

@Database(entities = [LocalTokenItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun localTokenDao(): LocalTokenDao
}