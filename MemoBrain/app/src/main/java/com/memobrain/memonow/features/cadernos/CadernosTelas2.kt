package com.memobrain.memonow.features.cadernos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class MetodoEstudo(val titulo: String, val emoji: String)
data class AtividadeRecente(val titulo: String, val subtitulo: String)

@Composable
fun CadernosTelas2() {
    val chipsFiltros = listOf("Todos", "Revisar Hoje", "Em andamento", "Concluidos")
    var chipSelecionado by remember { mutableStateOf("Todos") }

    val metodosEstudo = listOf(
        MetodoEstudo("Arrastar e Soltar", "🧩"),
        MetodoEstudo("Resposta Aberta", "✍️"),
        MetodoEstudo("Oclusão de Imagem", "🖼️")
    )

    val atividadesRecentes = listOf(
        AtividadeRecente("Direito Administrativo", "Atos Administrativos"),
        AtividadeRecente("Português", "Morfologia"),
        AtividadeRecente("Ciência de Dados", "Mineração de Dados e Machine Learning")
    )

    Scaffold(
        bottomBar = { MenuInferiorMemonow() },
        containerColor = Color(0xFFF8F9FA)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // 1. CABEÇALHO DE USUÁRIO
            HeaderUsuario(nome = "Allyson Novaes!")

            Spacer(modifier = Modifier.height(24.dp))

            // 2. FILTROS HORIZONTAIS (CHIPS)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(chipsFiltros) { filtro ->
                    val isSelected = filtro == chipSelecionado
                    FilterChipMemonow(
                        texto = filtro,
                        isSelected = isSelected,
                        onClick = { chipSelecionado = filtro }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 3. MÉTODOS DE ESTUDO DISPONÍVEIS
            Text(
                text = "Métodos de estudo disponíveis",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A2536)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                metodosEstudo.forEach { metodo ->
                    CardMetodoEstudo(metodo = metodo, modifier = Modifier.weight(1f))
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // 4. CADERNOS EM ANDAMENTO
            SectionHeader(titulo = "Cadernos em andamento", onVerMaisClick = {})
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(3) {
                    CardCadernoAndamento()
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // 5. ATIVIDADES RECENTES
            SectionHeader(titulo = "Atividades recentes", onVerMaisClick = {})
            Spacer(modifier = Modifier.height(12.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                atividadesRecentes.forEach { atividade ->
                    CardAtividadeRecente(atividade = atividade)
                }
            }
        }
    }
}



@Composable
fun HeaderUsuario(nome: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(Color(0xFFCBD5E1)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "👨‍💻", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = "Olá, $nome",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A2536)
            )
            Text(
                text = "Vamos revisar hoje?",
                fontSize = 12.sp,
                color = Color(0xFF748294)
            )
        }
    }
}

@Composable
fun FilterChipMemonow(texto: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) Color(0xFF1A3B5D) else Color(0xFFEDF1F5),
    ) {
        Text(
            text = texto,
            color = if (isSelected) Color.White else Color(0xFF5A697A),
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun CardMetodoEstudo(metodo: MetodoEstudo, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(80.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = metodo.emoji, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = metodo.titulo,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A2536),
                textAlign = TextAlign.Center,
                lineHeight = 12.sp
            )
        }
    }
}

@Composable
fun SectionHeader(titulo: String, onVerMaisClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = titulo,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A2536)
        )
        Text(
            text = "Ver mais",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF40A798),
            modifier = Modifier.clickable { onVerMaisClick() }
        )
    }
}

@Composable
fun CardCadernoAndamento() {
    Card(
        modifier = Modifier.size(130.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "🧠", fontSize = 48.sp)
        }
    }
}

@Composable
fun CardAtividadeRecente(atividade: AtividadeRecente) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFEDF7F6), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "📖", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = atividade.titulo,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A2536),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = atividade.subtitulo,
                    fontSize = 11.sp,
                    color = Color(0xFF748294),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            IconButton(onClick = { /* Opções */ }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Opções",
                    tint = Color(0xFF9EA8B6),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun MenuInferiorMemonow() {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp,
        modifier = Modifier.height(72.dp)
    ) {
        val itens = listOf("Início", "Cadernos", "Progresso", "Perfil")
        itens.forEachIndexed { index, item ->
            val isSelected = index == 1
            NavigationBarItem(
                selected = isSelected,
                onClick = { /* Navegar */ },
                label = {
                    Text(
                        text = item,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                icon = {
                    val emoji = when(index) {
                        0 -> "🏠"
                        1 -> "📖"
                        2 -> "📊"
                        else -> "👤"
                    }
                    Text(text = emoji, fontSize = 20.sp)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = Color(0xFF1A3B5D),
                    unselectedTextColor = Color(0xFF9EA8B6),
                    indicatorColor = Color(0xFFEBF1F7)
                )
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 800)
@Composable
fun CadernosTela2Preview() {
    CadernosTelas2()
}