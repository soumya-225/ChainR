package com.example.chainr

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class GridAdapter(
    context: Context,
    private val gameState: GameState,
    private val tapPlayer: MediaPlayer,
    private val score1: TextView,
    private val score2: TextView
) :
    ArrayAdapter<CellState>(context, 0, gameState.cells) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false)
        }

        listItemView!!.setOnClickListener {
            val row = position / gameState.gridLength
            val col = position % gameState.gridLength
            val nextPlayer = when (gameState.player) {
                Player.PLAYER_ONE -> Player.PLAYER_TWO
                Player.PLAYER_TWO -> Player.PLAYER_ONE
            }
            if (gameState.getCell(row, col).player == nextPlayer) return@setOnClickListener

            tapPlayer.start()

            val (playerOneCount, playerTwoCount) = playChance(
                gameState,
                gameState.player,
                row,
                col
            )

            score1.text = playerOneCount.toString()
            score2.text = playerTwoCount.toString()

            if (playerOneCount >= 10) endGame(Player.PLAYER_ONE)
            else if (playerTwoCount >= 10) endGame(Player.PLAYER_TWO)

            gameState.player = nextPlayer
            notifyDataSetChanged()
        }

        val cellState = getItem(position)

        if (cellState!!.player == null) {
            listItemView.findViewById<ImageView>(R.id.image_view).setImageDrawable(null)
        } else {
            listItemView.findViewById<ImageView>(R.id.image_view)
                .setImageResource(getImageId(cellState)!!)
        }
        return listItemView
    }

    private fun getImageId(cellState: CellState?): Int? {
        if (cellState == null)
            return null
        return if (cellState.player == Player.PLAYER_ONE) {
            when (cellState.particleCount) {
                1 -> R.drawable.one_player_one
                2 -> R.drawable.two_player_one
                3 -> R.drawable.three_player_one
                else -> R.drawable.ic_launcher_background
            }
        } else {
            when (cellState.particleCount) {
                1 -> R.drawable.one_player_two
                2 -> R.drawable.two_player_two
                3 -> R.drawable.three_player_two
                else -> R.drawable.ic_launcher_background
            }
        }
    }

    private fun endGame(player: Player) {
        MediaPlayer.create(context, R.raw.win).start()
        val playerStr = if (player == Player.PLAYER_ONE) "Player 1" else "Player 2"
        AlertDialog.Builder(context)
            .setTitle("Game Over")
            .setCancelable(false)
            .setMessage("$playerStr wins!")
            .setPositiveButton("OK") { _, _ ->
                (context as Activity).finish()
            }
            .show()
    }
}
