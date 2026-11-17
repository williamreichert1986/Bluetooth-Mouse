
package com.example.bluetoothmouse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MouseScreen()
        }
    }
}

@Composable
fun MouseScreen() {
    var status by remember {
        mutableStateOf("Bluetooth desconectado")
    }

    var movement by remember {
        mutableStateOf("Touchpad pronto")
    }

    var leftPressed by remember {
        mutableStateOf(false)
    }

    var rightPressed by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF101820)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {

            Text(
                text = "BLUETOOTH MOUSE",
                color = Color.White,
                fontSize = 21.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = status,
                color = Color(0xFF80CBC4),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(
                        Color(0xFF263746),
                        RoundedCornerShape(18.dp)
                    )
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                change.consume()
                                movement =
                                    "Movimento: ${dragAmount.x.toInt()}, ${dragAmount.y.toInt()}"
                            }
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "ÁREA DO TOUCHPAD",
                        color = Color.White,
                        fontSize = 22.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        movement,
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )

                    Text(
                        "Deslize o dedo nesta área",
                        color = Color.LightGray,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(76.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        rightPressed = !rightPressed
                    },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (rightPressed) Color(0xFF008577)
                            else Color(0xFF455A64)
                    )
                ) {
                    Text(
                        "BOTÃO ESQUERDO\nClique direito",
                        fontSize = 13.sp
                    )
                }

                Button(
                    onClick = {
                        leftPressed = !leftPressed
                    },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (leftPressed) Color(0xFF008577)
                            else Color(0xFF455A64)
                    )
                ) {
                    Text(
                        "BOTÃO DIREITO\nClique esquerdo",
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Versão inicial de desenvolvimento",
                color = Color.Gray,
                fontSize = 11.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
