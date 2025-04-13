package com.example.myapplicatiotest

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplicatiotest.models.FormData
import com.example.myapplicatiotest.models.validations.validationResult

class MainActivity : AppCompatActivity() {

    lateinit var edtStudentId: EditText;
    lateinit var spnYear: Spinner;
    lateinit var spnSemester: Spinner;
    lateinit var cbAgree: CheckBox;

    private var count = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtStudentId = findViewById(R.id.edtStudentId)
        spnYear = findViewById(R.id.spnYear)
        spnSemester = findViewById(R.id.spnSemester)
        cbAgree = findViewById(R.id.cbAgree)
    }

    fun displayAlert(title: String, message: String) {
        var builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->

        }

        val dialog = builder.create()
        dialog.show()

    }

    fun submit(v: View) {
        val myForm = FormData(
            edtStudentId.text.toString(),
            spnYear.selectedItem.toString(),
            spnSemester.selectedItem.toString(),
            cbAgree.isChecked
        )

        val studentIdValidation = myForm.validateStudentId()
        val spnYearValidation = myForm.validateYear()
        val spnSemesterValidation = myForm.validateSemester()
        val cbAgreeValidation = myForm.validateAgreement()

        when (studentIdValidation) {

            validationResult.Valid -> {
                count++
            }

            is validationResult.empty -> {
                displayAlert("Error", studentIdValidation.errorMessage)
            }

            is validationResult.Invalid -> {
                displayAlert("Error", studentIdValidation.errorMessage)
            }
        }

        when (spnYearValidation) {

            validationResult.Valid -> {
                count++
            }

            is validationResult.empty -> {
                val tv: TextView = spnYear.selectedView as TextView
                tv.error = ""
                tv.text = spnYearValidation.errorMessage
            }

            is validationResult.Invalid -> {

            }

        }

        when (spnSemesterValidation) {

            validationResult.Valid -> {
                count++
            }

            is validationResult.empty -> {
                val tv: TextView = spnSemester.selectedView as TextView
                tv.error = ""
                tv.text = spnSemesterValidation.errorMessage
            }

            is validationResult.Invalid -> {
                displayAlert("Error", spnSemesterValidation.errorMessage)
            }
        }
        when (cbAgreeValidation) {

            validationResult.Valid -> {
                count++
            }

            is validationResult.Invalid -> {
                displayAlert("Error", cbAgreeValidation.errorMessage)
            }

            is validationResult.empty -> {
                displayAlert("Error", cbAgreeValidation.errorMessage)
            }
        }

        if (count == 4) {
            displayAlert("Success", "Form submitted successfully")

        } else {
            count = 0
        }
    }
}