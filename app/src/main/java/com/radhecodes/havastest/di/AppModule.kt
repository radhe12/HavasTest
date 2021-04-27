package com.radhecodes.havastest.di

import com.radhecodes.havastest.repository.HavasRepository
import com.radhecodes.havastest.repository.HavasRepositoryImpl
import org.koin.dsl.module

val appModule = module {

    factory {
        createHavasRepository()
    }
}

fun createHavasRepository(): HavasRepository {
    return HavasRepositoryImpl()
}