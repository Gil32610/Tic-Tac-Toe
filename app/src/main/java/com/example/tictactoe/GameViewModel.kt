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
                gameReset()
            }
        }
    }

    private fun gameReset() {
        boardCells.forEach{(i, _)->
            boardCells[i] = BoardCellValue.NONE
        }
        state = state.copy(
            messageTurn = "Player X turn",
            currentTurn = BoardCellValue.CROSS,
            victoryType = VictoryType.NONE,
            hasWon = false
        )
    }

    private fun addValueToBoard(cellNumber: Int) {
        if(boardCells[cellNumber]!= BoardCellValue.NONE){
            return
        }
        if(state.currentTurn== BoardCellValue.CROSS){
            boardCells[cellNumber] = BoardCellValue.CROSS
            if(hasWon(BoardCellValue.CROSS)){
                state = state.copy(
                    messageTurn = "Player X Won",
                    playerCrossCount = state.playerCrossCount+1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            } else if(isBoardFull()){
                state = state.copy(
                    messageTurn = "Game Draw!",
                    drawCount = state.drawCount+1
                )
            }else{
                state = state.copy(
                    currentTurn = BoardCellValue.CIRCLE,
                    messageTurn = "Player O turn"
                )
            }
        }else if(state.currentTurn == BoardCellValue.CIRCLE){
            boardCells[cellNumber] = BoardCellValue.CIRCLE
            if(hasWon(BoardCellValue.CIRCLE)){
                state = state.copy(
                    messageTurn = "Player O Won",
                    playerCircleCount = state.playerCircleCount+1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            }
           else if(isBoardFull()){
                state = state.copy(
                    messageTurn = "Game Draw!",
                    drawCount = state.drawCount+1
                )
            }else{
                state = state.copy(
                    currentTurn = BoardCellValue.CROSS,
                    messageTurn = "Player 'X' turn"
                )
            }


        }

    }

    private fun hasWon(boardValue: BoardCellValue): Boolean {
        when{
            boardCells[1]== boardValue && boardCells[2]== boardValue && boardCells[3]== boardValue ->{
                state = state.copy(victoryType = VictoryType.HORIZONTAL1)
                return true

            }

            boardCells[4]== boardValue && boardCells[5]== boardValue && boardCells[6]== boardValue ->{
                state = state.copy(victoryType = VictoryType.HORIZONTAL2)
                return true
            }
            boardCells[7]== boardValue && boardCells[8]== boardValue && boardCells[9]== boardValue ->{
                state = state.copy(victoryType = VictoryType.HORIZONTAL3)
                return true
            }
            boardCells[1]== boardValue && boardCells[4]== boardValue && boardCells[7]== boardValue ->{
                state = state.copy(victoryType = VictoryType.VERTICAL1)
                return true
            }
            boardCells[2]== boardValue && boardCells[5]== boardValue && boardCells[8]== boardValue ->{
                state = state.copy(victoryType = VictoryType.VERTICAL2)
                return true
            }
            boardCells[3]== boardValue && boardCells[6]== boardValue && boardCells[9]== boardValue ->{
                state = state.copy(victoryType = VictoryType.VERTICAL3)
                return true
            }
            boardCells[1]== boardValue && boardCells[5]== boardValue && boardCells[9]== boardValue ->{
                state = state.copy(victoryType = VictoryType.DIAGONAL0)
                return true
            }
            boardCells[3]== boardValue && boardCells[5]== boardValue && boardCells[7]== boardValue ->{
                state = state.copy(victoryType = VictoryType.DIAGONAL1)
                return true
            }
            else -> return false
        }
    }

    private fun isBoardFull(): Boolean {
        if (boardCells.containsValue(BoardCellValue.NONE))return false
        return true
    }

}




