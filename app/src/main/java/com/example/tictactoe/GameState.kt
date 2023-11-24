package com.example.tictactoe

import androidx.compose.ui.res.stringResource

data class GameState(
    val playerCircleCount: Int =0,
    val playerCrossCount: Int = 0,
    val drawCount: Int =0,
    val currentTurn:BoardCellValue = BoardCellValue.CROSS,
    val messageTurn: String = "Player X turn",
    val victoryType: VictoryType = VictoryType.NONE,
    val hasWon: Boolean = false

)

enum class BoardCellValue{
    CIRCLE,
    CROSS,
    NONE
}

enum class VictoryType{
    HORIZONTAL1,
    HORIZONTAL2,
    HORIZONTAL3,
    VERTICAL1,
    VERTICAL2,
    VERTICAL3,
    DIAGONAL0,
    DIAGONAL1,
    NONE
}
