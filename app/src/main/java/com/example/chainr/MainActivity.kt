package com.example.chainr

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var gridView: GridView
    private lateinit var gameState: GameState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val p1 = intent.getStringExtra("player1")
        val p2 = intent.getStringExtra("player2")
        val row = intent.getIntExtra("row", 5)
        val column = intent.getIntExtra("column", 5)

        gridView = findViewById(R.id.grid_view)
        val cells = ArrayList<CellState>(25)
        repeat(row * column) {
            cells.add(CellState())
        }
        gameState = GameState(column, row, cells, Player.PLAYER_ONE)

        val adapter = GridAdapter(this, gameState)
        gridView.adapter = adapter
    }
}