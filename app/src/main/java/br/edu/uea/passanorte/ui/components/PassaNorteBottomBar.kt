package br.edu.uea.passanorte.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.uea.passanorte.ui.theme.AppBackground
import br.edu.uea.passanorte.ui.theme.BodyText
import br.edu.uea.passanorte.ui.theme.CompassBrown
import br.edu.uea.passanorte.ui.theme.GreenPrimary

// Figma Nav (1:1967): fundo translúcido, item ativo com pill rgba(0,133,93,0.2),
// labels 11sp Medium, dot de notificação #904d00 e home-indicator 128x4.
private val NavActivePill = Color(0xFF00855D).copy(alpha = 0.2f)
private val NavBackground = AppBackground.copy(alpha = 0.9f)
private val HomeIndicator = Color(0xFF0B1C30).copy(alpha = 0.2f)

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val iconSize: Dp = 20.dp,
    val showDot: Boolean = false
)

@Composable
fun PassaNorteBottomBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem("home", "Descoberta", Icons.Rounded.Explore, iconSize = 20.dp),
        BottomNavItem("minhas_rotas", "Minhas Rotas", Icons.Rounded.Map, iconSize = 18.dp, showDot = true),
        BottomNavItem("guia_ia", "Guia IA", Icons.Rounded.AutoAwesome, iconSize = 22.dp),
        BottomNavItem("perfil", "Perfil", Icons.Rounded.Person, iconSize = 16.dp)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(NavBackground)
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(23.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                BottomBarButton(
                    item = item,
                    selected = currentRoute == item.route,
                    onClick = { onNavigate(item.route) }
                )
            }
        }
        // Home indicator (1:1994)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .padding(bottom = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(width = 128.dp, height = 4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(HomeIndicator)
            )
        }
    }
}

@Composable
private fun RowScope.BottomBarButton(
    item: BottomNavItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    val tint = if (selected) GreenPrimary else BodyText
    Column(
        modifier = Modifier
            .weight(1f)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 64.dp, height = 32.dp)
                .clip(RoundedCornerShape(50))
                .background(if (selected) NavActivePill else Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label,
                tint = tint,
                modifier = Modifier.size(item.iconSize)
            )
            if (item.showDot) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = (-6).dp, y = 2.dp)
                        .size(8.dp)
                        .clip(RoundedCornerShape(50))
                        .background(CompassBrown)
                )
            }
        }
        Text(
            text = item.label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.5.sp,
            color = if (selected) GreenPrimary else BodyText
        )
    }
}