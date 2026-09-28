package br.edu.uea.passanorte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.uea.passanorte.ui.screens.CadastroScreen
import br.edu.uea.passanorte.ui.screens.CheckinScreen
import br.edu.uea.passanorte.ui.screens.ConclusaoRotaScreen
import br.edu.uea.passanorte.ui.screens.DetalhePontoScreen
import br.edu.uea.passanorte.ui.screens.GuiaIaScreen
import br.edu.uea.passanorte.ui.screens.HomeScreen
import br.edu.uea.passanorte.ui.screens.LoginScreen
import br.edu.uea.passanorte.ui.screens.MinhasRotasScreen
import br.edu.uea.passanorte.ui.screens.PerfilScreen
import br.edu.uea.passanorte.ui.theme.PassaNorteTheme

object Routes {
    const val LOGIN = "login"
    const val CADASTRO = "cadastro"
    const val HOME = "home"
    const val DETALHE = "detalhe"
    const val MINHAS_ROTAS = "minhas_rotas"
    const val GUIA_IA = "guia_ia"
    const val PERFIL = "perfil"
    const val CONCLUSAO_ROTA = "conclusao_rota"
    const val CHECKIN = "checkin"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PassaNorteTheme {
                PassaNorteApp()
            }
        }
    }
}

@Composable
fun PassaNorteApp(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) { LoginScreen(navController) }
        composable(Routes.CADASTRO) { CadastroScreen(navController) }
        composable(Routes.HOME) { HomeScreen(navController) }
        composable(Routes.DETALHE) { DetalhePontoScreen(navController) }
        composable(Routes.MINHAS_ROTAS) { MinhasRotasScreen(navController) }
        composable(Routes.GUIA_IA) { GuiaIaScreen(navController) }
        composable(Routes.PERFIL) { PerfilScreen(navController) }
        composable(Routes.CONCLUSAO_ROTA) { ConclusaoRotaScreen(navController) }
        composable(Routes.CHECKIN) { CheckinScreen(navController) }
    }
}