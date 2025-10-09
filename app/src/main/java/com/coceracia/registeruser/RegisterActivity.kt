package com.coceracia.registeruser

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isInvisible
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etName = findViewById<EditText>(R.id.etName)
        val txtErrorName = findViewById<TextView>(R.id.txtErrorName)
        checkName(etName,txtErrorName)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val txtErrorEmail = findViewById<TextView>(R.id.txtErrorEmail)
        checkEmail(etEmail,txtErrorEmail)

        val etPassword = findViewById<EditText>(R.id.etPassword)
        val txtErrorPassword = findViewById<TextView>(R.id.txtErrorPassword)
        checkPassword(etPassword,txtErrorPassword)

        val btn = findViewById<AppCompatButton>(R.id.btnSubmit)
        btn.setOnClickListener {
            if ((txtErrorPassword.isInvisible && etPassword.text.toString().trim() != "")
                && (txtErrorEmail.isInvisible && etEmail.text.toString().trim() != "")
                && (txtErrorName.isInvisible && etName.text.toString().trim() != "")){
                Toast.makeText(this, "Succesfully registered", Toast.LENGTH_SHORT).show()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Invalid form")
                    .setMessage("Please fill all the camps with a valid format")
                    .setPositiveButton("Cofirm") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }

    }

    fun checkName(etName: EditText, txtErrorName: TextView) {
        etName.doOnTextChanged { text, _, _, _ ->
            if (text.toString().all {it.isLetter() || it == ' '}){
                txtErrorName.visibility = View.INVISIBLE
            } else{
                txtErrorName.visibility = View.VISIBLE
            }
        }
    }

    fun checkEmail(etEmail: EditText, txtErrorEmail: TextView) {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        etEmail.doOnTextChanged { text, _, _, _ ->
            if (text.toString().matches(emailRegex)) {
                txtErrorEmail.visibility = View.INVISIBLE
            } else {
                txtErrorEmail.visibility = View.VISIBLE
            }
        }
    }

    fun checkPassword(etPassword: EditText, txtErrorPassword: TextView) {
        val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&.#_-])[A-Za-z\\d@\$!%*?&.#_-]{8,}$")
        etPassword.doOnTextChanged { text, _, _, _ ->
            if (text.toString().matches(passwordRegex)) {
                txtErrorPassword.visibility = View.INVISIBLE
            } else {
                txtErrorPassword.visibility = View.VISIBLE
            }
        }
    }
}