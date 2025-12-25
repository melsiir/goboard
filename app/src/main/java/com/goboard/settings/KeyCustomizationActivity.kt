package com.goboard.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.goboard.model.DefaultLayout
import com.goboard.model.Key
import com.goboard.ui.KeyView
import com.goboard.ui.theme.GoboardTheme

class KeyCustomizationActivity : ComponentActivity() {

    private lateinit var keyboardLayoutManager: KeyboardLayoutManager
    private var keyboardLayout by mutableStateOf<KeyboardLayout?>(null)

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        keyboardLayoutManager = KeyboardLayoutManager(this)
        keyboardLayout = mutableStateOf(keyboardLayoutManager.loadLayout())

        setContent {
            GoboardTheme {
                var showDialog by remember { mutableStateOf(false) }
                var selectedKey by remember { mutableStateOf<Key?>(null) }
                var selectedRow by remember { mutableStateOf<MutableList<Key>?>(null) }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Customize Keys") }
                        )
                    }
                ) { padding ->
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        keyboardLayout.value?.let { layout ->
                            LazyColumn(
                                modifier = Modifier.padding(padding),
                                contentPadding = PaddingValues(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(layout.rows) { row ->
                                    LazyRow(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        items(row) { key ->
                                            KeyView(
                                                key = key,
                                                keySize = 1.0f,
                                                onKeyPress = {
                                                    selectedKey = key
                                                    selectedRow = row as MutableList<Key>
                                                    showDialog = true
                                                },
                                                onDelete = {}
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        if (showDialog) {
                            var label by remember { mutableStateOf(selectedKey?.label ?: "") }
                            var keyValue by remember { mutableStateOf(selectedKey?.value ?: "") }
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                title = { Text("Edit Key") },
                                text = {
                                    Column {
                                        TextField(
                                            value = label,
                                            onValueChange = { label = it },
                                            label = { Text("Label") }
                                        )
                                        TextField(
                                            value = keyValue,
                                            onValueChange = { keyValue = it },
                                            label = { Text("Value") }
                                        )
                                    }
                                },
                                confirmButton = {
                                    Button(onClick = {
                                        selectedKey?.let { key ->
                                            val index = selectedRow?.indexOf(key)
                                            if (index != null && index != -1) {
                                                selectedRow?.set(index, key.copy(label = label, value = keyValue))
                                            }
                                        }
                                        showDialog = false
                                    }) {
                                        Text("Save")
                                    }
                                },
                                dismissButton = {
                                    Button(onClick = { showDialog = false }) {
                                        Text("Cancel")
                                    }

                                }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        keyboardLayout.value?.let {
            keyboardLayoutManager.saveLayout(it)
        }
    }
}
