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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Accessibility
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.Approval
import androidx.compose.material.icons.rounded.ConfirmationNumber
import androidx.compose.material.icons.rounded.Event
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.GpsFixed
import androidx.compose.material.icons.rounded.HistoryEdu
import androidx.compose.material.icons.rounded.Navigation
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import br.edu.uea.passanorte.ui.theme.MutedText
import br.edu.uea.passanorte.ui.theme.NavyText

private val Orange = Color(0xFFFE932C)
private val OrangeDarkText = Color(0xFF663500)
private val BlueLink = Color(0xFF006194)
private val MintSoft = Color(0x4D68DBA9)
private val MintSoft30 = Color(0x4D68DBA9)

@Composable
fun DetalhePontoScreen(navController: NavHostController) {
    var favoritado by rememberSaveable { mutableStateOf(false) }

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
            HeroSection(
                favoritado = favoritado,
                onToggleFavorito = { favoritado = !favoritado },
                onBack = { navController.popBackStack() }
            )
            TitleBlock()
            CheckinCard { navController.navigate(Routes.CHECKIN) }
            HorariosCard()
            ComoChegarSection()
            HistoriaSection()
            RotaOficialCard()
            EventosSection()
            GaleriaSection()
            Spacer(Modifier.height(16.dp))
        }
        PassaNorteBottomBar(currentRoute = Routes.DETALHE) { route ->
            navController.navigate(route) {
                popUpTo(Routes.HOME) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}

@Composable
private fun HeroSection(favoritado: Boolean, onToggleFavorito: () -> Unit, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.detalhe_hero),
            contentDescription = "Teatro Amazonas",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0f to Color(0xFF213145).copy(alpha = 0.55f),
                        0.5f to Color.Transparent,
                        1f to Color(0xB30B1C30)
                    )
                )
        )
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = Color(0x59FFFFFF),
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onBack() },
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(
                    shape = CircleShape,
                    color = Color(0x59FFFFFF),
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onToggleFavorito() },
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        if (favoritado) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                        contentDescription = "Favoritar",
                        tint = if (favoritado) Color(0xFFE5484D) else Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
                }
                Surface(
                    shape = CircleShape,
                    color = Color(0x59FFFFFF),
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { },
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Rounded.Share,
                        contentDescription = "Compartilhar",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
                }
            }
        }
        // Badges bottom
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color(0xE6F8F9FF)
                ) {
                    Text(
                        text = "Patrimônio Histórico & Cultural",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 16.sp,
                        color = NavyText,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Orange,
                    shadowElevation = 1.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Rounded.Approval,
                            contentDescription = null,
                            tint = OrangeDarkText,
                            modifier = Modifier.size(11.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "Carimbo de Ouro • +150 Pontos",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 16.sp,
                            color = OrangeDarkText
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color(0xB30B1C30)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "4.9",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Icon(
                            Icons.Rounded.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFC846),
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "(1.420)",
                            fontSize = 11.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TitleBlock() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = "Teatro Amazonas",
            fontSize = 22.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.55).sp,
            color = NavyText
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = "Largo de São Sebastião, Centro • Manaus, AM",
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            color = BodyText
        )
    }
}

@Composable
private fun CheckinCard(onCheckin: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(MintSoft30),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Rounded.GpsFixed,
                            contentDescription = null,
                            tint = GreenPrimary,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Selo Presencial",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 24.sp,
                            color = NavyText
                        )
                        Text(
                            text = "Aproxime-se a menos de 100m",
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp,
                            color = BodyText
                        )
                    }
                }
                Surface(
                    shape = RoundedCornerShape(50),
                    color = DividerBlue
                ) {
                    Text(
                        text = "Disponível",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 16.sp,
                        color = GreenPrimary,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }
            Surface(
                shape = RoundedCornerShape(50),
                color = GreenPrimary,
                shadowElevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clickable { onCheckin() },
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Rounded.GpsFixed,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Marcar Presença com GPS",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 20.sp,
                        color = Color.White
                    )
                }
            }
            }
        }
    }
}

@Composable
private fun HorariosCard() {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(12.dp)) {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(GreenPrimary.copy(alpha = 0.25f))
                        )
                        Box(
                            Modifier
                                .size(8.dp)
                                .align(Alignment.Center)
                                .clip(CircleShape)
                                .background(GreenPrimary)
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Aberto Agora",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        color = GreenPrimary
                    )
                }
                Text(
                    text = "Terça a Sábado: 09h às 17h",
                    fontSize = 12.sp,
                    letterSpacing = 0.5.sp,
                    color = BodyText
                )
            }
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = FieldBlue
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Rounded.Schedule,
                            contentDescription = null,
                            tint = NavyText,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "Próxima Visita Guiada",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 20.sp,
                            letterSpacing = 0.5.sp,
                            color = NavyText
                        )
                    }
                    Text(
                        text = "Hoje às 14:30",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 20.sp,
                        letterSpacing = 0.5.sp,
                        color = BlueLink
                    )
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FeatureChip(icon = { Icon(Icons.Rounded.Accessibility, null, tint = BodyText, modifier = Modifier.size(16.dp)) }, label = "Acessibilidade\nTotal", modifier = Modifier.weight(1f))
                FeatureChip(icon = { Icon(Icons.Rounded.Air, null, tint = BodyText, modifier = Modifier.size(16.dp)) }, label = "Climatizado", modifier = Modifier.weight(1f))
                FeatureChip(icon = { Icon(Icons.Rounded.HistoryEdu, null, tint = BodyText, modifier = Modifier.size(16.dp)) }, label = "Sem Flash", modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun FeatureChip(icon: @Composable () -> Unit, label: String, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = FieldBlue,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            icon()
            Spacer(Modifier.height(3.dp))
            Text(
                text = label,
                fontSize = 11.sp,
                lineHeight = 13.75.sp,
                letterSpacing = 0.5.sp,
                color = BodyText,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ComoChegarSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.Navigation,
                    contentDescription = null,
                    tint = NavyText,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "Como Chegar (Atalhos Rápidos)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp,
                    letterSpacing = 0.15.sp,
                    color = NavyText
                )
            }
            Text(
                text = "Ao vivo",
                fontSize = 11.sp,
                letterSpacing = 0.5.sp,
                color = BodyText
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            TransportCard(icon = { Icon(Icons.Rounded.Navigation, null, tint = BlueLink, modifier = Modifier.size(17.dp)) }, nome = "Maps", detalhe = "1,2 km • 5m", modifier = Modifier.weight(1f))
            TransportCard(icon = { Text("🚗", fontSize = 17.sp) }, nome = "Uber", detalhe = "~R$ 14 (4m)", modifier = Modifier.weight(1f))
            TransportCard(icon = { Text("🚕", fontSize = 17.sp) }, nome = "99 Pop", detalhe = "~R$ 12 (Perto)", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun TransportCard(
    icon: @Composable () -> Unit,
    nome: String,
    detalhe: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = DividerBlue,
                modifier = Modifier.size(40.dp),
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                icon()
            }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = nome,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 16.sp,
                color = NavyText
            )
            Text(
                text = detalhe,
                fontSize = 11.sp,
                lineHeight = 16.sp,
                color = BodyText
            )
        }
    }
}

@Composable
private fun HistoriaSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Rounded.HistoryEdu,
                contentDescription = null,
                tint = NavyText,
                modifier = Modifier.size(17.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = "História & Arquitetura",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp,
                color = NavyText
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Inaugurado em 1896 durante o fausto do Ciclo da Borracha, o Teatro Amazonas é o símbolo máximo da Belle Époque amazônica. Com cúpula…",
            fontSize = 12.sp,
            lineHeight = 16.sp,
            color = BodyText
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Ler mais",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.5.sp,
            color = GreenPrimary,
            modifier = Modifier.clickable { }
        )
    }
}

@Composable
private fun RotaOficialCard() {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = FieldBlue,
        shadowElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Rounded.Approval,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(17.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "PARTE DA ROTA OFICIAL",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 16.sp,
                        letterSpacing = 0.275.sp,
                        color = GreenPrimary
                    )
                }
                Text(
                    text = "Ponto 1 de 4",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 16.sp,
                    color = NavyText
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Coração Histórico de Manaus",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp,
                color = NavyText
            )
            Text(
                text = "Visite o Teatro, Palácio Rio Negro, Mercado Municipal e Porto Flutuante para concluir a insígnia.",
                fontSize = 12.sp,
                lineHeight = 16.sp,
                color = BodyText
            )
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFD3E4FE))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .height(8.dp)
                        .clip(RoundedCornerShape(50))
                        .background(GreenPrimary)
                )
            }
        }
    }
}

@Composable
private fun EventosSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.Event,
                    contentDescription = null,
                    tint = NavyText,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "Programação & Eventos",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp,
                    letterSpacing = 0.15.sp,
                    color = NavyText
                )
            }
            Text(
                text = "Ver todos",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp,
                color = GreenPrimary,
                modifier = Modifier.clickable { }
            )
        }
        EventoDetalheCard(
            categoria = "MÚSICA CLÁSSICA",
            categoriaColor = CompassBrown,
            titulo = "Ópera Carmen - Amazonas Filarmônica",
            quando = "Amanhã, 20h00 • Classificação 12 anos",
            preco = "R$ 40,00",
            precoBg = MintSoft,
            precoColor = Color(0xFF002114),
            rodape = "Meia-entrada: R$ 20,00",
            botao = "Garantir Ingresso",
            botaoBg = BlueLink,
            botaoTextColor = Color.White,
            botaoIcon = { Icon(Icons.Rounded.ConfirmationNumber, null, tint = Color.White, modifier = Modifier.size(12.dp)) }
        )
        EventoDetalheCard(
            categoria = "EXPERIÊNCIA NOTURNA",
            categoriaColor = GreenPrimary,
            titulo = "Visita Noturna Guiada das Lendas",
            quando = "Sexta-feira, 19h30 • Vagas Limitadas",
            preco = "Gratuito",
            precoBg = Color(0xFFFFDCC3),
            precoColor = Color(0xFF2F1500),
            rodape = "Lotação máx: 45 pessoas",
            botao = "Reservar Vaga",
            botaoBg = DividerBlue,
            botaoTextColor = NavyText,
            botaoIcon = { Icon(Icons.Rounded.Schedule, null, tint = NavyText, modifier = Modifier.size(12.dp)) }
        )
    }
}

@Composable
private fun EventoDetalheCard(
    categoria: String,
    categoriaColor: Color,
    titulo: String,
    quando: String,
    preco: String,
    precoBg: Color,
    precoColor: Color,
    rodape: String,
    botao: String,
    botaoBg: Color,
    botaoTextColor: Color,
    botaoIcon: @Composable () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = categoria,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 16.sp,
                        letterSpacing = 0.55.sp,
                        color = categoriaColor
                    )
                    Text(
                        text = titulo,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp,
                        color = NavyText
                    )
                    Spacer(Modifier.height(2.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Rounded.Schedule,
                            contentDescription = null,
                            tint = BodyText,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = quando,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            color = BodyText
                        )
                    }
                }
                Surface(shape = RoundedCornerShape(50), color = precoBg) {
                    Text(
                        text = preco,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 16.sp,
                        color = precoColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = rodape,
                    fontSize = 11.sp,
                    letterSpacing = 0.5.sp,
                    color = BodyText
                )
                Surface(
                    shape = RoundedCornerShape(50),
                    color = botaoBg,
                    shadowElevation = 1.dp,
                    modifier = Modifier.clickable { }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .height(36.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        botaoIcon()
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = botao,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 16.sp,
                            color = botaoTextColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GaleriaSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Galeria de Registros",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
            color = NavyText
        )
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            GaleriaItem(R.drawable.galeria_salao, "Salão Nobre", Modifier.weight(1f))
            GaleriaItem(R.drawable.galeria_cupula, "Cúpula Vitrificada", Modifier.weight(1f))
        }
    }
}

@Composable
private fun GaleriaItem(imageRes: Int, label: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(120.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = label,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0.5f to Color.Transparent,
                        1f to Color(0xB30B1C30)
                    )
                )
        )
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)
        )
    }
}