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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vibhu.nitjsr.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import kotlin.random.Random

/*
              My  Notes :------
1.> Alignment : The horizontal alignment of the layout's children
2.> Arrangement : The vertical arrangement of the layout's children
3.> Main axis(arrangement) and Cross Axis(alignment)
4.> Composable functions must start with Capital letter
5.> Row , Column , Box(rather than row and column box z axis mei ek ke upar ek rakhne lagta hai)
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fontFamily = FontFamily(
            Font(R.font.lexend_regular, FontWeight.Bold),
            Font(R.font.lexend_semibold, FontWeight.SemiBold),
            Font(R.font.lexend_thin, FontWeight.Thin)
        )
        /* Crazy Box -> If you click this then it will change its color randomly */


        /**/

        /* Doing text stylings that XML couldn't do
        setContent {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF101010))
            ){
                Text(
                    text = buildAnnotatedString{
                           withStyle(
                               style = SpanStyle(
                                   color = Color.Red,
                                   fontSize = 30.sp,
                                   fontWeight = FontWeight.SemiBold)
                           ){
                                   append("J")
                           }
                            append("etpack ")
                            withStyle(
                            style = SpanStyle(
                                color = Color.Green,
                                fontSize = 40.sp,
                                fontWeight = FontWeight.ExtraBold)
                            ){
                                append("Co")
                            }
                            append("mpose")
                    },
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,//Ye chezz xml mei nahi thi(we have to use koltin for that)

                )
            }

         */

            /*      How to draw an ImageCard
            setContent{
            val painter = painterResource(id = R.drawable.three)
            val description = "Some Anime"
            val title = "I love to make apps in Kotlin"
            Box(modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(16.dp)
            ){
                ImageCard(
                    painter = painter,
                    contentDescription = description,
                    title = title
                )
            }}
             */

            /*      Some fun with Modifiers
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.75f)
                    .background(Color.Green)
                    .fillMaxWidth(.8f)
                    .border(5.dp, Color.Magenta)
                    .padding(25.dp)
                    .border(5.dp, Color.Black)
                    .padding(25.dp)
                    .border(5.dp, Color.Red)
                    .padding(25.dp)
                    .border(5.dp, Color.Yellow)
                    .padding(25.dp)
            )
            {
                Text(
                    text = "Hello", Modifier
                        .border(5.dp, Color.Black)
                        .padding(5.dp)
                        .offset(100.dp, 200.dp)
                        .border(5.dp, Color.White)
                        .padding(10.dp)
                )
                Spacer(modifier = Modifier.height(60.dp))
                Text(text = "World", modifier = Modifier.clickable {

                })
            }
        }*/

    }

    @Composable
    fun ColorBox(modifier: Modifier = Modifier) {//default value de rahe hain
        val color = remember {
            mutableStateOf(Color.Yellow)
        }

        Box(modifier = modifier
            .background(color.value)
            .clickable {//click karne pe kyav hoga
                color.value = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            }
        )
    }
    @Composable
    fun Greeting(){
        Text(text = "Hello Ji Aunty Ji")
    }
    @Composable
    fun Greeting1(){
        Text(text = "Hello Ji Uncle Ji")
    }

    @Composable
    fun ImageCard(
        painter: Painter,
        contentDescription: String,
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
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            //todo : Make the Gradient
            Box(modifier= Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
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