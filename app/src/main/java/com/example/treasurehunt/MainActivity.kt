package com.example.treasurehunt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.treasurehunt.ui.theme.TreasureHuntTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "/home"
            ) {
                composable("/home") {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = { navController.navigate("/tip_01") }) { Text("Iniciar Caça ao Tesouro") }
                    }


                }
                composable("/tip_01") {
                    tipScreen(
                        title = "Dica 01",
                        tip = "5 + 5?",
                        answer = "10",
                        clickB1 = { navController.navigate("/tip_02") },
                        clickB2 = { navController.navigateUp() },
                        buttonText01 = "Próxima Dica",
                        buttonText02 = "Voltar"
                    )
                }
                composable("/tip_02") {
                    tipScreen(
                        title = "Dica 02",
                        tip = "10 + 10?",
                        answer = "20",
                        clickB1 = { navController.navigate("/tip_03") },
                        clickB2 = { navController.navigateUp() },
                        buttonText01 = "Próxima Dica",
                        buttonText02 = "Voltar"
                    )
                }
                composable("/tip_03") {
                    tipScreen(
                        title = "Dica 03",
                        tip = "A metade da segunda resposta multiplicada pelo dobro da primeira",
                        answer = "200",
                        clickB1 = { navController.navigate("/treasure") },
                        clickB2 = { navController.navigateUp() },
                        buttonText01 = "Próxima Dica",
                        buttonText02 = "Voltar"
                    )
                }
                composable("/treasure") {
                    tipScreen(
                        title = "Parabéns, você encontrou o tesouro!",
                        tip = "",
                        clickB1 = { navController.navigate("/home") },
                        clickB2 = { navController.navigate("/tip_01") },
                        buttonText01 = "Voltar ao Início",
                        buttonText02 = "Reiniciar Caçada",
                        answer = ""
                    )
                }
            }
        }
    }
}

@Composable
fun tipScreen(
    title: String,
    tip: String,
    answer: String,
    clickB1: () -> Unit,
    clickB2: () -> Unit,
    buttonText01: String,
    buttonText02: String,
) {
    var userInput by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            title, style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)

        )
        Text(
            tip, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        if (tip.isNotEmpty()) {
            TextField(
                value = userInput,
                onValueChange = { userInput = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(0.8f)
            )
        }
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        Button(onClick = {
            if (userInput == answer) {
                clickB1()
                errorMessage = ""
            } else {
                errorMessage = "Resposta incorreta!"
            }
        }, modifier = Modifier.width(250.dp)) { Text(buttonText01) }
        Button(onClick = clickB2, modifier = Modifier.width(250.dp)) { Text(buttonText02) }

    }
}

