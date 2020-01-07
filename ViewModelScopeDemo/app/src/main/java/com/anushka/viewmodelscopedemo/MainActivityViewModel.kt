package com.anushka.viewmodelscopedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {

    private var userRepository = UserRepository()
    private var _users: MutableLiveData<List<User>> = MutableLiveData()

    val user: LiveData<List<User>>
        get() = _users

    /**
     * No caso do viewModelScope não é necessário criar um job para ser cancelado
     *
     * Deve ser usado as seguintes depêndencias:
     * def arch_version = '2.2.0-alpha04'
     * implementation "androidx.lifecycle:lifecycle-extensions:$arch_version"
     * implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$arch_version"
     *
     * A versão mínima que contém o viewModelScope é a 2.1.0
     */
    fun getUserData() {
        log("Antes do launch")
        viewModelScope.launch {
            var result: List<User>? = null

            log("Antes do contexto")
            withContext(Dispatchers.IO) {
                log("Dentro do contexto")
                result = userRepository.getUsers()
            }

            log("Setando a variável")
            _users.value = result
        }
        log("Depois do launch")
    }

    /**
     * Escopo customizado deve levar um job na sua construção para que este possa ser cancelado
     * no momento que o ViewModel for destruido (Método onCleared).
     */
    /*private val myJob = Job()
    private val myScope = CoroutineScope(Dispatchers.IO + myJob)

    fun getUserData() {
        myScope.launch {
            //write some code
        }
    }

    override fun onCleared() {
        super.onCleared()
        myJob.cancel()
    }*/
}