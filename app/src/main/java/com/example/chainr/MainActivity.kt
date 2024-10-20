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

        gridView = findViewById(R.id.grid_view)
        val cells = ArrayList<CellState>(25)
        repeat(25) {
            cells.add(CellState())
        }
        gameState = GameState(5, 5, cells, Player.PLAYER_ONE)

        val adapter = GridAdapter(this, gameState)
        gridView.adapter = adapter
    }
}