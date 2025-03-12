package com.example.lab4mob

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    // Конструкторы в Kotlin не могут в себе содержать
    // какую-либо логику по инициализации свойств
    // init - блок инициализации - блок кода,
    // обязательно выполняемый при создании объекта
    init {
        viewModelScope.launch {

        }
    }
    // Создаем MutableStateFlow. В качестве значения по умолчанию пустая строка.
    private val _state = MutableStateFlow("")
    // Наружу отдаем read-only StateFlow
    val state = _state.asStateFlow()

    fun onButtonClicked() {
    // viewModelScope - это Coroutine Scope,
    // который привязан к ЖЦ ViewModel. Когда она будет
    // уничтожаться в onCleared, то все запущенные
    // Coroutines отменятся
    viewModelScope.launch {
        _state.value = "Loading"
    // останавливает корутину на 2 сек
        delay(2000)

        _state.value = "Success!"
    }
    }
    // Переопределять не надо, просто для демонстрации
    // такого метода
    override fun onCleared() {
        super.onCleared()
    }
}

