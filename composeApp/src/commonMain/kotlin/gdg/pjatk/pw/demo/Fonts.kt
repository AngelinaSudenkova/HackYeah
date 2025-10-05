import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import demo.composeapp.generated.resources.Res
import demo.composeapp.generated.resources.plus_jakarta_sans_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun PlusJakartaSemiBold(): FontFamily =
    FontFamily(
        Font(Res.font.plus_jakarta_sans_semibold, weight = FontWeight.SemiBold)
    )