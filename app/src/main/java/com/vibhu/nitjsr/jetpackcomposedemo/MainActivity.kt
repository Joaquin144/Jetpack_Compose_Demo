package com.vibhu.nitjsr.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vibhu.nitjsr.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
/*
                Notes :------
1.> Alignment : The horizontal alignment of the layout's children
2.> Arrangement : The vertical arrangement of the layout's children
3.> Main axis(arrangement) and Cross Axis(alignment)
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier
                .fillMaxHeight(0.75f)
                .background(Color.Green)
                .fillMaxWidth(.8f)
                .border(5.dp,Color.Magenta)
                .padding(25.dp)
                .border(5.dp,Color.Black)
                .padding(25.dp)
                .border(5.dp,Color.Red)
                .padding(25.dp)
                .border(5.dp,Color.Yellow)
                .padding(25.dp))
            {
                Text(text = "Hello",Modifier
                    .border(5.dp,Color.Black)
                    .padding(5.dp)
                    .offset(100.dp,200.dp)
                    .border(5.dp,Color.White)
                    .padding(10.dp))
                Spacer(modifier = Modifier.height(60.dp))
                Text(text = "World", modifier = Modifier.clickable {

                })
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Greeting("Vibhu")
    }
}