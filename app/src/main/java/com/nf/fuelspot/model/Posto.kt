package com.nf.fuelspot.model

import java.math.BigDecimal


/**
 *
 *      Author: Rodrigo Serrano
 *
 *      classe de modelo de posto
 *
 *
 * **/


abstract class Posto(

    protected var name: String = "",
    protected var cnpj: String = "",
    protected var cep: String = "",
    protected var bairro: String = "",
    protected var cidade: String = "",
    protected var rua: String = "",
    protected var numero: String = "",
    protected var id: String = "",
    protected var price: BigDecimal = BigDecimal.ZERO,
    protected var score: BigDecimal = BigDecimal.ZERO,
    protected var address: String = "",
    protected var distance: String = "",
    protected var distanceTime: String = "",
    protected var longitude: BigDecimal = BigDecimal.ZERO,
    protected var latitude: BigDecimal = BigDecimal.ZERO
//    protected var longitude: Double = 0.0,
//    protected var latitude: Double = 0.0

)

