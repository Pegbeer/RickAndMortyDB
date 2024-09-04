package me.pegbeer.rickandmortydb.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.pegbeer.rickandmortydb.R
import me.pegbeer.rickandmortydb.core.Result
import me.pegbeer.rickandmortydb.core.model.Character
import me.pegbeer.rickandmortydb.core.ui.theme.Purple40
import me.pegbeer.rickandmortydb.core.ui.theme.Purple80
import me.pegbeer.rickandmortydb.ui.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    id:Int,
    viewModel:DetailViewModel = hiltViewModel(),
    navController: NavController
){
    viewModel.characterId.value = id
    val uiState by viewModel.character.collectAsStateWithLifecycle()

    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_arrow_back_24),
                        contentDescription = null
                    )
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
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CircularProgressIndicator()
                }
            }else if(uiState.status == Result.Status.ERROR){
                Text(text = "Could not process the request")
            }else if (uiState.status == Result.Status.SUCCESS){
                val character = uiState.data as Character
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxHeight(.3f),
                        model = ImageRequest.Builder(context)
                            .data(character.imageUrl)
                            .crossfade(enable = true)
                            .placeholder(me.pegbeer.rickandmortydb.core.R.drawable.image_24)
                            .build(),
                        contentDescription = null)

                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(16.dp,0.dp)
                    ) {
                        Text(text = buildAnnotatedString{
                            withStyle(style = SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)){
                                append("Name: ")
                            }
                            withStyle(style = SpanStyle(fontSize = 20.sp)){
                                append(character.name)
                            }
                        })
                        Text(text = buildAnnotatedString{
                            withStyle(style = SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)){
                                append("Gender: ")
                            }
                            withStyle(style = SpanStyle(fontSize = 20.sp)){
                                append(character.gender)
                            }
                        })
                        Text(text = buildAnnotatedString{
                            withStyle(style = SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)){
                                append("Status: ")
                            }
                            withStyle(style = SpanStyle(fontSize = 20.sp)){
                                append(character.status)
                            }
                        })
                        Text(text = buildAnnotatedString{
                            withStyle(style = SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)){
                                append("Origin: ")
                            }
                            withStyle(style = SpanStyle(fontSize = 20.sp)){
                                append(character.origin)
                            }
                        })
                        Text(text = buildAnnotatedString{
                            withStyle(style = SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)){
                                append("Dimension: ")
                            }
                            withStyle(style = SpanStyle(fontSize = 20.sp)){
                                append(character.dimension)
                            }
                        })
                    }
                }
            }
        }
    }
}