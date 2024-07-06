package com.bienvenu.util

data class GenericResponse<out T>(val isSuccess: Boolean, val data: T)
