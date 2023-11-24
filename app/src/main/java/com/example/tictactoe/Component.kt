package com.example.tictactoe


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Cross(){
    Canvas(modifier = Modifier
        .padding(5.dp)
        .size(60.dp))
    {
        drawLine(
            cap = StrokeCap.Round,
            strokeWidth =15f,
            color = Color.Red,
            start = Offset(x=0f,y=0f),
            end = Offset(x=size.width,y = size.height)

        )
        drawLine(
            cap = StrokeCap.Round,
            strokeWidth =15f,
            color = Color.Red,
            start = Offset(x=size.width,y=0f),
            end = Offset(x=0f,y = size.height)

        )
    }

}

@Composable
fun TicTacToeCircle(){
    Canvas(modifier = Modifier
        .padding(5.dp)
        .size(60.dp)
        ){
        drawCircle(
            color = Color.Blue,
            style = Stroke(width = 20f)
        )
    }
}

@Composable
fun HorizontalWinLine(lineNumber: Int){
    Canvas(modifier = Modifier
        .size(904.dp)
        .aspectRatio(1f)
        ){
       val winningRow =  when(lineNumber){
            1 -> 1
            2 -> 3
            3-> 5
           else ->0
        }
        drawLine(
            cap = StrokeCap.Round,
            strokeWidth =15f,
            color = Color.Red,
            start = Offset(x=0f,y=size.width*winningRow/6),
            end = Offset(x=size.width,y = size.width*winningRow/6)
        )
    }
}

@Composable
fun VerticalWinLine(lineNumber: Int){
    Canvas(modifier = Modifier
        .size(904.dp)
        .aspectRatio(1f)
    ){
        val winningColumn =  when(lineNumber){
            1 -> 1
            2 -> 3
            3-> 5
            else ->0
        }
        drawLine(
            cap = StrokeCap.Round,
            strokeWidth =15f,
            color = Color.Red,
            start = Offset(x=size.width*winningColumn/6,y=0f),
            end = Offset(x = size.width*winningColumn/6,y = size.height)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Previews(){
    HorizontalWinLine(lineNumber = 1)
    HorizontalWinLine(lineNumber = 2)
    HorizontalWinLine(lineNumber = 3)
    VerticalWinLine(lineNumber = 1)
    VerticalWinLine(lineNumber = 2)
    VerticalWinLine(lineNumber = 3)

}