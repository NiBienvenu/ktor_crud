package com.bienvenu.model

import com.google.gson.annotations.SerializedName
import java.sql.Date

data class Post(
    @field:SerializedName("id")
    val id:Int ? =null,
    @field:SerializedName("name")
    val name:String ? =null,
    @field:SerializedName("description")
    val description:String? =null,
    @field:SerializedName("created_at")
    val created_at: Date? =null,
    @field:SerializedName("updated_at")
    val updated_at: Date? =null
)
