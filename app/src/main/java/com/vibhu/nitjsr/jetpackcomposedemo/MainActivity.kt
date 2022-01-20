package com.vibhu.nitjsr.jetpackcomposedemo

import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vibhu.nitjsr.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
/*
                Notes :------
1.> Alignment : The horizontal alignment of the layout's children
2.> Arrangement : The vertical arrangement of the layout's children
3.> Main axis(arrangement) and Cross Axis(alignment)
4.> Composable functions must start with Capital letter
5.> Row , Column , Box(rather than row and column box z axis mei ek ke upar ek rakhne lagta hai)
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val painter = painterResource(id = R.drawable.three)
            val description = "Some Anime"
            val title = "I love to make apps in Kotlin"
            Box(modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(16.dp)
            ){
                ImageCard(
                    painter = painter,
                    contentDescripton = description,
                    title = title
                )
            }

            /*Column(modifier = Modifier
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
            }*/
        }
    }

    @Composable
    fun ImageCard(
        painter: Painter,
        contentDescripton: String,
        title: String,
        modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ){
        Box(modifier = Modifier.height(200.dp)){
            Image(
                painter = painter,
                contentDescription = contentDescripton,
                contentScale = ContentScale.Crop
            )
            //todo : Make the Gradient
            Box(modifier= Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    ),
                    startY = 300f
                )
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ){
                Text(title, style = TextStyle(color = Color.White, fontSize = 18.sp))
            }
        }
    }
}


}