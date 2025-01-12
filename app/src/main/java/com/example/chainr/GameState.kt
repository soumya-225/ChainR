package com.example.chainr

data class CellState(
    val particleCount: Int = 0,
    val player: Player? = null,
)

enum class Player {
    PLAYER_ONE,
    PLAYER_TWO
}

data class GameState(
    val gridLength: Int,
    val gridWidth: Int,
    val cells: ArrayList<CellState>,
    var player: Player,
) {
    fun getCell(row: Int, col: Int): CellState {
        return cells[row * gridLength + col]
    }

    fun setCell(row: Int, col: Int, cell: CellState) {
        cells[row * gridLength + col] = cell
    }

    fun incrementParticleCount(row: Int, col: Int, player: Player? = null) {
        val cell = getCell(row, col)
        setCell(row, col, CellState(cell.particleCount + 1, player ?: cell.player))
    }
}