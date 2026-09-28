package br.edu.uea.passanorte.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.ContentCopy
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.VolunteerActivism
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material.icons.rounded.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
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
import br.edu.uea.passanorte.ui.theme.NavyText

private val Mint = Color(0xFF85F8C4)
private val Orange = Color(0xFFFE932C)
private val GoldLight = Color(0xFFFFD778)
private val GoldMid = Color(0xFFE49B14)
private val GoldDark = Color(0xFF975800)

@Composable
fun ConclusaoRotaScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        // Blobs decorativos de fundo
        Blob(
            Modifier
                .offset(x = (-48).dp, y = (-48).dp)
                .size(256.dp)
                .blur(48.dp),
            Color(0x4DFFDCC3)
        )
        Blob(
            Modifier
                .align(Alignment.TopEnd)
                .offset(x = 64.dp, y = 192.dp)
                .size(288.dp)
                .blur(48.dp),
            Color(0x5985F8C4)
        )
        Blob(
            Modifier
                .align(Alignment.TopCenter)
                .offset(y = 384.dp)
                .size(320.dp)
                .blur(48.dp),
            Color(0x1AFE932C)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 112.dp)
        ) {
            Header()
            TopPillRow(onClose = { navController.popBackStack() })
            CongratsSection()
            MedalSection()
            RewardCard()
            EtapasCard()
            ActionButtons(
                onExplorar = { navController.navigate(Routes.HOME) }
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(AppBackground)
        ) {
            PassaNorteBottomBar(currentRoute = "") { route ->
                navController.navigate(route) {
                    popUpTo(Routes.HOME) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }
}

@Composable
private fun Blob(modifier: Modifier, color: Color) {
    Box(modifier.clip(CircleShape).background(color))
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
private fun TopPillRow(onClose: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = RoundedCornerShape(50),
            color = Color(0x26FE932C),
            shadowElevation = 1.dp
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "🎉", fontSize = 14.sp)
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "ROTA FINALIZADA!",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 16.sp,
                    color = CompassBrown
                )
            }
        }
        Spacer(Modifier.weight(1f))
        Surface(
            shape = CircleShape,
            color = DividerBlue,
            modifier = Modifier
                .size(40.dp)
                .clickable(onClick = onClose),
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(
                Icons.Rounded.Close,
                contentDescription = "Fechar resumo",
                tint = NavyText,
                modifier = Modifier.size(16.dp)
            )
        }
        }
    }
}

@Composable
private fun CongratsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = RoundedCornerShape(50),
            color = GreenPrimary.copy(alpha = 0.1f)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Rounded.WorkspacePremium,
                    contentDescription = null,
                    tint = GreenPrimary,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "PassaNorte Conquistas",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 16.sp,
                    letterSpacing = 0.5.sp,
                    color = GreenPrimary
                )
            }
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Parabéns, Desbravador!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
            letterSpacing = (-0.7).sp,
            color = NavyText,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = buildAnnotatedString {
                append("Você completou todos os 4 pontos da rota ")
                withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = GreenPrimary)) {
                    append("\"Coração Histórico de Manaus\"")
                }
                append(".")
            },
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
            color = BodyText,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}

@Composable
private fun MedalSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {
            Box(
                modifier = Modifier
                    .size(224.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0x4DFE932C),
                                Color(0x66FFDCC3),
                                Color(0x3385F8C4)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(192.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.verticalGradient(
                                listOf(GoldLight, GoldMid, GoldDark),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY
                            )
                        )
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(R.drawable.medal_logo),
                                contentDescription = null,
                                modifier = Modifier.size(45.dp)
                            )
                            Spacer(Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Rounded.Star,
                                    contentDescription = null,
                                    tint = CompassBrown,
                                    modifier = Modifier.size(11.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text(
                                    text = "HONRA DO NORTE",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    letterSpacing = 0.55.sp,
                                    color = CompassBrown
                                )
                                Spacer(Modifier.width(4.dp))
                                Icon(
                                    Icons.Rounded.Star,
                                    contentDescription = null,
                                    tint = CompassBrown,
                                    modifier = Modifier.size(11.dp)
                                )
                            }
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = "MANAUS • AM",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = (-0.275).sp,
                                color = BodyText
                            )
                        }
                    }
                }
            }
            Surface(
                shape = RoundedCornerShape(50),
                shadowElevation = 4.dp,
                modifier = Modifier.offset(y = (-8).dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient(
                                listOf(CompassBrown, Orange, CompassBrown)
                            )
                        )
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.WorkspacePremium,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "MESTRE HISTÓRICO",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 16.sp,
                        letterSpacing = 0.6.sp,
                        color = Color.White
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Surface(
            shape = RoundedCornerShape(50),
            color = FieldBlue
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(CompassBrown)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Insígnia Rara • Adicionada à sua galeria",
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

@Composable
private fun RewardCard() {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0x80FFDCC3)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Rounded.VolunteerActivism,
                            contentDescription = null,
                            tint = CompassBrown,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(Modifier.width(4.dp))
                    Column {
                        Text(
                            text = "RECOMPENSA OFICIAL",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.55.sp,
                            color = CompassBrown
                        )
                        Text(
                            text = "Kit Turista Sustentável",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 24.sp,
                            letterSpacing = 0.15.sp,
                            color = NavyText
                        )
                    }
                }
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color(0xFFFFDAD6)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Rounded.Schedule,
                            contentDescription = null,
                            tint = Color(0xFF93000A),
                            modifier = Modifier.size(11.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "5 dias",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 16.sp,
                            color = Color(0xFF93000A)
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Inclui 1 Ecopack em lona amazônica reciclada + Mini Guia de Bolso ilustrado das rotas urbanas.",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
                color = BodyText
            )
            Spacer(Modifier.height(8.dp))
            // Bloco do cupom com QR Code
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = FieldBlue
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.White,
                        shadowElevation = 1.dp,
                        modifier = Modifier.size(144.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.voucher_qr),
                            contentDescription = "QR Code do voucher",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Código do cupom para validação",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 16.sp,
                        letterSpacing = 0.5.sp,
                        color = BodyText
                    )
                    Spacer(Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "PN-MANAUS-7829-X",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 24.sp,
                            letterSpacing = 1.6.sp,
                            color = GreenPrimary
                        )
                        Spacer(Modifier.width(8.dp))
                        Surface(
                            shape = CircleShape,
                            color = LogoRing,
                            modifier = Modifier
                                .size(28.dp)
                                .clickable { },
                        ) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Icon(
                                Icons.Rounded.ContentCopy,
                                contentDescription = "Copiar código",
                                tint = NavyText,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                        }
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = LogoRing
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        Icons.Rounded.Info,
                        contentDescription = null,
                        tint = NavyText,
                        modifier = Modifier.size(17.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Pontos de Retirada Presencial:",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp,
                            color = NavyText
                        )
                        Text(
                            text = "CAT Largo de São Sebastião ou Balcão de Turismo do Mercado Municipal Adolpho Lisboa.",
                            fontSize = 14.sp,
                            lineHeight = 19.25.sp,
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
private fun EtapasCard() {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = FieldBlue,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Etapas Concluídas (4/4)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp,
                    letterSpacing = 0.15.sp,
                    color = NavyText,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "100% Completa",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 16.sp,
                    letterSpacing = 0.5.sp,
                    color = GreenPrimary
                )
            }
            Spacer(Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                listOf(
                    "Teatro Amazonas",
                    "Palácio Rio Negro",
                    "Praça São Sebastião",
                    "Mercado Adolpho Lisboa"
                ).forEach { nome ->
                    EtapaRow(nome)
                }
            }
        }
    }
}

@Composable
private fun EtapaRow(nome: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Mint),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Rounded.Check,
                    contentDescription = null,
                    tint = GreenPrimary,
                    modifier = Modifier.size(14.dp)
                )
            }
            Spacer(Modifier.width(8.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = nome,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp,
                    color = NavyText
                )
                Text(
                    text = "Carimbado com sucesso",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,
                    letterSpacing = 0.5.sp,
                    color = BodyText
                )
            }
            Icon(
                Icons.Rounded.Approval,
                contentDescription = null,
                tint = CompassBrown,
                modifier = Modifier.size(19.dp)
            )
        }
    }
}

@Composable
private fun ActionButtons(onExplorar: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(50),
            color = GreenPrimary,
            shadowElevation = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable { },
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.Wallet,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Salvar Voucher na Carteira Digital",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp,
                    color = Color.White
                )
            }
        }
        }
        Surface(
            shape = RoundedCornerShape(50),
            color = DividerBlue,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable { },
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.Share,
                    contentDescription = null,
                    tint = NavyText,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Compartilhar Minha Conquista",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp,
                    color = NavyText
                )
            }
        }
        }
        Surface(
            shape = RoundedCornerShape(50),
            color = Mint.copy(alpha = 0.3f),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable(onClick = onExplorar),
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.ArrowForward,
                    contentDescription = null,
                    tint = Color(0xFF005137),
                    modifier = Modifier.size(17.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Explorar Próxima Rota Sugerida",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp,
                    color = Color(0xFF005137)
                )
            }
        }
        }
    }
}