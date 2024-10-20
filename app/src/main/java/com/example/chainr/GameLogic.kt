package com.example.chainr

fun playChance(
    gameState: GameState,
    player: Player,
    row: Int,
    col: Int,
    gameOverCallback: (Player) -> Unit,
) {
    val cell = gameState.getCell(row, col)
    gameState.setCell(
        row, col, CellState(
            particleCount = cell.particleCount + 1,
            player = player
        )
    )
}