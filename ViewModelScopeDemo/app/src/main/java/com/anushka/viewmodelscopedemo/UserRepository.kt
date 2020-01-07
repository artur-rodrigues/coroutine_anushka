package com.anushka.viewmodelscopedemo

import kotlinx.coroutines.delay

class UserRepository {

    suspend fun getUsers(): List<User> {
        try {
            log("Antes de criar a lista de usuário")
            delay(20000)
            return listOf(
                User(1, "User1"),
                User(2, "User2"),
                User(3, "User3"),
                User(4, "User4")
            )
        } finally {
            log("Lista de usuários criada")
        }
    }
}