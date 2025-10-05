package gdg.pjatk.pw.demo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import demo.composeapp.generated.resources.Res
import demo.composeapp.generated.resources.earth
import demo.composeapp.generated.resources.plus_jakarta_sans_semibold
import gdg.pjatk.pw.demo.navigation.Persona
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun OnboardingStartScreen() {
    val nav = LocalNavigator.currentOrThrow

    val headingFamily = FontFamily(
        Font(Res.font.plus_jakarta_sans_semibold, weight = FontWeight.SemiBold)
    )

    val bg = Color(0xFF000000)
    val textPrimary = Color(0xFFFFFFFF)
    val textSecondary = textPrimary.copy(alpha = 0.72f)
    val textTertiary = textPrimary.copy(alpha = 0.45f)
    val buttonContainer = Color(0xFFFFFFFF)
    val buttonContent = Color(0xFF000000)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
    ) {
        // Earth hero
        Image(
            painter = painterResource(Res.drawable.earth),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .aspectRatio(1f)
                .offset(y = 48.dp),
            contentScale = ContentScale.Crop
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 80.dp)
                .align(Alignment.TopStart)
        ) {
            Text(
                text = "Explore the world.",
                fontFamily = headingFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 40.sp,
                color = textPrimary
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Travel.",
                fontFamily = headingFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 40.sp,
                color = textSecondary
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Inspire.",
                fontFamily = headingFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 40.sp,
                color = textTertiary
            )
        }

        Button(
            onClick = { nav.push(Persona) },
            shape = RoundedCornerShape(100),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonContainer,
                contentColor = buttonContent
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .height(52.dp)
                .padding(horizontal = 24.dp)
        ) {
            Text("Start exploring")
        }
    }
}
