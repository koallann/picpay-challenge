package com.picpay.desafio.android.user.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.user.data.remote.UserResponse
import com.picpay.desafio.android.user.domain.User

@Entity(tableName = "users")
class UserEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("username")
    val username: String,
    @ColumnInfo("img_url")
    val imgUrl: String,
) {
    fun toDomain() = User(id, name, username, imgUrl)
}

fun List<UserResponse>.toEntity(): List<UserEntity> = this.map {
    UserEntity(it.id, it.name, it.username, it.imgUrl)
}

fun List<UserEntity>.toDomain() = this.map { it.toDomain() }
