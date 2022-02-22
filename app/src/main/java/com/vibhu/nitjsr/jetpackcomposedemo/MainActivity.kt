package com.vibhu.nitjsr.jetpackcomposedemo

import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.layoutId
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.vibhu.nitjsr.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import kotlinx.coroutines.launch
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

        //ConstrintLayout --> Pehle dependency add kar lena
        //Step 1.) Define constraintSet
        //Step 2.) create references to all the widgets you want to constraint, using createRefFor() function
        //Step 3.) define constrains using constrain() function
        //Step 4.) Create a Constraintlayout
        setContent{
            val constraints = ConstraintSet{
                val greenBox = createRefFor("greenbox")
                val redBox = createRefFor("redbox")

                constrain(greenBox){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }
                constrain(redBox){
                    top.linkTo(parent.top)
                    start.linkTo(greenBox.start)
                    end.linkTo(parent.end)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }
            }
            ConstraintLayout(constraints,modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .background(Color.Green)
                    .layoutId("greenbox")
                )
                Box(modifier = Modifier
                    .background(Color.Red)
                    .layoutId("redbox")
                )
            }
        }

        /*      XML RecyclerView    <---->      Compose LazyColumn
        setContent {
            LazyColumn {
                /* Method one : --
                items(80) {
                        Text(
                            text = "Item $it",
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                        )
                }*/
                //Method 2:-
                itemsIndexed(
                    listOf("This","is","Joaquin144","Microsoft","SDE 3")
                ){
                    index,string->
                    Text(
                        text = string,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )
                }
            }
        }
         */
    }
        /*  XML ListView == Compose Column
        setContent{
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier.verticalScroll(state= scrollState)//reverseScrolling->List by default neeche se shuru hogi
            ){
                for(i in 1..50){
                    Text(
                        text = "Item $i",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                    )
                }
            }
        }*/
        /* TextField, Button & SnackBar based on Material Design along with Scaffold and Coroutine launched(this is bad practice)
        setContent{
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()//Inside composable jab Coroutine use karna ho tab iska prayog bilkul mat karein. because Khud ka coroutine create karna is very bad practice except in case of callbacks like onClickListener
            var textFieldState by remember {
                mutableStateOf("")
            }
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp)
                ) {
                    TextField(
                        value = textFieldState,
                        label = {//label maane hint(EditText ko yaad karo)
                            Text("Enter your name")
                        },
                        onValueChange = {
                            textFieldState = it
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))//thodi jagah banane jke liye 2 widgets mei
                    Button(
                        onClick = {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Welcome $textFieldState",null,SnackbarDuration.Short)
                            }
                            //scaffoldState.snackbarHostState.showSnackbar("Welcome ${textFieldState}",null,) --> Inavlid beacuse showSnackbar is a suspend function. #### I don't know why ?
                        }) {
                        Text(text = "Greet me!")
                    }
                }
            }
        }*/
        /**/

        /* Crazy Box -> If you click this then it will change its color randomly
        setContent { 
            Column(Modifier.fillMaxSize()) {
                val color = remember {
                    mutableStateOf(Color.Yellow)
                }
                ColorBox(
                    Modifier.fillMaxSize().weight(1f)
                ){
                    color.value = it
                }
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color.value)
                    .weight(1f)
                )
            }
        }

        */

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



    @Composable
    fun ColorBox(
        modifier: Modifier = Modifier,
        updateColor: (Color) -> Unit) {//updateColor is a lambda function

        Box(modifier = modifier
            .background(Color.Red)
            .clickable {//click karne pe kyav hoga
                updateColor(
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                        1f
                    )
                )
            }
        )
    }
    /*@Composable
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
    }*/
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