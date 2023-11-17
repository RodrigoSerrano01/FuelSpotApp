package com.nf.fuelspot.model

abstract class Usuario {

    /**
     *      Author: Rodrigo Serrano, Juan Herrera
     *      classe de modelo de Usuario
     */

    protected var name: String = ""
    protected var email: String = ""
    protected var senha: String = ""
    protected var id: String = ""
    protected var owner: Boolean = false
    //private lateinit var posto: Posto

}

