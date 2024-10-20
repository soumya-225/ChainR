package com.example.chainr

data class CellState(
    val particleCount: Int,
    val player: Player?,
)

enum class Player {
    PLAYER_ONE,
    PLAYER_TWO
}

data class GameState(
    val gridLength: Int,
    val gridWidth: Int,
    val cells: ArrayList<CellState>,
    val player: Player,
) {
    fun getCell(row: Int, col: Int): CellState {
        return cells[row * gridLength + col]
    }

    fun setCell(row: Int, col: Int, cell: CellState) {
        cells[row * gridLength + col] = cell
    }
}