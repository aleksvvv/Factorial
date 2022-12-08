package com.example.factorial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

class MainViewModel : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

//    private val _error = MutableLiveData<Boolean>()
//    val error: LiveData<Boolean>
//        get() = _error
//
//    private val _factorial = MutableLiveData<String>()
//    val factorial: LiveData<String>
//        get() = _factorial
//
//    private val _progressBar = MutableLiveData<Boolean>()
//    val progressBar: LiveData<Boolean>
//        get() = _progressBar

    fun calculateFactorial(value: String?) {
//        _progressBar.value = true
//        _state.value = State(isInProgress = true)
        _state.value = Progress

        if (value.isNullOrBlank()) {
//            _error.value = true
//            _state.value = State(isError = true)
            _state.value = Error
            return
        }
        viewModelScope.launch {
            val number = value.toLong()
            val result = withContext(Dispatchers.Default) {
                factorial(number)
            }
            _state.value = Factorial(result)
        }

//            delay(1000)
//            _progressBar.value = false
//            _factorial.value = number.toString()
//            _state.value = State(isFactorial = number.toString())


}

private suspend fun factorial(number: Long): String {

    var result = BigInteger.ONE
    for (i in 1..number) {
        result = result.multiply(BigInteger.valueOf(i))
    }
    return result.toString()
}

//    private suspend fun factorial(number: Long): String {
//
//        return withContext(Dispatchers.Default) {
//            var result = BigInteger.ONE
//            for (i in 1..number) {
//                result = result.multiply(BigInteger.valueOf(i))
//            }
//            result.toString()
//        }
//
//    }

//    private suspend fun factorial(number: Long): String {
////делаем из метода suspend-функцию
//        return suspendCoroutine {
//            thread {
//                var result = BigInteger.ONE
//                for (i in 1..number) {
//                    result = result.multiply(BigInteger.valueOf(i))
//                }
//                //коллбэк в виде объекта Continuation
//                it.resumeWith(Result.success(result.toString()))
//            }
//        }
//    }
}