package com.example.nycschools

interface ItemSelector<T> {
    fun itemSelected(item: T)
}