package com.example.jettipapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettipapplication.components.InputField
import com.example.jettipapplication.ui.theme.JetTipApplicationTheme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                MyApp {
                    TopHeader()
                }
        }
    }
}

@Composable
fun MyApp(content : @Composable () -> Unit){
    JetTipApplicationTheme {

        Surface(color = MaterialTheme.colorScheme.background) {
                content()
        }
    }
    }

//@Preview
@Composable
fun TopHeader(totalPerPerson: Double = 134.0){
        Surface(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
            color = Color(0xFFD0C4DA)
        ){
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ){
                val total = "%.2f".format(totalPerPerson)
                Text(text = "Total Per Person" ,
                    fontSize = 25.sp)
                Text(text = "ksh $total",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold)

            }
        }
}

@Preview
@Composable
fun MainContent(){
    val totalBillState = remember{ mutableStateOf("") }
    val validState = remember(totalBillState.value){totalBillState.value.trim().isNotEmpty()}
    Surface(modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp , color = Color.LightGray)
    ) {
        Column() {
            InputField(valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions{
                    if(!validState) return@KeyboardActions

                } )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetTipApplicationTheme {
        MyApp {
            Text(text = "Hello Again")
        }
    }
}