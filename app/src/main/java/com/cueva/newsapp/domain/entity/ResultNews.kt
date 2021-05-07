package com.cueva.newsapp.domain.entity

sealed class ResultNews<out Success, out Failure>

data class Success<out Success>(val value: Success) : ResultNews<Success, Nothing>()
data class Failure<out Failure>(val reason: Failure) : ResultNews<Nothing, Failure>()