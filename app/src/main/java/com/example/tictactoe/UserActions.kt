package com.example.tictactoe

sealed class UserActions{
    object PlayAgainButtonClicked: UserActions()
    data class BoardTapped(val cellNumber: Int): UserActions()

}
