package com.anushka.livedatademo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.anushka.livedatademo.model.UserRepository
import kotlinx.coroutines.Dispatchers

class MainActivityViewModel : ViewModel()  {

    private var usersRepository= UserRepository()
    /**
     * Para usar o liveData é necessário adicionar a seguinte dependência:
     * implementation "androidx.lifecycle:lifecycle-livedata-ktx:$arch_version"
     *
     * A versão mínima é: 2.2.0-alpha04
     */
    var users = liveData(Dispatchers.IO) {
        val result = usersRepository.getUsers()
        emit(result)
    }

    /*var users: MutableLiveData<List<User>> = MutableLiveData()

    fun getUsers() {
        viewModelScope.launch {
            var result: List<User>? = null
            withContext(Dispatchers.IO) {
                result = usersRepository.getUsers()
            }
            users.value = result
        }
    }*/
}