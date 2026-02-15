package com.example.calculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var currentInput = ""
    private var firstNumber = 0.0
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        // Number buttons
        val numberButtons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9
        )

        numberButtons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                val value = (it as Button).text.toString()
                currentInput += value
                tvResult.text = currentInput
            }
        }

        // Operators
        findViewById<Button>(R.id.btnAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperator("/") }

        // Equal
        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            calculateResult()
        }

        // Clear
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            currentInput = ""
            firstNumber = 0.0
            operator = ""
            tvResult.text = "0"
        }

    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            firstNumber = currentInput.toDouble()
            operator = op
            currentInput = ""
        }
    }

    private fun calculateResult() {
        if (currentInput.isEmpty()) return

        val secondNumber = currentInput.toDouble()

        val result = when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else 0.0
            else -> 0.0
        }

        tvResult.text = result.toString()
        currentInput = result.toString()
    }
}
