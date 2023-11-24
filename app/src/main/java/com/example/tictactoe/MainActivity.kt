package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun TicTacToeScreen(){
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
            val charSize = 22.sp
            Text(
                text = "the truth",
                fontSize = charSize
            )
            Text(
                text = "score",
                fontSize = charSize
            )
            Text(
                text="another score",
                fontSize = charSize
            )
        }
        Text(
            text = stringResource(R.string.tictactoe_title),
            fontFamily = FontFamily.Monospace,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
        TicTacToeBox()
        Text(
            text = stringResource(R.string.x_player_turn)
        )
        Button(
            onClick = {

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


@Composable
fun TicTacToeBox( modifier: Modifier = Modifier) {
    BoxWithConstraints (
        modifier = Modifier

            .background(Color.Gray, shape = RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ){
        val width = constraints.maxWidth+ 0f
        val height =constraints.maxHeight+0f


        TicTacToeLines(width = width, height =height)
        Text(
            text = "width: $width\nheight: $height"
        )
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
            strokeWidth = 20f,
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
            strokeWidth = 20f,
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicTacToeTheme {
        TicTacToeScreen()
    }
}