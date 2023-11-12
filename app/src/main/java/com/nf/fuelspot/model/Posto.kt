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
    protected var price: BigDecimal = BigDecimal.ZERO,
    protected var score: BigDecimal = BigDecimal.ZERO,
    protected var address: String = "",
    protected var distance: BigDecimal = BigDecimal.ZERO,
    protected var distanceTime: BigDecimal = BigDecimal.ZERO,
    protected var longitude: BigDecimal = BigDecimal.ZERO,
    protected var latitude: BigDecimal = BigDecimal.ZERO
//    protected var longitude: Double=0.0,
//   protected var latitude: Double =0.0

)

