package com.example.chainr

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var gridView: GridView
    private lateinit var gameState: GameState
    private lateinit var tapPlayer: MediaPlayer
    private lateinit var score1: TextView
    private lateinit var score2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val p1 = intent.getStringExtra("player1")
        val p2 = intent.getStringExtra("player2")
        val row = intent.getIntExtra("row", 5)
        val column = intent.getIntExtra("column", 5)

        score1 = findViewById(R.id.tv_score1)
        score2 = findViewById(R.id.tv_score2)

        tapPlayer = MediaPlayer.create(this, R.raw.click)

        gridView = findViewById(R.id.grid_view)
        val cells = ArrayList<CellState>(25)
        repeat(row * column) {
            cells.add(CellState())
        }
        gameState = GameState(column, row, cells, Player.PLAYER_ONE)

        val adapter = GridAdapter(this, gameState, tapPlayer, score1, score2)
        gridView.adapter = adapter
    }
}