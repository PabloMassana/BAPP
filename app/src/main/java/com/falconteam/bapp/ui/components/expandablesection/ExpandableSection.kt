package com.falconteam.bapp.ui.components.expandablesection

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.ui.theme.BAPPTheme
import io.github.jan.supabase.realtime.Column

@Composable
fun ExpandableSection(
    modifier: Modifier = Modifier,
    sectionTitle: String,
    sectionItemsShowed: List<SectionItem>,
    sectionItemsRemaining: List<SectionItem>? = null,
    isLoading: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0D0))
    ) {
        if (isLoading) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color(0xFFF09985),
                )
            }
        } else {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = sectionTitle,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
                Spacer(Modifier.height(16.dp))

                if (sectionItemsShowed.isEmpty()) {
                    Text(
                        text = "No hay elementos para mostrar",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(16.dp))
                    return@Column
                }

                sectionItemsShowed.forEach {
                    ExpandableSectionItem(
                        id = it.id,
                        name = it.name,
                        itemActions = it.actions
                    )
                }

                AnimatedVisibility(
                    visible = expanded && sectionItemsRemaining.isNullOrEmpty().not()
                ) {
                    Column {
                        sectionItemsRemaining?.forEach {
                            ExpandableSectionItem(
                                id = it.id,
                                name = it.name,
                                itemActions = it.actions
                            )
                            Spacer(Modifier.height(8.dp))
                        }
                    }
                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = { expanded = expanded.not() },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            modifier = Modifier.rotate(if (expanded) 180f else 0f),
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Expand",
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ExpandableSectionItem(
    id: Int,
    name: String,
    itemActions: List<SectionAction>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$id - $name",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.weight(1f)
            )
            itemActions.forEach {
                Button(
                    modifier = Modifier.height(32.dp).padding(horizontal = 2.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF09985)),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                    onClick = { it.onClick(id) }
                ) {
                    Text(text = it.label, fontSize = 12.sp)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ExpandableSectionPreview() {
    BAPPTheme {
        ExpandableSection(
            modifier = Modifier.fillMaxWidth(),
            sectionTitle = "Grupos existentes",
            sectionItemsShowed = listOf(
                SectionItem(
                    id = 1,
                    name = "Grupo A",
                    actions = listOf(
                        SectionAction(label = "Alumnos", onClick = {}),
                        SectionAction(label = "Docente", onClick = {}),
                    )
                ),
                SectionItem(
                    id = 2,
                    name = "Grupo B",
                    actions = listOf(
                        SectionAction(label = "Asignar hijo", onClick = {}),
                    )
                )
            ),
            sectionItemsRemaining = listOf(
                SectionItem(
                    id = 3,
                    name = "Grupo C",
                    actions = listOf(
                        SectionAction(label = "Asignar hijo", onClick = {}),
                    )
                ),
                SectionItem(
                    id = 4,
                    name = "Grupo D",
                    actions = listOf(
                        SectionAction(label = "Asignar hijo", onClick = {}),
                    )
                )
            )
        )
    }
}