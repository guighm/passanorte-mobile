package br.edu.uea.passanorte.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Approval
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Flag
import androidx.compose.material.icons.rounded.GpsFixed
import androidx.compose.material.icons.rounded.Leaderboard
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.MilitaryTech
import androidx.compose.material.icons.rounded.Navigation
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material.icons.rounded.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
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
import br.edu.uea.passanorte.ui.theme.LogoRing
import br.edu.uea.passanorte.ui.theme.Mint
import br.edu.uea.passanorte.ui.theme.MutedText
import br.edu.uea.passanorte.ui.theme.NavyText

private val OnlineGreen = Color(0xFF00855D)
private val DarkOverlay = Color(0xFF213145)

@Composable
fun CheckinScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 32.dp)
        ) {
            Header()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 4.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                SubHeader(onVoltar = { navController.popBackStack() })
                TituloSection()
                LocationCard()
                ValidationModule()
                ProgressCard()
                ActionButtons(
                    onConfirmar = { navController.navigate(Routes.CONCLUSAO_ROTA) }
                )
                AuthenticityNotice()
            }
        }
        PassaNorteBottomBar(currentRoute = "") { route ->
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
        Image(
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
                letterSpacing = (-0.4).sp,
                color = NavyText
            )
            Text(
                text = "Perfil",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp,
                color = GreenPrimary
            )
        }
        Spacer(Modifier.weight(1f))
        Box {
            Icon(
                Icons.Rounded.NotificationsNone,
                contentDescription = "Notificações",
                tint = NavyText,
                modifier = Modifier
                    .size(44.dp)
                    .padding(12.dp)
            )
            Box(
                Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-10).dp, y = 10.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(CompassBrown)
            )
        }
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color(0xFFD3E4FE))
                .padding(2.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.profile_marina),
                contentDescription = "Perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
private fun SubHeader(onVoltar: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = onVoltar)
        ) {
            Icon(
                Icons.Rounded.ArrowBack,
                contentDescription = null,
                tint = GreenPrimary,
                modifier = Modifier.size(14.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Voltar ao Ponto",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp,
                color = GreenPrimary
            )
        }
        Spacer(Modifier.weight(1f))
        Surface(
            shape = RoundedCornerShape(50),
            color = DividerBlue,
            shadowElevation = 1.dp
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Rounded.GpsFixed,
                    contentDescription = null,
                    tint = BodyText,
                    modifier = Modifier.size(13.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "GPS Ativo (±3m)",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,
                    letterSpacing = (-0.275).sp,
                    color = BodyText
                )
            }
        }
    }
}

@Composable
private fun TituloSection() {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = "Check-in de Visitação",
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 36.sp,
            letterSpacing = (-0.7).sp,
            color = NavyText
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Confirme sua localização para estampar seu passaporte digital.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
            color = BodyText
        )
    }
}

@Composable
private fun LocationCard() {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(176.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.checkin_hero),
                    contentDescription = "Teatro Amazonas",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    DarkOverlay.copy(alpha = 0.2f),
                                    DarkOverlay.copy(alpha = 0.8f)
                                )
                            )
                        )
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = "PONTO HISTÓRICO #09",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 16.sp,
                            letterSpacing = 0.55.sp,
                            color = Mint
                        )
                        Text(
                            text = "Teatro Amazonas",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 27.5.sp,
                            color = Color.White
                        )
                        Text(
                            text = "Largo de São Sebastião, Manaus - AM",
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            letterSpacing = 0.25.sp,
                            color = DividerBlue.copy(alpha = 0.9f)
                        )
                    }
                    Icon(
                        Icons.Rounded.Navigation,
                        contentDescription = null,
                        tint = Mint,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
            Surface(color = FieldBlue) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(OnlineGreen.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Rounded.Navigation,
                            contentDescription = null,
                            tint = OnlineGreen,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = "Dentro do Perímetro Seguro",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp,
                            color = GreenPrimary
                        )
                        Text(
                            text = buildAnnotatedString {
                                append("Raio de validação: 50m • ")
                                withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = NavyText)) {
                                    append("Você está a 18 metros")
                                }
                            },
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp,
                            color = BodyText
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = GreenPrimary.copy(alpha = 0.1f)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(GreenPrimary)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = "Pronto",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 16.sp,
                                letterSpacing = 0.5.sp,
                                color = GreenPrimary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ValidationModule() {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box {
            Box(
                Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 32.dp, y = (-32).dp)
                    .size(176.dp)
                    .clip(CircleShape)
                    .background(FieldBlue.copy(alpha = 0.24f))
            )
            Column(Modifier.padding(16.dp)) {
                // Stage 1: telemetria GPS ao vivo
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = FieldBlue.copy(alpha = 0.7f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Mint.copy(alpha = 0.4f))
                                .border(1.dp, GreenPrimary.copy(alpha = 0.4f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Rounded.GpsFixed,
                                contentDescription = null,
                                tint = GreenPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(Modifier.width(16.dp))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "GEOFENCING ATIVO",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    lineHeight = 16.sp,
                                    letterSpacing = 0.5.sp,
                                    color = GreenPrimary
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    text = "03°07'49\"S 60°01'24\"W",
                                    fontSize = 10.sp,
                                    lineHeight = 15.sp,
                                    letterSpacing = (-0.5).sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = BodyText
                                )
                            }
                            Text(
                                text = "Validando presença no perímetro histórico via GPS...",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 16.sp,
                                letterSpacing = 0.25.sp,
                                color = NavyText
                            )
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
                // Stage 2: revelação do carimbo digital
                Surface(
                    shape = RoundedCornerShape(32.dp),
                    color = FieldBlue.copy(alpha = 0.4f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box {
                            Box(
                                modifier = Modifier
                                    .size(112.dp)
                                    .clip(CircleShape)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp)
                                        .clip(CircleShape)
                                        .background(FieldBlue.copy(alpha = 0.3f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Icon(
                                            Icons.Rounded.MilitaryTech,
                                            contentDescription = null,
                                            tint = CompassBrown,
                                            modifier = Modifier.size(width = 15.dp, height = 30.dp)
                                        )
                                        Spacer(Modifier.height(3.dp))
                                        Text(
                                            text = "CARIMBO #09",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            lineHeight = 13.5.sp,
                                            letterSpacing = 0.45.sp,
                                            color = CompassBrown
                                        )
                                    }
                                }
                            }
                            Surface(
                                shape = RoundedCornerShape(50),
                                color = CompassBrown,
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .offset(x = (-6).dp, y = (-6).dp)
                            ) {
                                Text(
                                    text = "OFICIAL",
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 12.sp,
                                    letterSpacing = (-0.2).sp,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(50),
                                color = GreenPrimary,
                                shadowElevation = 1.dp,
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .offset(x = 4.dp, y = 8.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        Icons.Rounded.Check,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(11.dp)
                                    )
                                    Spacer(Modifier.width(2.dp))
                                    Text(
                                        text = "Apto",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        lineHeight = 15.sp,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                        Spacer(Modifier.height(16.dp))
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = Color(0xFFFFDCC3)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Rounded.WorkspacePremium,
                                    contentDescription = null,
                                    tint = Color(0xFF2F1500),
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    text = "+150 Pontos de Explorador",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 16.sp,
                                    letterSpacing = 0.5.sp,
                                    color = Color(0xFF2F1500)
                                )
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Guardião da Ópera Amazônica",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 24.sp,
                            letterSpacing = 0.15.sp,
                            color = NavyText,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Teatro Amazonas • Coleção Marco Zero",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp,
                            color = BodyText
                        )
                        Spacer(Modifier.height(8.dp))
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = LogoRing
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Rounded.CalendarMonth,
                                    contentDescription = null,
                                    tint = BodyText,
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    text = "Registro: Hoje, 21:46",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    lineHeight = 16.sp,
                                    letterSpacing = 0.5.sp,
                                    color = BodyText
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProgressCard() {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Rounded.Leaderboard,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "Progresso da Rota",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        color = NavyText
                    )
                }
                Surface(
                    shape = RoundedCornerShape(50),
                    color = GreenPrimary.copy(alpha = 0.1f)
                ) {
                    Text(
                        text = "75% Concluído",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 16.sp,
                        letterSpacing = 0.5.sp,
                        color = GreenPrimary,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Rota Centro Histórico de Manaus",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 16.sp,
                    letterSpacing = 0.5.sp,
                    color = NavyText,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "3 de 4 pontos visitados",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,
                    letterSpacing = 0.5.sp,
                    color = BodyText
                )
            }
            Spacer(Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(50))
                    .background(LogoRing)
            ) {
                // Figma 1:1912: fill com inset de 2px (altura 6px) e largura ~74%
                Box(
                    Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 2.dp)
                        .fillMaxWidth(0.74f)
                        .height(6.dp)
                        .clip(RoundedCornerShape(50))
                        .background(GreenPrimary)
                )
            }
            Spacer(Modifier.height(4.dp))
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = FieldBlue
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.Flag,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Próximo e último destino",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp,
                            color = NavyText
                        )
                        Text(
                            text = "Mercado Municipal Adolpho Lisboa (a 1.2 km)",
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            letterSpacing = 0.25.sp,
                            color = BodyText
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ActionButtons(onConfirmar: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(50),
            color = GreenPrimary,
            shadowElevation = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable(onClick = onConfirmar),
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.Approval,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Confirmar & Registrar no Passaporte",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp,
                    color = Color.White
                )
            }
        }
        }
        Surface(
            shape = RoundedCornerShape(50),
            color = LogoRing,
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .clickable { },
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.Map,
                    contentDescription = null,
                    tint = NavyText,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "Ver Rota Completa no Mapa",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp,
                    color = NavyText
                )
            }
        }
        }
    }
}

@Composable
private fun AuthenticityNotice() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Rounded.WorkspacePremium,
            contentDescription = null,
            tint = BodyText,
            modifier = Modifier.size(13.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = "Validação criptografada por geolocalização e carimbo anti-fraude.",
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            color = BodyText
        )
    }
}