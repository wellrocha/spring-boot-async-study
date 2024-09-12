package br.com.wellrocha.spring_boot_async_study.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Phones (
    val phoneId: String,
    val phone: String
)
