package com.example.chainr

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged

class PreGameActivity : AppCompatActivity() {
    private lateinit var etP1: EditText
    private lateinit var etP2: EditText
    private lateinit var btn55: Button
    private lateinit var btn66: Button
    private lateinit var btn86: Button
    private lateinit var btnc: Button
    private lateinit var etc: EditText
    private lateinit var cont: Button
    private var row: Int = 5
    private var column: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*enableEdgeToEdge()
        setContentView(R.layout.activity_pre_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        setContentView(R.layout.activity_pre_game)

        etP1 = findViewById(R.id.et_p1)
        etP2 = findViewById(R.id.et_p2)
        btn55 = findViewById(R.id.btn_55)
        btn66 = findViewById(R.id.btn_66)
        btn86 = findViewById(R.id.btn_86)
        btnc = findViewById(R.id.btn_custom)
        etc = findViewById(R.id.et_grid)
        cont = findViewById(R.id.btn_cont)

        btn55.setOnClickListener {
            row = 5
            column = 5
        }

        btn66.setOnClickListener {
            row = 6
            column = 6
        }

        btn86.setOnClickListener {
            row = 8
            column = 6
        }

        btnc.setOnClickListener {
            etc.visibility = View.VISIBLE
        }

        etc.doAfterTextChanged {
            row = etc.text.toString().toInt()
            column = 5
        }


        cont.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("player1", etP1.text.toString())
            intent.putExtra("player2", etP2.text.toString())
            intent.putExtra("row", row)
            intent.putExtra("column", column)
            startActivity(intent)
        }


    }
}