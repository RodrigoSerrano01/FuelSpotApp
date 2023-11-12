package com.nf.fuelspot.controller

import com.nf.fuelspot.model.Usuario
import java.security.MessageDigest


/**
 *
 *      Author: Rodrigo Serrano
 *
 *      classe de controle de usuario
 *
 *      extende usuario
 *
 *      cria e atualizada os dados de usuario
 *
 * **/


class UserController : Usuario() {




    fun getUser(): UserController {
        return this
    }

    fun createUser( name: String, email:String , senha: String) {
        this.name = name
        this.email = email
        this.senha = encriptaSenha(senha)

    }
    fun updateUserName (name: String){
      this.name = name
    }

    fun updateEmail (email: String){
      this.email = email
    }


    fun getUserName (): String {
        return this.name
    }

    fun getUserEmail (): String {
        return this.email
    }
    fun getUserPassword(): String {
        return this.senha
    }

    fun encriptaSenha(senha: String): String {
        val bytes = senha.toByteArray()
        val digest = MessageDigest.getInstance("SHA-256")
        val senhaHash = digest.digest(bytes)
        return senhaHash.joinToString("") { "%02x".format(it) }
    }

    override fun toString(): String {
        return "UserService(name='$name', email='$email', posto)"
    }



}
