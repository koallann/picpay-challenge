package com.picpay.desafio.android.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.user.data.local.UserDAO
import com.picpay.desafio.android.user.data.local.UserEntity

const val DATABASE_NAME = "db_picpay"

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDAO: UserDAO
}
