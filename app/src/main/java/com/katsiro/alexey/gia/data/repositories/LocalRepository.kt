package com.katsiro.alexey.gia.data.repositories


interface LocalRepository<T> {
    
    suspend fun get(id: Long): T

    suspend fun getAll(): List<T>

    suspend fun insert(data: T): Long

    suspend fun update(data: T)

    suspend fun delete(data: T)

    suspend fun getCount(): Int
}