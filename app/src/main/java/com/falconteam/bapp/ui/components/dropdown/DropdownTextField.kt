package com.falconteam.bapp.ui.components.dropdown

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownTextField(
    modifier: Modifier = Modifier,
    expandedDropdownBox: Boolean = false,
    onExpandableChange: (Boolean) -> Unit = {},
    onDismissRequest: () -> Unit,
    options: List<String>,
    selectedOption: String,
    onClickOption: (String) -> Unit,
    label: String,
) {
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expandedDropdownBox,
        onExpandedChange = onExpandableChange
    ) {
        OutlinedTextField(
            modifier = modifier
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable),
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Icon"
                )
            }
        )
        ExposedDropdownMenu(
            expanded = expandedDropdownBox,
            onDismissRequest = onDismissRequest
        ) {
            options.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        onClickOption(item)
                        onDismissRequest()
                    }
                )
            }
        }
    }
}