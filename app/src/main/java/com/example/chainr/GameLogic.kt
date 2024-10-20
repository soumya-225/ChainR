package com.example.chainr

fun playChance(
    gameState: GameState,
    player: Player,
    row: Int,
    col: Int,
    gameOverCallback: (Player) -> Unit,
) {
    gameState.incrementParticleCount(row, col, player)

    var playerOneCount = 0
    var playerTwoCount = 0

    while (true) {
        for (gridCell in gameState.cells) {
            if (gridCell.player == Player.PLAYER_ONE) ++playerOneCount
            else if (gridCell.player == Player.PLAYER_TWO) ++playerTwoCount
        }

        if (playerOneCount >= 10) {
            gameOverCallback(Player.PLAYER_ONE)
        }
        else if (playerTwoCount >= 10) {
            gameOverCallback(Player.PLAYER_TWO)
        }

        val stateChanged = simplifyState(gameState, player)
        if (!stateChanged) break
    }
}

fun simplifyState(gameState: GameState, player: Player): Boolean {
    var stateChanged = false
    for (i in 0 until gameState.gridLength) {
        for (j in 0 until gameState.gridWidth) {
            val cell = gameState.getCell(i, j)
            if (cell.particleCount > 3) {
                stateChanged = true
                val newParticleCount = cell.particleCount - 4
                if (newParticleCount > 0)
                    gameState.setCell(i, j, CellState(newParticleCount, player))
                else
                    gameState.setCell(i, j, CellState(particleCount = 0, player = null))
                if (i > 0) gameState.incrementParticleCount(i - 1, j, player)
                if (i < gameState.gridLength - 1) gameState.incrementParticleCount(i + 1, j, player)
                if (j > 0) gameState.incrementParticleCount(i, j - 1, player)
                if (j < gameState.gridWidth - 1) gameState.incrementParticleCount(i, j + 1, player)
            }
        }
    }
    return stateChanged
}