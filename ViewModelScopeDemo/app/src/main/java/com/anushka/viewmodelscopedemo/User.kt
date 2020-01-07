package com.anushka.viewmodelscopedemo

data class User(val id: Int, val name: String) {

    override fun toString(): String {
        return "id: $id, nome: $name"
    }
}