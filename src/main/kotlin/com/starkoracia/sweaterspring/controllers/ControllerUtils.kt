package com.starkoracia.sweaterspring.controllers

import org.springframework.validation.BindingResult

object ControllerUtils {
    fun getErrors(bindingResult: BindingResult): MutableMap<String, String> {
        return bindingResult.fieldErrors.map { it.field + "Error" to it.defaultMessage!! }.toMap().toMutableMap()
    }
}
