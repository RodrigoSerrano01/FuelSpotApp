package com.nf.fuelspot.controller

import com.nf.fuelspot.model.Posto
import java.math.BigDecimal
import java.util.UUID

class PostoController: Posto() {
    fun createGasStation(
        name: String,
        cnpj: String,
        cep: String,
        bairro: String,
        cidade: String,
        rua: String,
        numero: String,
        price: BigDecimal
    ) {
        this.name = name
        this.cnpj = cnpj
        this.cep = cep
        this.bairro = bairro
        this.cidade = cidade
        this.rua = rua
        this.numero = numero
        this.id = generateId()
        this.price = price
    }

    fun getPostoName(): String {
        return this.name
    }

    fun getPostoCnpj(): String {
        return this.cnpj
    }

    fun getPostoCep(): String {
        return this.cep
    }

    fun getPostoBairro(): String {
        return this.bairro
    }

    fun getPostoCidade(): String {
        return this.cidade
    }

    fun getPostoRua(): String {
        return this.rua
    }

    fun getPostoNumero(): String {
        return this.numero
    }

    fun getPostoId(): String {
        return this.id
    }

    fun getPostoPrice():BigDecimal {
        return this.price
    }

    fun setPostoValorPrice(price: BigDecimal) {
        this.price = price
    }

    fun generateId(): String {
        return UUID.randomUUID().toString()
    }
}