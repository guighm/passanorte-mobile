package br.edu.uea.passanorte.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Approval
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Public
import androidx.compose.material.icons.rounded.Redeem
import androidx.compose.material.icons.rounded.Route
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material.icons.rounded.Visibility
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

private val OnlineGreen = Color(0xFF00855D)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PerfilScreen(navController: NavHostController) {
    var notificacoes by rememberSaveable { mutableStateOf(true) }
    var modoOffline by rememberSaveable { mutableStateOf(true) }
    var exibirSelos by rememberSaveable { mutableStateOf(false) }

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
            ProfileCard()
            ConquistasSection()
            DadosCadastraisCard()
            InteressesCard()
            PreferenciasCard(
                notificacoes = notificacoes,
                onNotificacoesChange = { notificacoes = it },
                modoOffline = modoOffline,
                onModoOfflineChange = { modoOffline = it },
                exibirSelos = exibirSelos,
                onExibirSelosChange = { exibirSelos = it }
            )
            Spacer(Modifier.height(32.dp))
            Surface(
                shape = RoundedCornerShape(50),
                color = GreenPrimary,
                shadowElevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(48.dp)
                    .clickable { },
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Rounded.Save,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Salvar Perfil e Preferências",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 20.sp,
                        letterSpacing = 0.1.sp,
                        color = Color.White
                    )
                }
            }
            }
            Spacer(Modifier.height(24.dp))
        }
        PassaNorteBottomBar(currentRoute = Routes.PERFIL) { route ->
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
private fun ProfileCard() {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box {
            Box(
                Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 64.dp, y = (-64).dp)
                    .size(144.dp)
                    .clip(CircleShape)
                    .background(GreenPrimary.copy(alpha = 0.1f))
            )
            Box(
                Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = (-40).dp, y = 40.dp)
                    .size(112.dp)
                    .clip(CircleShape)
                    .background(Color(0x26FE932C))
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(listOf(GreenPrimary, Color(0xFFFE932C)))
                            )
                            .padding(4.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.profile_marina),
                            contentDescription = "Foto de Marina Duarte",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                        )
                    }
                    Surface(
                        shape = CircleShape,
                        color = GreenPrimary,
                        shadowElevation = 2.dp,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(32.dp)
                            .clickable { },
                    ) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            Icons.Rounded.Edit,
                            contentDescription = "Alterar foto do perfil",
                            tint = Color.White,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                    }
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Marina Duarte",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 32.sp,
                    color = NavyText
                )
                Spacer(Modifier.height(4.dp))
                Surface(
                    shape = RoundedCornerShape(50),
                    color = DividerBlue
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Rounded.Shield,
                            contentDescription = null,
                            tint = GreenPrimary,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "Exploradora Curiosa • Nível 3",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp,
                            color = GreenPrimary
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = FieldBlue
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(GreenPrimary.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Rounded.Badge,
                                contentDescription = null,
                                tint = GreenPrimary,
                                modifier = Modifier.size(17.dp)
                            )
                        }
                        Spacer(Modifier.width(8.dp))
                        Column(Modifier.weight(1f)) {
                            Text(
                                text = "PASSAPORTE DIGITAL",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 16.sp,
                                letterSpacing = 0.55.sp,
                                color = BodyText
                            )
                            Text(
                                text = "#BR-92841-AM",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 24.sp,
                                letterSpacing = 0.15.sp,
                                color = NavyText
                            )
                        }
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = OnlineGreen
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Rounded.Check,
                                    contentDescription = null,
                                    tint = Color(0xFFF5FFF7),
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text(
                                    text = "Ativo",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    lineHeight = 16.sp,
                                    letterSpacing = 0.5.sp,
                                    color = Color(0xFFF5FFF7)
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
private fun ConquistasSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Conquistas na Região",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp,
                color = NavyText,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Passaporte Norte",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
                color = GreenPrimary
            )
        }
        Spacer(Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ConquistaCard(
                icone = { Icon(Icons.Rounded.Approval, contentDescription = null, tint = CompassBrown, modifier = Modifier.size(19.dp)) },
                iconeBg = CompassBrown.copy(alpha = 0.1f),
                valor = "08",
                label = "Carimbos\nColetados",
                modifier = Modifier.weight(1f)
            )
            ConquistaCard(
                icone = { Icon(Icons.Rounded.Route, contentDescription = null, tint = GreenPrimary, modifier = Modifier.size(20.dp)) },
                iconeBg = GreenPrimary.copy(alpha = 0.1f),
                valor = "03",
                label = "Rotas Concluídas",
                modifier = Modifier.weight(1f)
            )
            ConquistaCard(
                icone = { Icon(Icons.Rounded.Redeem, contentDescription = null, tint = Color(0xFF006194), modifier = Modifier.size(20.dp)) },
                iconeBg = Color(0x1A006194),
                valor = "01",
                label = "Brinde\nResgatado",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ConquistaCard(
    icone: @Composable () -> Unit,
    iconeBg: Color,
    valor: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = modifier.height(120.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconeBg),
                contentAlignment = Alignment.Center
            ) {
                icone()
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = valor,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp,
                color = NavyText
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = label,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 13.75.sp,
                letterSpacing = 0.5.sp,
                color = BodyText,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
private fun DadosCadastraisCard() {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Rounded.Badge,
                        contentDescription = null,
                        tint = BodyText,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Dados Cadastrais",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 28.sp,
                        color = NavyText
                    )
                }
                Text(
                    text = "Sincronizado",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,
                    letterSpacing = 0.5.sp,
                    color = BodyText
                )
            }
            Spacer(Modifier.height(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                LabeledIconField("Nome Completo", "Marina Duarte", Icons.Rounded.Person)
                LabeledIconField("E-mail", "marina.duarte@email.com", Icons.Rounded.Email)
                LabeledIconField("Telefone WhatsApp", "(92) 98452-1100", Icons.Rounded.Phone)
                LabeledIconField(
                    "CPF / Documento",
                    "042.***.***-89",
                    Icons.Rounded.Badge,
                    bloqueado = true
                )
                LabeledIconField("País de Residência", "Brasil 🇧🇷", Icons.Rounded.Public)
            }
        }
    }
}

@Composable
private fun LabeledIconField(
    label: String,
    valor: String,
    icone: androidx.compose.ui.graphics.vector.ImageVector,
    bloqueado: Boolean = false
) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            color = BodyText,
            modifier = Modifier.padding(start = 4.dp, bottom = 5.dp)
        )
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = if (bloqueado) LogoRing else FieldBlue
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    icone,
                    contentDescription = null,
                    tint = if (bloqueado) BodyText else MutedText,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = valor,
                    fontSize = 14.sp,
                    letterSpacing = 0.25.sp,
                    color = if (bloqueado) BodyText else NavyText
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun InteressesCard() {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.Tune,
                    contentDescription = null,
                    tint = BodyText,
                    modifier = Modifier.size(19.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Meus Interesses Turísticos",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp,
                    color = NavyText
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Personalize sugestões da Guia IA e notificações de carimbos próximos.",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
                color = BodyText
            )
            Spacer(Modifier.height(16.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    "🌿 Ecoturismo & Floresta",
                    "🏛️ Patrimônio Histórico",
                    "🍲 Culinária Amazônica",
                    "🛶 Passeios Fluviais"
                ).forEach { interesse ->
                    InteresseChip(texto = interesse, selecionado = true)
                }
                listOf(
                    "🎭 Festivais Folclóricos",
                    "🏺 Artesanato Indígena",
                    "🌙 Vida Noturna",
                    "📸 Fotografia de Natureza"
                ).forEach { interesse ->
                    InteresseChip(texto = interesse, selecionado = false)
                }
            }
        }
    }
}

@Composable
private fun InteresseChip(texto: String, selecionado: Boolean) {
    Surface(
        shape = RoundedCornerShape(50),
        color = if (selecionado) GreenPrimary else LogoRing,
        modifier = Modifier
            .height(36.dp)
            .clickable { }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (selecionado) {
                Icon(
                    Icons.Rounded.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(11.dp)
                )
            } else {
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = null,
                    tint = NavyText,
                    modifier = Modifier.size(9.dp)
                )
            }
            Spacer(Modifier.width(6.dp))
            Text(
                text = texto,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
                color = if (selecionado) Color.White else NavyText
            )
        }
    }
}

@Composable
private fun PreferenciasCard(
    notificacoes: Boolean,
    onNotificacoesChange: (Boolean) -> Unit,
    modoOffline: Boolean,
    onModoOfflineChange: (Boolean) -> Unit,
    exibirSelos: Boolean,
    onExibirSelosChange: (Boolean) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.Tune,
                    contentDescription = null,
                    tint = BodyText,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Preferências do Aplicativo",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp,
                    color = NavyText
                )
            }
            Spacer(Modifier.height(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                PreferenciaRow(
                    icone = { Icon(Icons.Rounded.NotificationsNone, contentDescription = null, tint = GreenPrimary, modifier = Modifier.size(17.dp)) },
                    iconeBg = GreenPrimary.copy(alpha = 0.1f),
                    titulo = "Notificações em Tempo Real",
                    descricao = "Avisar sobre selos e eventos próximos",
                    checked = notificacoes,
                    onChange = onNotificacoesChange
                )
                PreferenciaRow(
                    icone = { Icon(Icons.Rounded.Map, contentDescription = null, tint = GreenPrimary, modifier = Modifier.size(17.dp)) },
                    iconeBg = GreenPrimary.copy(alpha = 0.1f),
                    titulo = "Modo Trilha Offline",
                    descricao = "Baixar mapas para áreas sem sinal fluvial",
                    checked = modoOffline,
                    onChange = onModoOfflineChange
                )
                PreferenciaRow(
                    icone = { Icon(Icons.Rounded.Visibility, contentDescription = null, tint = Color(0xFFB36A0D), modifier = Modifier.size(17.dp)) },
                    iconeBg = Color(0x33FE932C),
                    titulo = "Exibir Selos no Perfil Público",
                    descricao = "Compartilhar progresso com outros exploradores",
                    checked = exibirSelos,
                    onChange = onExibirSelosChange
                )
            }
        }
    }
}

@Composable
private fun PreferenciaRow(
    icone: @Composable () -> Unit,
    iconeBg: Color,
    titulo: String,
    descricao: String,
    checked: Boolean,
    onChange: (Boolean) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = FieldBlue,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onChange(!checked) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(iconeBg),
                contentAlignment = Alignment.Center
            ) {
                icone()
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 24.sp,
                    letterSpacing = 0.15.sp,
                    color = NavyText
                )
                Text(
                    text = descricao,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.25.sp,
                    color = BodyText
                )
            }
            Spacer(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(19.dp)
                    .clip(RoundedCornerShape(2.5.dp))
                    .background(if (checked) GreenPrimary else Color.White)
                    .border(
                        width = if (checked) 0.dp else 1.dp,
                        color = if (checked) Color.Transparent else Color(0xFF767676),
                        shape = RoundedCornerShape(2.5.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (checked) {
                    Icon(
                        Icons.Rounded.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(13.dp)
                    )
                }
            }
        }
    }
}