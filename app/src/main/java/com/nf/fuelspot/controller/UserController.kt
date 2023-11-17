package com.nf.fuelspot.controller

import com.nf.fuelspot.model.Usuario
import java.security.MessageDigest
import java.util.UUID

/**
 *      Author: Rodrigo Serrano e Juan Herrera
 *      classe de controle de usuario
 *      extende usuario
 *      cria e atualizada os dados de usuario
 */

class UserController : Usuario() {

    fun getUser(): UserController {
        return this
    }

    fun createUser(name: String, email: String, senha: String, owner: Boolean) {
        this.name = name
        this.email = email
        this.senha = encriptaSenha(senha)
        this.id = generateId()
        this.owner = owner
    }

    fun updateUserName(name: String) {
        this.name = name
    }

    fun updateEmail(email: String) {
        this.email = email
    }


    fun getUserName(): String {
        return this.name
    }

    fun getUserEmail(): String {
        return this.email
    }

    fun getUserPassword(): String {
        return this.senha
    }

    fun getUserId(): String {
        return this.id
    }

    fun getUserOwner(): Boolean {
        return this.owner
    }

    fun encriptaSenha(senha: String): String {
        if (senha.isEmpty()) {
            return senha
        }
        val bytes = senha.toByteArray()
        val digest = MessageDigest.getInstance("SHA-256")
        val senhaHash = digest.digest(bytes)
        return senhaHash.joinToString("") { "%02x".format(it) }
    }

    fun generateId(): String {
        return UUID.randomUUID().toString()
    }
}
