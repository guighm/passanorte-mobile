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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Approval
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.HelpOutline
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Public
import androidx.compose.material.icons.rounded.Sell
import androidx.compose.material.icons.rounded.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.edu.uea.passanorte.R
import br.edu.uea.passanorte.Routes
import br.edu.uea.passanorte.ui.components.LabeledPillTextField
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

private val Orange = Color(0xFFFE932C)
private val OrangeDarkText = Color(0xFF663500)
private val ProgressTrack = Color(0xFFD3E4FE)

@Composable
fun CadastroScreen(navController: NavHostController) {
    var nome by rememberSaveable { mutableStateOf("Marina Duarte") }
    var email by rememberSaveable { mutableStateOf("marina.duarte@email.com") }
    var telefone by rememberSaveable { mutableStateOf("(92) 98452-1100") }
    var cpf by rememberSaveable { mutableStateOf("702.439.812-05") }
    var termosAceitos by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 40.dp)
    ) {
        // Top action bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = FieldBlue,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.popBackStack() },
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Voltar",
                    tint = NavyText,
                    modifier = Modifier.size(14.dp)
                )
            }
            }
            Surface(
                shape = RoundedCornerShape(50),
                color = LogoRing
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.WorkspacePremium,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.size(4.dp))
                    Text(
                        text = "Passaporte Oficial",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.5.sp,
                        color = GreenPrimary
                    )
                }
            }
            Surface(
                shape = CircleShape,
                color = FieldBlue,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { },
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(
                    Icons.Rounded.HelpOutline,
                    contentDescription = "Ajuda",
                    tint = NavyText,
                    modifier = Modifier.size(17.dp)
                )
            }
            }
        }

        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Box(
                    modifier = Modifier
                        .size(112.dp)
                        .clip(CircleShape)
                        .background(Color(0x3368DBA9))
                        .blur(12.dp)
                )
                Box(
                    modifier = Modifier
                        .offset(y = 8.dp)
                        .size(96.dp)
                        .shadow(3.dp, CircleShape)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.cadastro_selo),
                        contentDescription = "Selo oficial",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                            .clip(CircleShape)
                    )
                }
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Orange,
                    shadowElevation = 1.dp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 4.dp, y = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Rounded.Sell,
                            contentDescription = null,
                            tint = OrangeDarkText,
                            modifier = Modifier.size(10.dp)
                        )
                        Spacer(Modifier.size(2.dp))
                        Text(
                            text = "AMAZÔNIA",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-0.25).sp,
                            color = OrangeDarkText
                        )
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Crie seu Passaporte Digital",
                fontSize = 28.sp,
                lineHeight = 36.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (-0.7).sp,
                color = NavyText,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Explore o Norte, colecione carimbos e desbloqueie recompensas culturais.",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
                color = BodyText,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            Spacer(Modifier.height(16.dp))
            // Progress indicator
            Surface(
                shape = RoundedCornerShape(32.dp),
                color = FieldBlue
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
                            Icons.Rounded.Approval,
                            contentDescription = null,
                            tint = GreenDeepCompat,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(Modifier.size(8.dp))
                    Column(Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Edição Boas-Vindas",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 0.5.sp,
                                color = GreenPrimary
                            )
                            Text(
                                text = "Bônus: 1º Carimbo Grátis",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.5.sp,
                                color = CompassBrown
                            )
                        }
                        Spacer(Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(50))
                                .background(ProgressTrack)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.4f)
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(GreenPrimary)
                            )
                        }
                    }
                }
            }
        }

        // Form
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            LabeledIconField(
                icon = { Icon(Icons.Rounded.Person, null, tint = MutedText, modifier = Modifier.size(20.dp)) },
                label = "Nome Completo",
                value = nome,
                onValueChange = { nome = it }
            )
            LabeledIconField(
                icon = { Icon(Icons.Rounded.MailOutline, null, tint = MutedText, modifier = Modifier.size(20.dp)) },
                label = "E-mail Pessoal",
                value = email,
                onValueChange = { email = it },
                trailing = {
                    Icon(
                        Icons.Rounded.Check,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(15.dp)
                    )
                }
            )
            // Phone with DDI
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 1.dp,
                modifier = Modifier.height(56.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.Call,
                        contentDescription = null,
                        tint = MutedText,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.size(12.dp))
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = FieldBlue
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("🇧🇷", fontSize = 14.sp)
                            Spacer(Modifier.size(4.dp))
                            Text(
                                text = "+55",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.5.sp,
                                color = NavyText
                            )
                            Icon(
                                Icons.Rounded.ExpandMore,
                                contentDescription = null,
                                tint = NavyText,
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }
                    Spacer(Modifier.size(8.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = "WhatsApp / Celular",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.5.sp,
                            color = BodyText
                        )
                        Text(
                            text = telefone,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            letterSpacing = 0.5.sp,
                            color = NavyText
                        )
                    }
                }
            }
            // Document switcher
            Surface(
                shape = RoundedCornerShape(32.dp),
                color = FieldBlue
            ) {
                Column(Modifier.padding(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Rounded.CreditCard,
                                contentDescription = null,
                                tint = BodyText,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(Modifier.size(4.dp))
                            Text(
                                text = "Tipo de Documentação",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.5.sp,
                                color = BodyText
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Rounded.Lock,
                                contentDescription = null,
                                tint = GreenPrimary,
                                modifier = Modifier.size(10.dp)
                            )
                            Spacer(Modifier.size(2.dp))
                            Text(
                                text = "Seguro & Criptografado",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.5.sp,
                                color = GreenPrimary
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    // Segmented toggle
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = ProgressTrack
                    ) {
                        Row(Modifier.padding(4.dp)) {
                            Surface(
                                shape = RoundedCornerShape(50),
                                color = Color.White,
                                shadowElevation = 1.dp
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        Icons.Rounded.Badge,
                                        contentDescription = null,
                                        tint = GreenPrimary,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Spacer(Modifier.size(4.dp))
                                    Text(
                                        text = "CPF (Brasileiro)",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        letterSpacing = 0.5.sp,
                                        color = GreenPrimary
                                    )
                                }
                            }
                            Spacer(Modifier.size(4.dp))
                            Row(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .clickable { }
                                    .padding(horizontal = 8.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Rounded.WorkspacePremium,
                                    contentDescription = null,
                                    tint = BodyText,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(Modifier.size(4.dp))
                                Text(
                                    text = "Passaporte\n(Estrangeiro)",
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    letterSpacing = 0.5.sp,
                                    color = BodyText,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = Color.White,
                        shadowElevation = 1.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.CreditCard,
                                contentDescription = null,
                                tint = MutedText,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(Modifier.size(8.dp))
                            Column(Modifier.weight(1f)) {
                                Text(
                                    text = "Número do CPF",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    letterSpacing = 0.5.sp,
                                    color = BodyText
                                )
                                Text(
                                    text = cpf,
                                    fontSize = 16.sp,
                                    lineHeight = 24.sp,
                                    letterSpacing = 0.4.sp,
                                    color = NavyText
                                )
                            }
                        }
                    }
                }
            }
            // Country selector
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 1.dp,
                modifier = Modifier.height(56.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.Public,
                        contentDescription = null,
                        tint = MutedText,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(Modifier.size(10.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = "País de Residência",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.5.sp,
                            color = BodyText
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🇧🇷", fontSize = 18.sp)
                            Spacer(Modifier.size(8.dp))
                            Text(
                                text = "Brasil",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.5.sp,
                                color = NavyText
                            )
                        }
                    }
                    Surface(
                        shape = CircleShape,
                        color = LogoRing,
                        modifier = Modifier.size(32.dp),
                    ) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            Icons.Rounded.ExpandMore,
                            contentDescription = null,
                            tint = NavyText,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    }
                }
            }
            // Terms card
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(2.5.dp))
                            .background(if (termosAceitos) GreenPrimary else Color.White)
                    )
                    Spacer(Modifier.size(8.dp))
                    val annotated = buildAnnotatedString {
                        append("Li e aceito os ")
                        pushStyle(SpanStyle(color = GreenPrimary, fontWeight = FontWeight.SemiBold, textDecoration = TextDecoration.Underline))
                        append("Termos de Uso")
                        pop()
                        append(" e a ")
                        pushStyle(SpanStyle(color = GreenPrimary, fontWeight = FontWeight.SemiBold, textDecoration = TextDecoration.Underline))
                        append("Política de Privacidade")
                        pop()
                        append(" da expedição PassaNorte.")
                    }
                    Text(
                        text = annotated,
                        fontSize = 14.sp,
                        lineHeight = 17.5.sp,
                        letterSpacing = 0.25.sp,
                        color = BodyText,
                        modifier = Modifier.clickable { termosAceitos = !termosAceitos }
                    )
                }
            }
            // Gamified reward note
            Surface(
                shape = RoundedCornerShape(32.dp),
                color = Color(0x26FE932C)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Orange),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Rounded.Approval,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    Spacer(Modifier.size(8.dp))
                    val rewardText = buildAnnotatedString {
                        append("Seu carimbo inaugural ")
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        append("\"Desbravador do Rio Negro\"")
                        pop()
                        append(" será estampado instantaneamente!")
                    }
                    Text(
                        text = rewardText,
                        fontSize = 12.sp,
                        lineHeight = 15.sp,
                        letterSpacing = 0.5.sp,
                        fontWeight = FontWeight.Medium,
                        color = OrangeDarkText
                    )
                }
            }
            // Confirm button
            Button(
                onClick = { navController.navigate(Routes.HOME) },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                modifier = Modifier
                    .shadow(2.dp, RoundedCornerShape(50))
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Icon(
                    Icons.Rounded.Approval,
                    contentDescription = null,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(Modifier.size(8.dp))
                Text(
                    text = "Confirmar Cadastro",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.1.sp
                )
                Spacer(Modifier.size(8.dp))
                Icon(
                    Icons.Rounded.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )
            }
        }

        // Decorative ribbon
        Spacer(Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(width = 48.dp, height = 2.dp)
                    .clip(RoundedCornerShape(50))
                    .background(MutedText.copy(alpha = 0.35f))
            )
            Spacer(Modifier.size(16.dp))
            Icon(
                Icons.Rounded.WorkspacePremium,
                contentDescription = null,
                tint = MutedText,
                modifier = Modifier.size(12.dp)
            )
            Spacer(Modifier.size(4.dp))
            Text(
                text = "PASSAPORTE TURÍSTICO INTEGRADO",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.55.sp,
                color = MutedText
            )
            Spacer(Modifier.size(16.dp))
            Box(
                Modifier
                    .size(width = 48.dp, height = 2.dp)
                    .clip(RoundedCornerShape(50))
                    .background(MutedText.copy(alpha = 0.35f))
            )
        }

        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Já tem um passaporte? ",
                fontSize = 14.sp,
                letterSpacing = 0.25.sp,
                color = BodyText
            )
            Text(
                text = "Entrar",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.1.sp,
                color = GreenPrimary,
                modifier = Modifier.clickable { navController.popBackStack() }
            )
        }
    }
}

@Composable
private fun LabeledIconField(
    icon: @Composable () -> Unit,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    trailing: @Composable (() -> Unit)? = null
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier.height(56.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()
            Spacer(Modifier.size(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = label,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.5.sp,
                    color = BodyText
                )
                androidx.compose.foundation.text.BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    singleLine = true,
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.5.sp,
                        color = NavyText
                    )
                )
            }
            trailing?.invoke()
        }
    }
}

// alias para evitar conflito de import
private val GreenDeepCompat = br.edu.uea.passanorte.ui.theme.GreenDeep