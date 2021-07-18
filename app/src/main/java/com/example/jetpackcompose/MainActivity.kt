package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            ColumSetups()
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultText() {


        ColumSetups()


    }

    @Composable
    fun ColumSetups() {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {

            Row(
                Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(100.dp)
                    .border(BorderStroke(5.dp, Color.Black), RoundedCornerShape(10.dp))
                    .padding(5.dp)
                    .border(5.dp, Color.White, RoundedCornerShape(10.dp))
                    .padding(5.dp)
                    .border(5.dp, Color.Red, RoundedCornerShape(10.dp))
                    .background(Color.Blue, RoundedCornerShape(10.dp)),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                TextSetups(string = "Hello")
                TextSetups(string = "World")
                TextSetups(string = "Back")
            }

            ImageCard(
                painter = painterResource(id = R.drawable.jungle),
                contentDescription = "Android",
                title = "Android is highly recommended"
            )

            BuildText()

            OnStateChange()

            TextFieldDesgin()

        }

    }

    @Composable
    fun TextSetups(string: String) {

        Text(
            string, color = Color.White, fontSize = 18.sp,
            modifier = Modifier
                .background(Color.Green, shape = RoundedCornerShape(10.dp))
                .border(2.dp, Color.White, RoundedCornerShape(10.dp))
                .padding(5.dp)
        )

    }

    @Composable
    fun ImageCard(
        painter: Painter,
        contentDescription: String,
        title: String,
        modifier: Modifier = Modifier
    ) {

        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(5.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp
        ) {

            Box(modifier = Modifier.height(180.dp)) {

                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.FillWidth
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ), startY = 250f
                            )
                        )
                ) {
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(text = title, style = TextStyle(fontSize = 16.sp, color = Color.White))
                }

            }

        }

    }

    @Composable
    fun BuildText() {

        val fontFamily = FontFamily(
            Font(R.font.old_standart_regular, FontWeight.Normal),
            Font(R.font.old_standart_bold, FontWeight.Bold),
            Font(R.font.old_standart_italic, FontWeight.Thin),
        )

        Box(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .background(color = Color.Black, RoundedCornerShape(5.dp))
                .padding(10.dp)

        ) {

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            fontSize = 36.sp
                        )
                    ) {
                        append("J")
                    }
                    append("etpack  ")
                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            fontSize = 36.sp
                        )
                    ) {
                        append("C")
                    }
                    append("ompose")

                },
                fontSize = 24.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Thin,
                color = Color(0xFFFFFFFF)
            )

        }

    }


    @Composable
    fun OnStateChange() {

        val color = remember {
            mutableStateOf(Color.Green)
        }


        Box(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(100.dp)
            .background(color.value, RoundedCornerShape(10.dp))
            .clickable {

                color.value = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                )

            }) {

            Text(
                text = "Click Please", modifier = Modifier.align(Alignment.Center),
                fontSize = 24.sp,
                color = Color.White,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )

        }

    }

    @Composable
    fun TextFieldDesgin() {

        val scaffoldState = rememberScaffoldState()
        var textFieldState by remember {
            mutableStateOf("")
        }

        val scope = rememberCoroutineScope()

        Scaffold(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            scaffoldState = scaffoldState
        ) {

            Column {

                OutlinedTextField(
                    value = textFieldState,
                    label = {
                        Text(text = "Enter your name")
                    },
                    onValueChange = { newText ->
                        textFieldState = newText
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                ) 
                
                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                    }
                }) {
                    Text(text = "Greet")
                }

            }

        }

    }

}