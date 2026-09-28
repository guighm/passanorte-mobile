package br.edu.uea.passanorte.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalance
import androidx.compose.material.icons.rounded.Approval
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.ConfirmationNumber
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.HourglassTop
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material.icons.rounded.Restaurant
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material.icons.rounded.Water
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
import br.edu.uea.passanorte.ui.theme.NavyText

private val Orange = Color(0xFFFE932C)
private val OrangeDarkText = Color(0xFF663500)
private val EventGreen = Color(0xFF00855D)
private val NightOverlay = Color(0xFF213145)
private val CardOverlay = Color(0xB30B1C30)
private val ProfileRing = Color(0xFFD3E4FE)

private data class Ponto(
    val imageRes: Int,
    val categoria: String,
    val carimbo: String,
    val carimboBg: Color,
    val carimboText: Color,
    val distancia: String,
    val nota: String,
    val avaliacoes: String,
    val nome: String,
    val status: String,
    val statusBg: Color,
    val statusDot: Color,
    val statusText: Color,
    val descricao: String,
    val rodape: String,
    val rodapeIcon: ImageVector
)

private val pontos = listOf(
    Ponto(
        imageRes = R.drawable.ponto_teatro,
        categoria = "Patrimônio Histórico",
        carimbo = "Vale Carimbo Ouro",
        carimboBg = Orange,
        carimboText = OrangeDarkText,
        distancia = "A 1.2 km de você",
        nota = "4.9",
        avaliacoes = "(1.4k)",
        nome = "Teatro Amazonas",
        status = "Aberto hoje até 17h",
        statusBg = Mint.copy(alpha = 0.3f),
        statusDot = GreenPrimary,
        statusText = GreenPrimary,
        descricao = "Inaugurado em 1896, o principal símbolo da era da borracha reúne peças teatrais, visitas guiadas e",
        rodape = "Carimbo digital instantâneo",
        rodapeIcon = Icons.Rounded.Approval
    ),
    Ponto(
        imageRes = R.drawable.ponto_aguas,
        categoria = "Ecoturismo",
        carimbo = "Carimbo de Rota",
        carimboBg = Color(0xFFCCE5FF),
        carimboText = Color(0xFF001D31),
        distancia = "A 8.5 km",
        nota = "4.9",
        avaliacoes = "(980)",
        nome = "Encontro das Águas",
        status = "Passeios abertos",
        statusBg = Mint.copy(alpha = 0.3f),
        statusDot = GreenPrimary,
        statusText = GreenPrimary,
        descricao = "Fenômeno natural raro onde as águas escuras do Rio Negro e barrentas do Rio Solimões correm…",
        rodape = "Passeio fluvial com guia",
        rodapeIcon = Icons.Rounded.Water
    ),
    Ponto(
        imageRes = R.drawable.ponto_mercado,
        categoria = "Gastronomia & Artesanato",
        carimbo = "Carimbo Prata",
        carimboBg = Color(0xFFFFDCC3),
        carimboText = Color(0xFF2F1500),
        distancia = "A 2.0 km",
        nota = "4.7",
        avaliacoes = "(820)",
        nome = "Mercado Adolpho Lisboa",
        status = "Aberto",
        statusBg = Mint.copy(alpha = 0.3f),
        statusDot = GreenPrimary,
        statusText = GreenPrimary,
        descricao = "Inspirado no Les Halles de Paris, reúne ervas medicinais, peixes amazônicos frescos,…",
        rodape = "Degustação e compras",
        rodapeIcon = Icons.Rounded.Restaurant
    ),
    Ponto(
        imageRes = R.drawable.ponto_bosque,
        categoria = "Fauna & Flora",
        carimbo = "Carimbo Ecológico",
        carimboBg = Mint,
        carimboText = Color(0xFF002114),
        distancia = "A 5.4 km",
        nota = "4.8",
        avaliacoes = "(630)",
        nome = "Bosque da Ciência",
        status = "Fecha às 16h30",
        statusBg = Color(0xFFFFDAD6),
        statusDot = Color(0xFFBA1A1A),
        statusText = Color(0xFF93000A),
        descricao = "Área de preservação do INPA em meio à cidade com trilhas ecológicas, peixes-boi, ariranhas e…",
        rodape = "Trilha auto-guiada",
        rodapeIcon = Icons.Rounded.HourglassTop
    )
)

@Composable
fun HomeScreen(navController: NavHostController) {
    var filtroSelecionado by rememberSaveable { mutableStateOf(0) }
    val filtros = listOf("Todos", "Natureza & Rios", "Cultura & Museus", "Gastronomia Regional", "Abertos agora")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 88.dp, bottom = 120.dp)
        ) {
            TrackerWidget()
            Spacer(Modifier.height(16.dp))
            SearchBar()
            FilterChips(filtros, filtroSelecionado) { filtroSelecionado = it }
            EventCard()
            SectionHeader()
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                pontos.forEach { ponto ->
                    PontoCard(ponto) { navController.navigate(Routes.DETALHE) }
                }
            }
            TipCard()
        }
        Header(modifier = Modifier.align(Alignment.TopCenter))
        Box(Modifier.align(Alignment.BottomCenter)) {
            PassaNorteBottomBar(currentRoute = Routes.HOME) { route ->
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
private fun Header(modifier: Modifier = Modifier) {
    Surface(
        color = AppBackground.copy(alpha = 0.9f),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 16.dp),
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
                    text = "Descoberta",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.5.sp,
                    color = GreenPrimary
                )
            }
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Rounded.NotificationsNone,
                    contentDescription = "Notificações",
                    tint = NavyText,
                    modifier = Modifier.size(20.dp)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 10.dp, top = 10.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(CompassBrown)
                )
            }
            Spacer(Modifier.width(4.dp))
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.profile_marina),
                    contentDescription = "Perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .border(2.dp, ProfileRing, CircleShape)
                )
            }
        }
    }
}

@Composable
private fun TrackerWidget() {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            Modifier
                .padding(16.dp)
                // Warm decorative glow (Figma 1:327), desenhado sem afetar o layout
                .drawBehind {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(Color(0x66FFDCC3), Color(0x00FFDCC3)),
                            center = Offset(size.width + 16.dp.toPx(), size.height + 40.dp.toPx()),
                            radius = 48.dp.toPx()
                        ),
                        radius = 48.dp.toPx(),
                        center = Offset(size.width + 16.dp.toPx(), size.height + 40.dp.toPx())
                    )
                }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Mint),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Rounded.Approval,
                        contentDescription = null,
                        tint = Color(0xFF002114),
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(Modifier.width(8.dp))
                Column(Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "PASSAPORTE ATIVO",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.55.sp,
                            color = CompassBrown
                        )
                        Spacer(Modifier.width(6.dp))
                        Box(
                            Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(GreenPrimary)
                        )
                    }
                    Text(
                        text = "Explorador Ribeirinho",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        color = NavyText
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "4",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            letterSpacing = 0.1.sp,
                            color = GreenPrimary
                        )
                        Text(
                            text = "/12",
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp,
                            color = BodyText
                        )
                    }
                    Text(
                        text = "Carimbos",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.5.sp,
                        color = BodyText
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(50))
                        .background(DividerBlue)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(
                                Brush.horizontalGradient(
                                    listOf(GreenPrimary, Orange)
                                )
                            )
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Próxima recompensa: Selo de Ouro",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.5.sp,
                        color = BodyText
                    )
                    Text(
                        text = "Faltam 8 selos",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.5.sp,
                        color = CompassBrown
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchBar() {
    Surface(
        shape = RoundedCornerShape(50),
        color = LogoRing,
        shadowElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(48.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp, end = 8.dp)
        ) {
            Icon(
                Icons.Rounded.Search,
                contentDescription = null,
                tint = BodyText,
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = "Buscar pontos turísticos, rotas, eventos...",
                fontSize = 14.sp,
                letterSpacing = 0.25.sp,
                color = BodyText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .size(32.dp)
                    .clickable { },
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Rounded.Tune,
                        contentDescription = "Filtrar",
                        tint = NavyText,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun FilterChips(
    filtros: List<String>,
    selecionado: Int,
    onSelect: (Int) -> Unit
) {
    val chipIcons = listOf(
        Icons.Rounded.ConfirmationNumber,
        Icons.Rounded.Water,
        Icons.Rounded.AccountBalance,
        Icons.Rounded.Restaurant,
        Icons.Rounded.Schedule
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        filtros.forEachIndexed { index, filtro ->
            val ativo = index == selecionado
            Surface(
                shape = RoundedCornerShape(50),
                color = if (ativo) GreenPrimary else FieldBlue,
                modifier = Modifier.clickable { onSelect(index) }
            ) {
                Row(
                    modifier = Modifier
                        .height(32.dp)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        chipIcons[index],
                        contentDescription = null,
                        tint = if (ativo) Color.White else BodyText,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = filtro,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.5.sp,
                        color = if (ativo) Color.White else BodyText
                    )
                }
            }
        }
    }
}

@Composable
private fun EventCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp)
            .height(240.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(NightOverlay)
            .clickable { }
    ) {
        // Image blended over the navy base (Figma: mix-blend luminosity, opacity 40%)
        Image(
            painter = painterResource(R.drawable.evento_festival),
            contentDescription = "Festival do Tacacá",
            contentScale = ContentScale.Crop,
            alpha = 0.4f,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0f to Color.Transparent,
                        0.5f to NightOverlay.copy(alpha = 0.85f),
                        1f to NightOverlay
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = CompassBrown,
                    shadowElevation = 1.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(AppBackground)
                        )
                        Text(
                            text = "EVENTO HOJE • 18H",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.55.sp,
                            color = Color.White
                        )
                    }
                }
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color(0x66663500)
                ) {
                    Text(
                        text = "Largo de São Sebastião",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.5.sp,
                        color = Color(0xFFFFDCC3),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }
            Column {
                Text(
                    text = "Festival do Tacacá da Amazônia",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp,
                    letterSpacing = (-0.55).sp,
                    color = AppBackground
                )
                Text(
                    text = "Música ao vivo, danças folclóricas e barracas…",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.25.sp,
                    color = Color(0xFFD3E4FE)
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            Icons.Rounded.Approval,
                            contentDescription = null,
                            tint = Mint,
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = "+1 Carimbo Edição Especial",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.5.sp,
                            color = Mint
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = EventGreen,
                        shadowElevation = 1.dp,
                        modifier = Modifier.clickable { }
                    ) {
                        Row(
                            modifier = Modifier
                                .height(36.dp)
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "Ver Evento",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.5.sp,
                                color = Color.White
                            )
                            Icon(
                                Icons.Rounded.ArrowForward,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(11.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = "Explorar Pontos Turísticos",
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.Bold,
                color = NavyText
            )
            Text(
                text = "Ordenados por proximidade e carimbos disponíveis",
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp,
                color = BodyText
            )
        }
        Spacer(Modifier.width(12.dp))
        Surface(
            shape = CircleShape,
            color = LogoRing,
            modifier = Modifier
                .size(36.dp)
                .clickable { },
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(
                    Icons.Rounded.Tune,
                    contentDescription = null,
                    tint = NavyText,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
private fun PontoCard(ponto: Ponto, onExplorar: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onExplorar() }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(192.dp)
            ) {
                Image(
                    painter = painterResource(ponto.imageRes),
                    contentDescription = ponto.nome,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                0f to Color.Transparent,
                                0.5f to Color.Transparent,
                                1f to CardOverlay
                            )
                        )
                )
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = Color.White.copy(alpha = 0.9f)
                    ) {
                        Text(
                            text = ponto.categoria,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp,
                            color = NavyText,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = ponto.carimboBg,
                        shadowElevation = 1.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.Approval,
                                contentDescription = null,
                                tint = ponto.carimboText,
                                modifier = Modifier.size(11.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = ponto.carimbo,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 16.sp,
                                letterSpacing = 0.5.sp,
                                color = ponto.carimboText
                            )
                        }
                    }
                }
                Surface(
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.9f),
                    shadowElevation = 1.dp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .size(40.dp)
                        .clickable { }
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            Icons.Rounded.FavoriteBorder,
                            contentDescription = "Favoritar",
                            tint = NavyText,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            Icons.Rounded.Explore,
                            contentDescription = null,
                            tint = AppBackground,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = ponto.distancia,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.5.sp,
                            color = AppBackground
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = Color.White.copy(alpha = 0.9f)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                Icons.Rounded.Star,
                                contentDescription = null,
                                tint = Color(0xFFFFC846),
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = ponto.nota,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp,
                                color = NavyText
                            )
                            Text(
                                text = ponto.avaliacoes,
                                fontSize = 11.sp,
                                letterSpacing = 0.5.sp,
                                color = BodyText
                            )
                        }
                    }
                }
            }
            Column(Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = ponto.statusBg
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(
                                Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(ponto.statusDot)
                            )
                            Text(
                                text = ponto.status,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 16.sp,
                                letterSpacing = 0.5.sp,
                                color = ponto.statusText
                            )
                        }
                    }
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    text = ponto.nome,
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyText
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = ponto.descricao,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.25.sp,
                    color = BodyText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(28.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            ponto.rodapeIcon,
                            contentDescription = null,
                            tint = BodyText,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = ponto.rodape,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.5.sp,
                            color = BodyText
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = GreenPrimary,
                        shadowElevation = 1.dp,
                        modifier = Modifier
                            .height(40.dp)
                            .clickable { onExplorar() }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Explorar Ponto",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.5.sp,
                                color = Color.White
                            )
                            Icon(
                                Icons.Rounded.ArrowForward,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TipCard() {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = LogoRing,
        shadowElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 1.dp,
                modifier = Modifier.size(56.dp),
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Rounded.Lightbulb,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
            Column {
                Text(
                    text = "DICA DE ROTA",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.275.sp,
                    color = GreenPrimary
                )
                Text(
                    text = "Visite 2 pontos hoje e ganhe\nbônus!",
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.15.sp,
                    color = NavyText
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "Desbloqueie o badge \"Explorador da Capital\" e ganhe descontos em passeios fluviais.",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.25.sp,
                    color = BodyText
                )
            }
        }
    }
}