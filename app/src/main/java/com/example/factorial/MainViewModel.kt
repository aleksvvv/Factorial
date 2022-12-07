package com.example.factorial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state:LiveData<State>
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
//        calculate

            delay(1000)
//            _progressBar.value = false
//            _factorial.value = number.toString()
//            _state.value = State(isFactorial = number.toString())
            _state.value = Result(number.toString())
        }


    }
}