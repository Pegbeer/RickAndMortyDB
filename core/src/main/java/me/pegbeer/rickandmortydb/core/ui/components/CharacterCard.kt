package me.pegbeer.rickandmortydb.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.pegbeer.rickandmortydb.core.R
import me.pegbeer.rickandmortydb.core.model.Character
import me.pegbeer.rickandmortydb.core.ui.theme.RickAndMortyDBTheme
import me.pegbeer.rickandmortydb.core.ui.theme.White40
import me.pegbeer.rickandmortydb.core.ui.theme.Typography
import me.pegbeer.rickandmortydb.core.ui.theme.WhiteGray40

@Composable
fun CharacterCard(character: Character, onClick:() -> Unit){
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = WhiteGray40)
            .clickable(
                enabled = true,
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
            )
            .padding(15.dp)
    ){
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = character.name, style = Typography.titleMedium, lineHeight = 18.sp, overflow = TextOverflow.Ellipsis, maxLines = 2)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .align(Alignment.CenterHorizontally),
                model = ImageRequest.Builder(context)
                .data(character.imageUrl)
                .crossfade(true)
                .allowHardware(true)
                .placeholder(R.drawable.image_24)
                .build(), contentDescription = null)
        }
    }
}

@Preview
@Composable
fun PreviewCharacterCard(){
    RickAndMortyDBTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}