package com.starkoracia.sweaterspring.entities.dto

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CaptchaResponseDto(
        val success: Boolean,
        @JsonAlias("error-codes")
        val errorCodes: Set<String>?) {

}