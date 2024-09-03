package me.pegbeer.rickandmortydb.ui.screen


import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import me.pegbeer.rickandmortydb.core.ui.components.CharacterCard
import me.pegbeer.rickandmortydb.core.ui.theme.RickAndMortyDBTheme
import me.pegbeer.rickandmortydb.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
){
    val characters = viewModel.characters.collectAsLazyPagingItems()
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LaunchedEffect(key1 = characters.loadState) {
            if(characters.loadState.refresh is LoadState.Error){
                Toast.makeText(
                    context,
                    "Error: " + (characters.loadState.refresh as LoadState.Error).error.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        Column {
            Box(
                modifier = Modifier.padding(14.dp)
            ){
                Text(text = buildAnnotatedString{
                    withStyle(style = SpanStyle(fontSize = 20.sp)){
                        append("¡Hola,")
                    }
                    withStyle(style = SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)){
                        append(" bienvenido!")
                    }
                })
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp))

            if(characters.loadState.refresh is LoadState.Loading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }else{
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(characters.itemCount){ index ->
                        val character = characters[index]!!
                        CharacterCard(character = character)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen(){
    RickAndMortyDBTheme {
        HomeScreen()
    }
}