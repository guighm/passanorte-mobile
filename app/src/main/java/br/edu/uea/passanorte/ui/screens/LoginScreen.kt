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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Approval
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Eco
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.edu.uea.passanorte.R
import br.edu.uea.passanorte.Routes
import br.edu.uea.passanorte.ui.components.PillTextField
import br.edu.uea.passanorte.ui.theme.AppBackground
import br.edu.uea.passanorte.ui.theme.BodyText
import br.edu.uea.passanorte.ui.theme.CompassBrown
import br.edu.uea.passanorte.ui.theme.DividerBlue
import br.edu.uea.passanorte.ui.theme.FieldBlue
import br.edu.uea.passanorte.ui.theme.GovBrBlue
import br.edu.uea.passanorte.ui.theme.GreenDeep
import br.edu.uea.passanorte.ui.theme.GreenPrimary
import br.edu.uea.passanorte.ui.theme.LogoRing
import br.edu.uea.passanorte.ui.theme.Mint
import br.edu.uea.passanorte.ui.theme.MutedText
import br.edu.uea.passanorte.ui.theme.NavyText

@Composable
fun LoginScreen(navController: NavHostController) {
    var identifier by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var rememberMe by rememberSaveable { mutableStateOf(true) }
    var showPassword by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp, bottom = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OfficialStampBadge()
            Spacer(Modifier.height(16.dp))
            BadgePill()
            Spacer(Modifier.height(8.dp))
            Text(
                text = "PassaNorte",
                fontSize = 28.sp,
                lineHeight = 36.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (-0.7).sp,
                color = NavyText,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Bem-vindo de volta, explorador! Acesse seus selos e rotas amazônicas.",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = BodyText,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 48.dp)
            )
        }

        AuthenticationCard(
            identifier = identifier,
            onIdentifierChange = { identifier = it },
            password = password,
            onPasswordChange = { password = it },
            showPassword = showPassword,
            onTogglePassword = { showPassword = !showPassword },
            rememberMe = rememberMe,
            onRememberMeChange = { rememberMe = it },
            onLogin = { navController.navigate(Routes.HOME) },
            onCadastro = { navController.navigate(Routes.CADASTRO) }
        )

        Spacer(Modifier.height(24.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Primeira vez nas trilhas do Norte?",
                fontSize = 14.sp,
                color = BodyText,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Surface(
                shape = RoundedCornerShape(50),
                color = DividerBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clickable { navController.navigate(Routes.CADASTRO) },
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Rounded.Shield,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Cadastrar novo passaporte",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = GreenPrimary
                    )
                }
            }
            }
        }

        Spacer(Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Rounded.Lock,
                contentDescription = null,
                tint = MutedText.copy(alpha = 0.7f),
                modifier = Modifier.size(12.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Criptografia 256-bit",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = MutedText.copy(alpha = 0.7f)
            )
            Spacer(Modifier.width(16.dp))
            Box(
                Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(MutedText.copy(alpha = 0.5f))
            )
            Spacer(Modifier.width(16.dp))
            Icon(
                Icons.Rounded.Eco,
                contentDescription = null,
                tint = MutedText.copy(alpha = 0.7f),
                modifier = Modifier.size(13.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Pegada Neutra de Carbono",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = MutedText.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun OfficialStampBadge() {
    Box {
        Box(
            modifier = Modifier
                .size(128.dp)
                // Ambient organic glow aura (Figma 1:1186): círculo mint esfumado
                // atrás do carimbo, desenhado sem afetar o layout.
                .drawBehind {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(Mint.copy(alpha = 0.35f), Mint.copy(alpha = 0f)),
                            center = Offset(
                                x = size.width / 2f - 12.dp.toPx(),
                                y = size.height / 2f - 8.dp.toPx()
                            ),
                            radius = 96.dp.toPx()
                        )
                    )
                }
                .clip(CircleShape)
                .background(LogoRing)
                .shadow(4.dp, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.login_stamp),
                contentDescription = "PassaNorte",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 4.dp, y = 4.dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(CompassBrown),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Rounded.Explore,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun BadgePill() {
    Surface(
        shape = RoundedCornerShape(50),
        color = Mint,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Rounded.Eco,
                contentDescription = null,
                tint = GreenDeep,
                modifier = Modifier.size(12.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = "Turismo Sustentável Oficial",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp,
                color = GreenDeep
            )
        }
    }
}

@Composable
private fun AuthenticationCard(
    identifier: String,
    onIdentifierChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    showPassword: Boolean,
    onTogglePassword: () -> Unit,
    rememberMe: Boolean,
    onRememberMeChange: (Boolean) -> Unit,
    onLogin: () -> Unit,
    onCadastro: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(24.dp)) {
            Text(
                text = "E-mail, CPF ou Passaporte",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp,
                color = BodyText,
                modifier = Modifier.padding(bottom = 6.dp, start = 4.dp)
            )
            PillTextField(
                value = identifier,
                onValueChange = onIdentifierChange,
                placeholder = "viajante@passanorte.br",
                leading = {
                    Icon(
                        Icons.Rounded.Badge,
                        contentDescription = null,
                        tint = MutedText,
                        modifier = Modifier.size(22.dp)
                    )
                }
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Senha de Acesso",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp,
                color = BodyText,
                modifier = Modifier.padding(bottom = 6.dp, start = 4.dp)
            )
            PillTextField(
                value = password,
                onValueChange = onPasswordChange,
                placeholder = "••••••••",
                visualTransformation = if (showPassword) VisualTransformation.None
                else PasswordVisualTransformation(),
                leading = {
                    Icon(
                        Icons.Rounded.Lock,
                        contentDescription = null,
                        tint = MutedText,
                        modifier = Modifier.size(22.dp)
                    )
                },
                trailing = {
                    IconButton(onClick = onTogglePassword) {
                        Icon(
                            if (showPassword) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
                            contentDescription = null,
                            tint = MutedText,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
            Spacer(Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onRememberMeChange(!rememberMe) }
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (rememberMe) GreenPrimary else Color.Transparent)
                            .border(1.dp, GreenPrimary, RoundedCornerShape(6.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        if (rememberMe) {
                            Icon(
                                Icons.Rounded.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Lembrar de mim",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.5.sp,
                        color = BodyText
                    )
                }
                Text(
                    text = "Esqueceu a senha?",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.5.sp,
                    color = GreenPrimary,
                    modifier = Modifier.clickable { }
                )
            }
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = onLogin,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Icon(
                    Icons.Rounded.Approval,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Acessar meu Passaporte",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(DividerBlue)
                )
                Column(
                    modifier = Modifier.padding(start = 12.dp, end = 27.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "OU",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.55.sp,
                        color = MutedText
                    )
                    Text(
                        text = "CONTINUE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.55.sp,
                        color = MutedText
                    )
                    Text(
                        text = "COM",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.55.sp,
                        color = MutedText
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = FieldBlue,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clickable { },
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "gov.br",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = GovBrBlue
                    )
                }
                }
                Surface(
                    shape = RoundedCornerShape(50),
                    color = FieldBlue,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clickable { },
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(R.drawable.ic_google_g),
                        contentDescription = "Google",
                        modifier = Modifier.size(20.dp)
                    )
                }
                }
                Surface(
                    shape = RoundedCornerShape(50),
                    color = FieldBlue,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clickable { },
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(R.drawable.ic_social_id),
                        contentDescription = "ID",
                        modifier = Modifier.size(width = 14.dp, height = 18.dp)
                    )
                }
                }
            }
        }
    }
}