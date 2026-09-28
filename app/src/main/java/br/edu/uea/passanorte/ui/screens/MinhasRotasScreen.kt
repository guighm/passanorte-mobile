package br.edu.uea.passanorte.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Route
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.edu.uea.passanorte.R
import br.edu.uea.passanorte.Routes
import br.edu.uea.passanorte.ui.components.PassaNorteBottomBar
import br.edu.uea.passanorte.ui.theme.AppBackground
import br.edu.uea.passanorte.ui.theme.BodyText
import br.edu.uea.passanorte.ui.theme.CompassBrown
import br.edu.uea.passanorte.ui.theme.DividerBlue
import br.edu.uea.passanorte.ui.theme.FieldBlue
import br.edu.uea.passanorte.ui.theme.GreenPrimary
import br.edu.uea.passanorte.ui.theme.NavyText

@Composable
fun MinhasRotasScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Header()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Minhas Rotas",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 36.sp,
                    color = NavyText
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Acompanhe seu progresso nas rotas oficiais de Manaus.",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = BodyText
                )
                Spacer(Modifier.height(16.dp))
                RotaCard(
                    nome = "Coração Histórico de Manaus",
                    progresso = "3/4 pontos visitados",
                    fracao = 0.75f
                )
                Spacer(Modifier.height(8.dp))
                RotaCard(
                    nome = "Sabores do Mercado",
                    progresso = "0/3 pontos visitados",
                    fracao = 0f
                )
            }
        }
        PassaNorteBottomBar(currentRoute = Routes.MINHAS_ROTAS) { route ->
            navController.navigate(route) {
                popUpTo(Routes.HOME) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(AppBackground.copy(alpha = 0.9f))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.foundation.Image(
            painter = painterResource(R.drawable.logo_passanorte),
            contentDescription = "PassaNorte",
            modifier = Modifier.size(width = 59.dp, height = 32.dp)
        )
        Spacer(Modifier.width(8.dp))
        Column {
            Text(
                text = "PassaNorte",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 16.sp,
                color = NavyText
            )
            Text(
                text = "Minhas Rotas",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp,
                color = GreenPrimary
            )
        }
    }
}

@Composable
private fun RotaCard(nome: String, progresso: String, fracao: Float) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = CircleShape,
                    color = FieldBlue,
                    modifier = Modifier.size(40.dp),
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Rounded.Route,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                }
                Spacer(Modifier.width(8.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        text = nome,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp,
                        color = NavyText
                    )
                    Text(
                        text = progresso,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 16.sp,
                        color = BodyText
                    )
                }
                Icon(
                    Icons.Rounded.Map,
                    contentDescription = null,
                    tint = CompassBrown,
                    modifier = Modifier.size(19.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(DividerBlue, RoundedCornerShape(50))
            ) {
                Box(
                    Modifier
                        .fillMaxWidth(fracao)
                        .height(10.dp)
                        .background(GreenPrimary, RoundedCornerShape(50))
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Em andamento",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.5.sp,
                color = GreenPrimary
            )
        }
    }
}