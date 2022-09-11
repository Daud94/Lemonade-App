package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                LemonadeApp(1)
            }
        }
    }
}


@Composable
fun LemonadeApp(stateValue: Int) {
    var stateValue by remember {
        mutableStateOf(stateValue)
    }

    var squeezeCount by remember {
        mutableStateOf(0)
    }

    var imageResource = R.drawable.lemon_tree
    var textResource = R.string.tap_to_select_a_lemon
    var contentDescription = R.string.lemon_tree

    when (stateValue) {
        1 -> {
            imageResource = R.drawable.lemon_tree
            textResource = R.string.tap_to_select_a_lemon
            contentDescription = R.string.lemon_tree
            squeezeCount = (2..4).random()
        }
        2 -> {
            imageResource = R.drawable.lemon_squeeze
            textResource = R.string.squeeze_lemon
            contentDescription = R.string.lemon
        }
        3 -> {
            imageResource = R.drawable.lemon_drink
            textResource = R.string.drink_lemonade
            contentDescription = R.string.glass_of_lemonade

        }
        4 -> {
            imageResource = R.string.start_again
            imageResource = R.drawable.lemon_restart
            contentDescription = R.string.empty_glass
        }
        5 -> {
            stateValue = 1
        }
    }


    androidx.compose.material.Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.clickable { stateValue},
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = stringResource(id = textResource), fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = stringResource(
                    id = contentDescription
                ),
                Modifier
                    .border(
                        BorderStroke(2.dp, Color(105, 205, 216)),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(16.dp).clickable {
                        if (stateValue != 2) {
                            stateValue += 1
                        } else{
                            squeezeCount--
                            stateValue = 2
                            if (squeezeCount == 0) {
                                stateValue = 3
                            }
                        }
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeAppTheme {
        LemonadeApp(1)
    }
}