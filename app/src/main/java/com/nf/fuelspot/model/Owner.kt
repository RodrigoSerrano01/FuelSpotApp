package com.nf.fuelspot.model

abstract class Owner: Usuario() {


    /**
     *
     *      Author: Rodrigo Serrano
     *
     *      classe de modelo de proprietario
     *      extende usuario e adiciona uma lista de postos
     *
     *
     *
     *
     * **/


    protected var gasStationList = mutableListOf<Posto>()


}