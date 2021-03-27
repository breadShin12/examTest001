package com.sjh.sample.domain.base

import com.sjh.sample.domain.Result
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

typealias Block<T> = Result<T>.() -> Unit
abstract class BaseUseCase<T, in Any> {
    private var parentJob: Job = Job()
    var backgroundContext: CoroutineContext = Dispatchers.IO
    var foregroundContext: CoroutineContext = Dispatchers.Main
    private val scope = CoroutineScope(parentJob + foregroundContext) //기본 Dispatchers 는 Main으로 설정

    protected abstract suspend fun executeOnBackground(params: Any) : Result<T>

    fun execute(objects: Any, func: Block<T>){
        scope.launch {
                val result = withContext(backgroundContext) { //백그라운드 동작시 IO로 Context Switching
                     executeOnBackground(objects)
                 }
                result.run{func()}
        }
    }

    fun unsubscribe(){
        scope.cancel()
    }

    fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
}
