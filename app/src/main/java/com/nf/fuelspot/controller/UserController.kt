package com.nf.fuelspot.controller

import com.nf.fuelspot.model.Usuario




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

    fun createUser( name: String, email:String ) {
        this.name = name
        this.email = email


    }
    fun updateUserName (name: String){
      this.name = name
    }

    fun updateEmail (email: String){
      this.email = email
    }

    override fun toString(): String {
        return "UserService(name='$name', email='$email', posto)"
    }

}
