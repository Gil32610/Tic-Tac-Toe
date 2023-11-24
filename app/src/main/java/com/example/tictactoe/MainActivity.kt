package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    BoxWithConstraints (
        modifier = Modifier
            .padding(16.dp)
            .background(Color.Gray)
            .fillMaxSize()
    ){
        HorizontalLine(maxWidth = constraints.maxWidth - 0f, yOffset = 36f + constraints.maxHeight/2f)
        val maxWidth = constraints.maxHeight
        Text(
        text="$maxWidth"
        )
    }
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
            strokeWidth = 30f,
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicTacToeTheme {
        Greeting("Android")
    }
}