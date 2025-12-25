package com.goboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.goboard.model.DefaultLayout
import com.goboard.model.Key
import com.goboard.model.KeyboardLayout
import com.goboard.ui.KeyView

@Composable
fun KeyboardScreen(
    keyboardLayout: KeyboardLayout,
    height: Dp,
    keySize: Float,
    onKeyPress: (String) -> Unit,
    onDelete: () -> Unit
) {
    var currentLayout by remember { mutableStateOf(keyboardLayout) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(MaterialTheme.colorScheme.surface)
            .padding(2.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        currentLayout.rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
            ) {
                row.forEach { key ->
                    KeyView(
                        key = key,
                        keySize = keySize,
                        onKeyPress = {
                            when (it) {
                                "?123" -> currentLayout = DefaultLayout.numeric
                                "ABC" -> currentLayout = DefaultLayout.qwerty
                                "Shift" -> currentLayout = if (currentLayout == DefaultLayout.qwerty) {
                                    DefaultLayout.qwertyShift
                                } else {
                                    DefaultLayout.qwerty
                                }
                                else -> onKeyPress(it)
                            }
                        },
                        onDelete = onDelete
                    )
                }
            }
        }
    }
}

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback


