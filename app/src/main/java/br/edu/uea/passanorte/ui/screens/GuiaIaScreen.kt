package br.edu.uea.passanorte.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Approval
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.ConfirmationNumber
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.DirectionsBoat
import androidx.compose.material.icons.rounded.DirectionsWalk
import androidx.compose.material.icons.rounded.DoneAll
import androidx.compose.material.icons.rounded.Eco
import androidx.compose.material.icons.rounded.LocalCafe
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material.icons.rounded.Paid
import androidx.compose.material.icons.rounded.Park
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Restaurant
import androidx.compose.material.icons.rounded.Route
import androidx.compose.material.icons.rounded.Savings
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material.icons.rounded.SmartToy
import androidx.compose.material.icons.rounded.Straighten
import androidx.compose.material.icons.rounded.TipsAndUpdates
import androidx.compose.material.icons.rounded.Tune
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
import br.edu.uea.passanorte.ui.theme.GreenDeep
import br.edu.uea.passanorte.ui.theme.GreenPrimary
import br.edu.uea.passanorte.ui.theme.LogoRing
import br.edu.uea.passanorte.ui.theme.Mint
import br.edu.uea.passanorte.ui.theme.NavyText

private val OnlineGreen = Color(0xFF00855D)
private val Orange = Color(0xFFFE932C)
private val TimelineLine = Color(0xFFDCE9FF)
private val ChatDivider = Color(0xFFD3E4FE)
private val TopEscolhaBg = Color(0xFFFFDCC3)
private val TopEscolhaText = Color(0xFF2F1500)
private val GreenTint = GreenPrimary.copy(alpha = 0.1f)
private val TimeText = BodyText.copy(alpha = 0.7f)
private val DividerText = BodyText.copy(alpha = 0.8f)
private val PlaceholderText = BodyText.copy(alpha = 0.6f)

@Composable
fun GuiaIaScreen(navController: NavHostController) {
    var mensagem by rememberSaveable { mutableStateOf("") }

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
            ChatHeader()
            SubHeader()
            ChatBody()
            Spacer(Modifier.height(8.dp))
        }
        InputBar(mensagem) { mensagem = it }
        PassaNorteBottomBar(currentRoute = Routes.GUIA_IA) { route ->
            navController.navigate(route) {
                popUpTo(Routes.HOME) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}

@Composable
private fun ChatHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
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
                lineHeight = 16.sp,
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
                    .offset(x = (-10).dp, y = 10.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(CompassBrown)
            )
        }
        Box(
            modifier = Modifier.size(44.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.profile_marina),
                contentDescription = "Perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .border(2.dp, ChatDivider, CircleShape)
            )
        }
    }
}

@Composable
private fun SubHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box {
                Surface(
                    shape = CircleShape,
                    color = OnlineGreen,
                    shadowElevation = 1.dp,
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            Icons.Rounded.SmartToy,
                            contentDescription = "Guia IA",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Surface(
                    shape = CircleShape,
                    color = Orange,
                    shadowElevation = 1.dp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 2.dp, y = 2.dp)
                        .size(16.dp)
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            Icons.Rounded.AutoAwesome,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(9.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.width(8.dp))
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Guia IA PassaNorte",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        color = NavyText
                    )
                    Spacer(Modifier.width(4.dp))
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = GreenTint
                    ) {
                        Text(
                            text = "v2.4",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp,
                            color = GreenPrimary,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(OnlineGreen)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "Online • Especialista Amazônico",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 16.sp,
                        letterSpacing = 0.5.sp,
                        color = BodyText
                    )
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Rounded.DeleteOutline,
                        contentDescription = "Limpar histórico",
                        tint = NavyText,
                        modifier = Modifier.size(14.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Rounded.Tune,
                        contentDescription = "Opções do assistente",
                        tint = NavyText,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }
        }
        // Banner sutil de contexto do viajante
        Surface(
            shape = RoundedCornerShape(50),
            color = LogoRing
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Rounded.Person,
                    contentDescription = null,
                    tint = BodyText,
                    modifier = Modifier.size(12.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        append("Baseado no seu perfil: ")
                        withStyle(SpanStyle(color = NavyText)) {
                            append("Ecoturismo, História e Gastronomia")
                        }
                    },
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
private fun ChatBody() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        DateDivider("Hoje, 11:28")
        IaBubble(
            texto = "Olá, Marina! 🌿 Sou seu Guia IA de bolso. Como posso montar seu dia perfeito em Manaus hoje? Me conte o que você procura: horários, faixa de orçamento, transporte ou ritmo do passeio!",
            hora = "11:28"
        )
        SugestoesRapidas()
        UserBubble(
            hora = "11:30",
            texto = "Quero um roteiro para hoje à tarde (13h às 18h), com foco histórico e gastronômico, gastando até R$ 60 e que dê para fazer a pé saindo do Largo de São Sebastião!"
        )
        RespostaCompleta()
        FollowUp()
    }
}

@Composable
private fun SugestoesRapidas() {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                Icons.Rounded.TipsAndUpdates,
                contentDescription = null,
                tint = BodyText,
                modifier = Modifier.size(13.dp)
            )
            Text(
                text = "Ideias de partida rápida:",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
                color = BodyText
            )
        }
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            PromptChip("Roteiro cultural até R$ 50", Icons.Rounded.Tune)
            PromptChip("Passeio de 1 dia a pé", Icons.Rounded.DirectionsWalk)
            PromptChip("Onde comer tacacá perto", Icons.Rounded.Place)
            PromptChip("Trilha com sombra à tarde", Icons.Rounded.Park)
        }
    }
}

@Composable
private fun RespostaCompleta() {
    Row(verticalAlignment = Alignment.Top) {
        IaAvatar()
        Spacer(Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(topStart = 2.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp),
                color = FieldBlue,
                shadowElevation = 1.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(3.6.dp)
                ) {
                    Text(
                        text = "Perfeito! Preparei o roteiro",
                        fontSize = 14.sp,
                        lineHeight = 22.75.sp,
                        letterSpacing = 0.25.sp,
                        color = NavyText
                    )
                    Text(
                        text = "“Caminhos da Borracha & Sabores”",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        color = GreenPrimary
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("com 3 paradas a pé (apenas ")
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                append("1.4 km de caminhada total")
                            }
                            append("), ")
                            withStyle(
                                SpanStyle(fontWeight = FontWeight.SemiBold, color = CompassBrown)
                            ) {
                                append("2 carimbos novos")
                            }
                            append(" para o seu passaporte e custo estimado de apenas ")
                            withStyle(
                                SpanStyle(fontWeight = FontWeight.SemiBold, color = GreenPrimary)
                            ) {
                                append("R$ 42")
                            }
                            append("!")
                        },
                        fontSize = 14.sp,
                        lineHeight = 22.75.sp,
                        letterSpacing = 0.25.sp,
                        color = NavyText
                    )
                }
            }
            ItineraryCard()
            Text(
                text = "11:31",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
                color = TimeText,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
private fun FollowUp() {
    Row(verticalAlignment = Alignment.Top) {
        IaAvatar()
        Spacer(Modifier.width(8.dp))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Surface(
                shape = RoundedCornerShape(topStart = 2.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp),
                color = FieldBlue,
                shadowElevation = 1.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Deseja que eu reserve os ingressos da visita das 15h ou prefere incluir uma parada para café regional com bolo de macaxeira antes de ir ao Palácio?",
                    fontSize = 14.sp,
                    lineHeight = 22.75.sp,
                    letterSpacing = 0.25.sp,
                    color = NavyText,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Row(
                modifier = Modifier.padding(top = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Mint,
                    shadowElevation = 1.dp,
                    modifier = Modifier.clickable { }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Rounded.ConfirmationNumber,
                            contentDescription = null,
                            tint = GreenDeep,
                            modifier = Modifier.size(11.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "Reservar ingressos (15h)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp,
                            color = GreenDeep
                        )
                    }
                }
                Surface(
                    shape = RoundedCornerShape(50),
                    color = LogoRing,
                    shadowElevation = 1.dp,
                    modifier = Modifier.clickable { }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Rounded.LocalCafe,
                            contentDescription = null,
                            tint = NavyText,
                            modifier = Modifier.size(11.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "Adicionar Café",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp,
                            color = NavyText
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DateDivider(texto: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .weight(1f)
                .height(1.dp)
                .background(ChatDivider)
        )
        Surface(
            shape = RoundedCornerShape(50),
            color = FieldBlue
        ) {
            Text(
                text = texto,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
                color = DividerText,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )
        }
        Box(
            Modifier
                .weight(1f)
                .height(1.dp)
                .background(ChatDivider)
        )
    }
}

@Composable
private fun IaAvatar() {
    Box(
        modifier = Modifier
            .padding(top = 4.dp)
            .size(32.dp)
            .clip(CircleShape)
            .background(GreenPrimary),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Rounded.SmartToy,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
private fun IaBubble(texto: String, hora: String) {
    Row(verticalAlignment = Alignment.Top) {
        IaAvatar()
        Spacer(Modifier.width(8.dp))
        Column {
            Surface(
                shape = RoundedCornerShape(topStart = 2.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp),
                color = FieldBlue,
                shadowElevation = 1.dp,
                modifier = Modifier.widthIn(max = 290.dp)
            ) {
                Text(
                    text = texto,
                    fontSize = 14.sp,
                    lineHeight = 22.75.sp,
                    letterSpacing = 0.25.sp,
                    color = NavyText,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = hora,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
                color = TimeText,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
private fun UserBubble(hora: String, texto: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(horizontalAlignment = Alignment.End) {
                Surface(
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 2.dp, bottomStart = 16.dp, bottomEnd = 16.dp),
                    color = GreenPrimary,
                    shadowElevation = 1.dp,
                    modifier = Modifier.widthIn(max = 300.dp)
                ) {
                    Text(
                        text = texto,
                        fontSize = 14.sp,
                        lineHeight = 22.75.sp,
                        letterSpacing = 0.25.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 27.dp, top = 16.dp, bottom = 16.dp)
                    )
                }
                Spacer(Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Text(
                        text = hora,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 16.sp,
                        letterSpacing = 0.5.sp,
                        color = TimeText
                    )
                    Icon(
                        Icons.Rounded.DoneAll,
                        contentDescription = null,
                        tint = TimeText,
                        modifier = Modifier.size(11.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(ChatDivider),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.profile_marina),
                    contentDescription = "Marina",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun PromptChip(texto: String, icone: androidx.compose.ui.graphics.vector.ImageVector) {
    Surface(
        shape = RoundedCornerShape(50),
        color = LogoRing,
        shadowElevation = 1.dp,
        modifier = Modifier.clickable { }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icone,
                contentDescription = null,
                tint = NavyText,
                modifier = Modifier.size(12.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = texto,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
                color = NavyText
            )
        }
    }
}

@Composable
private fun ItineraryCard() {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Mini Header do Roteiro
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Rounded.Route,
                    contentDescription = null,
                    tint = GreenPrimary,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Itinerário Sugerido",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 24.sp,
                    letterSpacing = 0.15.sp,
                    color = NavyText,
                    modifier = Modifier.weight(1f)
                )
                Surface(
                    shape = RoundedCornerShape(50),
                    color = TopEscolhaBg
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Rounded.AutoAwesome,
                            contentDescription = null,
                            tint = TopEscolhaText,
                            modifier = Modifier.size(11.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "Top Escolha IA",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp,
                            color = TopEscolhaText
                        )
                    }
                }
            }
            // Timeline Vertical
            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Etapa(
                    numero = "1",
                    hora = "13:30",
                    preco = "Médio: R$ 32",
                    precoColor = CompassBrown,
                    precoWeight = FontWeight.Medium,
                    titulo = "Almoço no Tambaqui de Banda",
                    descricao = "Degustação de caldeirada fresca ou tacacá à moda cabocla no Largo de São Sebastião.",
                    seloIcon = Icons.Rounded.Restaurant,
                    selo = "Selo Gastronômico (+50 pts)",
                    seloWeight = FontWeight.Medium,
                    isLast = false
                )
                Etapa(
                    numero = "2",
                    hora = "15:00",
                    preco = "R$ 20 meia / R$ 40 int.",
                    precoColor = GreenPrimary,
                    precoWeight = FontWeight.Medium,
                    titulo = "Visita Guiada: Teatro Amazonas",
                    descricao = "Monumento histórico icônico da Era da Borracha. Ingressos com embarque prioritário no app.",
                    seloIcon = Icons.Rounded.ConfirmationNumber,
                    selo = "Carimbo de Ouro (+150 pts)",
                    seloWeight = FontWeight.SemiBold,
                    isLast = false
                )
                Etapa(
                    numero = "3",
                    hora = "16:45",
                    preco = "Entrada Franca",
                    precoColor = NavyText,
                    precoWeight = FontWeight.Bold,
                    titulo = "Palácio Rio Negro & Pôr do Sol",
                    descricao = "Arquitetura secular e jardins arborizados ideais para fotos douradas no final de tarde.",
                    seloIcon = Icons.Rounded.Approval,
                    selo = "Carimbo do Passaporte (+100 pts)",
                    seloWeight = FontWeight.Medium,
                    isLast = true
                )
            }
            // Badges de Resumo Técnico
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ResumoBadge(Icons.Rounded.Schedule, "Duração", "4h 30min", LogoRing, BodyText, NavyText, FontWeight.SemiBold, Modifier.weight(1f))
                    ResumoBadge(Icons.Rounded.Straighten, "Distância", "1.4 km a pé", LogoRing, BodyText, NavyText, FontWeight.SemiBold, Modifier.weight(1f))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ResumoBadge(Icons.Rounded.Savings, "Estimativa", "~ R$ 52 total", LogoRing, BodyText, NavyText, FontWeight.SemiBold, Modifier.weight(1f))
                    ResumoBadge(Icons.Rounded.Approval, "Recompensa", "+300 pts", TopEscolhaBg, CompassBrown, TopEscolhaText, FontWeight.Bold, Modifier.weight(1f))
                }
            }
            // CTAs no Card
            Column(
                modifier = Modifier.padding(top = 4.dp),
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
                                Icons.Rounded.Route,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "Adicionar Roteiro a Minhas Rotas",
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
                                tint = GreenPrimary,
                                modifier = Modifier.size(17.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "Ver Rota no Mapa Interativo",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 20.sp,
                                letterSpacing = 0.1.sp,
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
private fun Etapa(
    numero: String,
    hora: String,
    preco: String,
    precoColor: Color,
    precoWeight: FontWeight,
    titulo: String,
    descricao: String,
    seloIcon: androidx.compose.ui.graphics.vector.ImageVector,
    selo: String,
    seloWeight: FontWeight,
    isLast: Boolean
) {
    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Dot com anel branco (sombra 0 0 0 4px white no Figma)
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(GreenPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = numero,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 16.sp,
                        letterSpacing = 0.5.sp,
                        color = Color.White
                    )
                }
            }
            if (!isLast) {
                Box(
                    Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .background(TimelineLine)
                )
            }
        }
        Spacer(Modifier.width(8.dp))
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = FieldBlue,
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = GreenTint
                    ) {
                        Text(
                            text = hora,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 16.sp,
                            letterSpacing = 0.5.sp,
                            color = GreenPrimary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                    Text(
                        text = preco,
                        fontSize = 11.sp,
                        fontWeight = precoWeight,
                        lineHeight = 16.sp,
                        letterSpacing = 0.5.sp,
                        color = precoColor
                    )
                }
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
                Row(
                    modifier = Modifier.padding(top = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        seloIcon,
                        contentDescription = null,
                        tint = CompassBrown,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = selo,
                        fontSize = 11.sp,
                        fontWeight = seloWeight,
                        lineHeight = 16.sp,
                        letterSpacing = 0.5.sp,
                        color = CompassBrown
                    )
                }
            }
        }
    }
}

@Composable
private fun ResumoBadge(
    icone: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    valor: String,
    fundo: Color,
    labelColor: Color,
    valorColor: Color,
    valorWeight: FontWeight,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = fundo,
        modifier = modifier.height(52.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                icone,
                contentDescription = null,
                tint = labelColor,
                modifier = Modifier.size(15.dp)
            )
            Column {
                Text(
                    text = label,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,
                    letterSpacing = 0.5.sp,
                    color = labelColor
                )
                Text(
                    text = valor,
                    fontSize = 12.sp,
                    fontWeight = valorWeight,
                    lineHeight = 16.sp,
                    letterSpacing = 0.5.sp,
                    color = valorColor
                )
            }
        }
    }
}

@Composable
private fun InputBar(texto: String, onTextoChange: (String) -> Unit) {
    Surface(
        color = AppBackground.copy(alpha = 0.95f),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).padding(bottom = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Chips de Modificadores Rápidos de Busca
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ModificadorChip("+ Orçamento max.", Icons.Rounded.Paid)
                ModificadorChip("+ Transporte fluvial", Icons.Rounded.DirectionsBoat)
                ModificadorChip("+ Roteiro sem plástico", Icons.Rounded.Eco)
            }
            // Campo de Entrada
            Surface(
                shape = RoundedCornerShape(50),
                color = LogoRing
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 6.dp, top = 6.dp, bottom = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = texto,
                        onValueChange = onTextoChange,
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            letterSpacing = 0.25.sp,
                            color = NavyText
                        ),
                        modifier = Modifier.weight(1f)
                    ) { innerTextField ->
                        if (texto.isEmpty()) {
                            Text(
                                text = "Pergunte ao Guia IA sobre roteiros, locais...",
                                fontSize = 14.sp,
                                letterSpacing = 0.25.sp,
                                color = PlaceholderText
                            )
                        } else {
                            innerTextField()
                        }
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Rounded.Mic,
                            contentDescription = "Comando por voz",
                            tint = BodyText,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(GreenPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Rounded.Send,
                            contentDescription = "Enviar mensagem",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ModificadorChip(texto: String, icone: androidx.compose.ui.graphics.vector.ImageVector) {
    Surface(
        shape = RoundedCornerShape(50),
        color = DividerBlue,
        modifier = Modifier.clickable { }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                icone,
                contentDescription = null,
                tint = BodyText,
                modifier = Modifier.size(11.dp)
            )
            Text(
                text = texto,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
                color = BodyText
            )
        }
    }
}