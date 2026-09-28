# PassaNorte 🌿

Aplicativo mobile de turismo amazônico para Manaus: um **passaporte digital do explorador** com rotas oficiais, carimbos em pontos turísticos, guia de IA e perfil de turismo sustentável.

## 📱 Funcionalidades

| Tela | Descrição |
|---|---|
| **Login / Cadastro** | Autenticação (Google, CPF brasileiro) e criação de novo passaporte |
| **Home** | Busca de pontos turísticos, categorias, eventos ao vivo e sugestões |
| **Detalhe do Ponto** | Informações do ponto turístico, horários, preço e carimbo digital (check-in por proximidade, < 100 m) |
| **Minhas Rotas** | Rotas oficiais com progresso (ex.: 3/4 pontos visitados, 1.4 km de caminhada) |
| **Guia IA** | Assistente especialista amazônico que monta o dia perfeito em Manaus (roteiros, orçamento, transporte) |
| **Perfil** | Passaporte do explorador, carimbos coletados, brindes resgatados e preferências |
| **Check-in** | Carimbo digital instantâneo ao chegar no ponto |
| **Conclusão de Rota** | Resumo da rota concluída, pontos ganhos e carimbos novos |

## 🛠️ Stack

- **Kotlin** + **Jetpack Compose** (Material 3, edge-to-edge)
- **Navigation Compose** para o fluxo de navegação
- **Gradle** com version catalog (`gradle/libs.versions.toml`)
- SDK mínimo 24 / target e compile SDK 37

## 📂 Estrutura

```
app/src/main/java/br/edu/uea/passanorte/
├── MainActivity.kt          # NavHost e definição de rotas (object Routes)
├── ui/
│   ├── components/          # PassaNorteBottomBar, PillTextField
│   ├── screens/             # Login, Cadastro, Home, DetalhePonto,
│   │                        # MinhasRotas, GuiaIa, Perfil, Checkin, ConclusaoRota
│   └── theme/               # Color, Type, Theme (paleta verde/mint amazônica)
```

As rotas de navegação estão centralizadas em `Routes` (`MainActivity.kt`), com destinos: `login`, `cadastro`, `home`, `detalhe`, `minhas_rotas`, `guia_ia`, `perfil`, `conclusao_rota` e `checkin`.

> **Nota:** atualmente o app é um protótipo de UI — as telas são estáticas (sem backend, persistência ou integrações reais).

## 🚀 Como rodar

```bash
# Build de debug
./gradlew assembleDebug

# Instalar no dispositivo/emulador conectado
./gradlew installDebug
```

Requisitos: Android Studio com SDK 37 e JDK 11+.

## 🎨 Identidade visual

- **Verde primário** `#006948` (ações) e **mint** `#85F8C4` (selo "Turismo Sustentável Oficial")
- **Fundo claro** `#F8F9FF` com azul suave em campos (`#EFF4FF`)
- Tipografia e ícones Material (Material Icons Extended)