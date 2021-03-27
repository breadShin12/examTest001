package com.sjh.sample.domain.extensions

import kotlinx.coroutines.*

/**
 * 병렬실행을 위한 함수
 */
suspend fun <A, B> Iterable<A>.parallelMap(func: suspend (A) -> B): List<B> =
    coroutineScope {
        withContext(Dispatchers.IO) {
            map { async { func(it) } }.awaitAll()
        }
    }