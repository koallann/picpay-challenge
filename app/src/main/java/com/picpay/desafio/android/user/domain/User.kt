package com.picpay.desafio.android.user.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val imgUrl: String,
) : Parcelable
