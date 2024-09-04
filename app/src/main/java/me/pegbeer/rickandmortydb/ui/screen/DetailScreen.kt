package me.pegbeer.rickandmortydb.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import me.pegbeer.rickandmortydb.core.Result
import me.pegbeer.rickandmortydb.core.ui.theme.Purple40
import me.pegbeer.rickandmortydb.ui.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    id:Int,
    viewModel:DetailViewModel = hiltViewModel(),
    navController: NavController
){
    viewModel.loadData(id)
    val uiState by viewModel.character.collectAsStateWithLifecycle()


    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icons.Outlined.ArrowBack
                }
                Spacer(modifier = Modifier
                    .fillMaxHeight()
                    .width(8.dp))
                Text(text = "Details")
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(14.dp))

            if(uiState.status == Result.Status.LOADING){
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }else if(uiState.status == Result.Status.ERROR){
                Text(text = "Could not process the request")
            }else if (uiState.status == Result.Status.SUCCESS){
                val character = uiState.data!!
                Column(
                    modifier = Modifier.background(color = Purple40)
                ){
                    Text(text = buildAnnotatedString{
                        withStyle(style = SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)){
                            append("Name: ")
                        }
                        withStyle(style = SpanStyle(fontSize = 20.sp)){
                            append(character.name)
                        }
                    })
                }
            }
        }
    }
}