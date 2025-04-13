package com.example.myapplicatiotest.models.validations

sealed class validationResult {
    data class empty (val errorMessage: String): validationResult()
    data class Invalid (val errorMessage: String): validationResult()
    object Valid: validationResult()
}