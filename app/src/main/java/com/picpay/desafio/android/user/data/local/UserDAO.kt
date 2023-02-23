package com.picpay.desafio.android.user.data.local

import androidx.room.*

@Dao
interface UserDAO {
    @Query("SELECT * FROM users")
    suspend fun getAll(): List<UserEntity>

    @Transaction
    suspend fun set(users: List<UserEntity>) {
        deleteAll()
        insertAll(users)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>)

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}
