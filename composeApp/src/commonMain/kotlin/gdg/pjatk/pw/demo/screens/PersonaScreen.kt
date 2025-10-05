package gdg.pjatk.pw.demo.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import demo.composeapp.generated.resources.Res
import demo.composeapp.generated.resources.earth
import gdg.pjatk.pw.demo.navigation.CountryPicker
import gdg.pjatk.pw.demo.presentation.PersonaViewModel
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.shape.RoundedCornerShape

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PersonaScreen() {
    val nav = LocalNavigator.currentOrThrow
    val viewModel = remember { PersonaViewModel() }
    val state by viewModel.state.collectAsState()

    val buttonContainer = Color(0xFFFFFFFF)
    val buttonContent = Color(0xFF000000)

    val infinite = rememberInfiniteTransition(label = "earth")
    val driftY by infinite.animateFloat(
        initialValue = -6f, targetValue = 6f,
        animationSpec = infiniteRepeatable(tween(16000, 1000), RepeatMode.Reverse),
        label = "driftY"
    )
    val rotate by infinite.animateFloat(
        initialValue = -2f, targetValue = 2f,
        animationSpec = infiniteRepeatable(tween(22000, 1000), RepeatMode.Reverse),
        label = "rotate"
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clipToBounds()
    ) {

        Box(
            Modifier
                .size(520.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 60.dp, y = 40.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(Color(0x4436E4FF), Color.Transparent)
                    )
                )
        )

        Image(
            painter = painterResource(Res.drawable.earth),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxWidth(1.25f)
                .aspectRatio(1f)
                .offset(y = 48.dp + driftY.dp)
                .graphicsLayer(
                    rotationZ = rotate,
                    transformOrigin = TransformOrigin(1f, 1f)
                ),
            contentScale = ContentScale.FillWidth
        )

        Column(
            Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(Modifier.height(64.dp))

            Text(
                "Our AI will find the\nbest option for you.",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )

            Spacer(Modifier.height(64.dp))


            val allTags = listOf("Couple", "Family", "Tech", "Relaxing", "Active", "Culture", "Solo")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                items(allTags) { tag ->
                    FilterChip(
                        selected = tag in state.tags,
                        onClick = { viewModel.toggleTag(tag) },
                        label = { Text(tag) },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color(0x22FFFFFF),
                            labelColor = Color.White,
                            selectedContainerColor = Color.White,
                            selectedLabelColor = Color.Black
                        )
                    )
                }
            }


            if (state.tags.isNotEmpty()) {
                Spacer(Modifier.height(10.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    state.tags.forEach { tag ->
                        FilterChip(
                            selected = true,
                            onClick = { viewModel.toggleTag(tag) },
                            label = { Text(tag) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color.White,
                                selectedLabelColor = Color.Black
                            )
                        )
                    }
                }
            }

            Spacer(Modifier.height(64.dp))

            OutlinedTextField(
                value = state.about,
                onValueChange = { viewModel.updateAbout(it) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Tell us about your activity preferences") },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color(0x66FFFFFF),
                    unfocusedIndicatorColor = Color(0x33FFFFFF),
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedPlaceholderColor = Color(0xAAFFFFFF),
                    unfocusedPlaceholderColor = Color(0x88FFFFFF)
                )
            )

            Spacer(Modifier.height(20.dp))

            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    shape = RoundedCornerShape(100),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonContainer,
                        contentColor = buttonContent
                    ), onClick = { nav.push(CountryPicker()) }) {
                    Text("Explore")
                }
            }



            Spacer(Modifier.height(28.dp))
        }
    }
}
