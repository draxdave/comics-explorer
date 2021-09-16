package com.shortcut.explorer.test_util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


public fun <T> runBlockingTest(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> T) {
    runBlocking(context,block)
}

fun <T> Int.times(block:(Int)->T) :List<T>{
    val resultList = mutableListOf<T>()
    repeat(this){
        resultList.add(
            block(it)
        )
    }
    return resultList
}