package com.example.chainr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView

class GridAdapter(context: Context, cellStateArrayList: ArrayList<CellState>) :
    ArrayAdapter<CellState>(context, 0, cellStateArrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false)
        }

        val cellState = getItem(position)

        listItemView!!.findViewById<ImageView>(R.id.image_view).setImageResource(getImageId(cellState)!!)
        return listItemView
    }

    private fun getImageId(cellState: CellState?): Int? {
        if (cellState != null) {
            if (cellState.player == Player.PLAYER_ONE) {
                when (cellState.particleCount) {
//                    1 -> return R.drawable.one_player_one
//                    2 -> return R.drawable.two_player_one
//                    3 -> return R.drawable.three_player_one
                }
            } else {
                when (cellState.particleCount) {
//                    1 -> return R.drawable.one_player_two
//                    2 -> return R.drawable.two_player_two
//                    3 -> return R.drawable.three_player_two
                }
            }
        }
        return null
    }
}
