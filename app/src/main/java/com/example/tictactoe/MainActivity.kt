package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.TicTacToeTheme
import androidx.lifecycle.viewmodel.initializer


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                    TicTacToeScreen(viewModel = GameViewModel())
                // A surface container using the 'background' color from the theme

            }
        }
    }
}

@Composable
fun TicTacToeScreen(
    viewModel: GameViewModel
){
    val state = viewModel.state

    Column(modifier = Modifier
        .background(Color.LightGray)
        .padding(16.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            val charSize = 16.sp
            val family = FontFamily.Monospace
            Text(
                text = "Player X:${state.playerCrossCount}",
                fontSize = charSize,
                fontFamily = family,
                color = Color.Red,
                fontWeight = FontWeight.Bold

            )
            Text(
                text = "Draw:${state.drawCount}",
                fontSize = charSize,
                fontFamily = family
            )
            Text(
                text="Player 'O':${state.playerCircleCount}",
                fontSize = charSize,
                fontFamily = family,
                color = Color.Blue,
                fontWeight = FontWeight.Bold

            )
        }
        Text(
            text = stringResource(R.string.tictactoe_title),
            fontFamily = FontFamily.Monospace,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
        TicTacToeBox(viewModel = viewModel, state = state)
        Text(
            text = state.messageTurn,
            fontFamily = FontFamily.Monospace,
            fontSize=24.sp
        )
        Button(
            onClick = {
               viewModel.onAction(
                   UserActions.PlayAgainButtonClicked
               )
            },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Blue,
                containerColor = Color.Red
            )
        ){
            Text(
                text = stringResource(R.string.restart_game),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        }
    }

}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TicTacToeBox( modifier: Modifier = Modifier,viewModel: GameViewModel, state: GameState) {
    BoxWithConstraints (
        modifier = Modifier

            .background(Color.Gray, shape = RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        val width = constraints.maxWidth
        val height =constraints.maxHeight


        TicTacToeLines(width = width+ 0f, height =height+0f)
        Text(
            text = "width: $width\nheight: $height"
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ){
            viewModel.boardCells.forEach{(cellNumber,boardCellValue)->
                item{
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) {
                                viewModel.onAction(
                                    UserActions.BoardTapped(cellNumber)
                                )
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ){
                        AnimatedVisibility(
                            visible = viewModel.boardCells[cellNumber]!= BoardCellValue.NONE,
                            enter = scaleIn(tween(400))
                            ) {
                            if(boardCellValue== BoardCellValue.CROSS){
                                Cross()
                            }
                            else if(boardCellValue==BoardCellValue.CIRCLE){
                                TicTacToeCircle()
                            }
                        }


                    }
                }
            }

        }
        AnimatedVisibility(visible = state.hasWon,
            enter = scaleIn(tween(100))
        ) {
            VictoryLine(state = state)
            
        }
    }



}

@Composable
fun TicTacToeLines(width: Float, height: Float){
    val quarterHeight = height/3f
    val quarterWidth = width/3f
    HorizontalLine(maxWidth = width - 0f, quarterHeight)
    HorizontalLine(maxWidth = width -0f, yOffset =height - quarterHeight)
    VerticalLine(xOffset =quarterWidth , maxHeight = (height + 0f))
    VerticalLine(xOffset = width - quarterWidth, maxHeight = (height + 0f))
}

@Composable
fun HorizontalLine(maxWidth: Float, yOffset: Float){
    Canvas(modifier = Modifier
        .fillMaxSize()
    ){
        drawLine(
            color = Color.Black,
            start = Offset(0f, yOffset),
            end = Offset(maxWidth, yOffset),
            strokeWidth = 15f,
            cap = StrokeCap.Round
        )

    }
}

@Composable
fun VerticalLine(xOffset: Float, maxHeight: Float){
    Canvas(modifier = Modifier
        .fillMaxSize()
    ){
        drawLine(
            color = Color.Black,
            start = Offset(xOffset, 0f),
            end = Offset(xOffset, maxHeight),
            strokeWidth = 15f,
            cap = StrokeCap.Round
        )

    }
}

@Composable
fun VictoryLine(state: GameState){
    when(state.victoryType){
        VictoryType.HORIZONTAL1 -> HorizontalWinLine(lineNumber = 1)
        VictoryType.HORIZONTAL2 -> HorizontalWinLine(lineNumber = 2)
        VictoryType.HORIZONTAL3 -> HorizontalWinLine(lineNumber = 3)
        VictoryType.VERTICAL1 -> VerticalWinLine(lineNumber = 1)
        VictoryType.VERTICAL2 -> VerticalWinLine(lineNumber = 2)
        VictoryType.VERTICAL3 -> VerticalWinLine(lineNumber = 3)
        VictoryType.DIAGONAL0 -> DiagonalWinLine(diagonalNumber = 0)
        VictoryType.DIAGONAL1 -> DiagonalWinLine(diagonalNumber = 1)
        VictoryType.NONE -> {}
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicTacToeTheme {
        TicTacToeScreen(viewModel = GameViewModel())
    }
}