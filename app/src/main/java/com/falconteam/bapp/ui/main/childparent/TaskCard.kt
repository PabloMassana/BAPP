package com.falconteam.bapp.ui.main.childparent

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.data.models.Task

@Composable
fun TaskCard(
    task: Task,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    enabled: Boolean = true,
    checked: Boolean = false
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (enabled) Color(0xFFFFECE3) else Color(0xFFE0E0E0)
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                checked = checked || task.completada,
                onCheckedChange = onCheckedChange,
                enabled = enabled
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text("Tarea: ${task.titulo}", fontWeight = FontWeight.SemiBold)
                Text("Objetivo: ${task.objetivo}", fontSize = 13.sp)
            }
        }
    }
}
