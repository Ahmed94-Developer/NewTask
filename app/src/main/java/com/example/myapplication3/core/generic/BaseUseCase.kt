package com.example.myapplication3.core.generic

interface BaseUseCase<In, Out>{
    suspend fun execute(input: In): Out
}