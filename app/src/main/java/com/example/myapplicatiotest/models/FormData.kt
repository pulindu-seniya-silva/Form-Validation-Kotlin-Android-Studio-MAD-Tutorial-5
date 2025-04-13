package com.example.myapplicatiotest.models

import com.example.myapplicatiotest.models.validations.validationResult

class FormData (
    private var studentId: String,
    private var Year: String,
    private var Semester: String,
    private var agree: Boolean
) {
    fun validateStudentId(): validationResult {
        return if (studentId.isEmpty()) {
            validationResult.empty("Student Id is required")
         } else if(!studentId.startsWith("IT")) {
             validationResult.Invalid("Student Id must starts with IT")
         } else if (studentId.length != 10) {
             validationResult.Invalid("Student Id must be 10 characters long")
         } else {
             validationResult.Valid
         }
     }

    fun validateYear(): validationResult {
        return if (Year.isEmpty()) {
            validationResult.empty("Year is required")
         } else {
             validationResult.Valid
        }
    }

    fun validateSemester(): validationResult {
        return if (Semester.isEmpty()) {
            validationResult.empty("Year is required")
        } else {
            validationResult.Valid
        }
    }

    fun validateAgreement(): validationResult {
        return if (!agree) {
            validationResult.Invalid("You must agree to the terms and conditions")
        } else {
            validationResult.Valid
        }
    }
}