package com.example.tictactoe

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class GameViewModel: ViewModel() {
    var state by mutableStateOf(GameState())

    val boardCells : MutableMap<Int,BoardCellValue> = mutableMapOf(
        1 to BoardCellValue.NONE,
        2 to BoardCellValue.NONE,
        3 to BoardCellValue.NONE,
        4 to BoardCellValue.NONE,
        5 to BoardCellValue.NONE,
        6 to BoardCellValue.NONE,
        7 to BoardCellValue.NONE,
        8 to BoardCellValue.NONE,
        9 to BoardCellValue.NONE
    )
    fun onAction(action: UserActions){
        when(action){
            is UserActions.BoardTapped->{
                addValueToBoard(action.cellNumber)
            }
            UserActions.PlayAgainButtonClicked ->{

            }
        }
    }

    private fun addValueToBoard(cellNumber: Int) {
        if(boardCells[cellNumber]!= BoardCellValue.NONE){
            return
        }
        if(state.currentTurn== BoardCellValue.CROSS){
            boardCells[cellNumber] = BoardCellValue.CROSS
            state = state.copy(
                currentTurn = BoardCellValue.CIRCLE,
                messageTurn = "Player 'O' turn"
            )
        }else{
            boardCells[cellNumber] = BoardCellValue.CIRCLE
            state = state.copy(
                currentTurn = BoardCellValue.CROSS,
                messageTurn = "Player 'X' turn"
            )
        }

    }

}




