package com.goboard.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import org.json.JSONObject
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import com.goboard.ui.theme.GoboardTheme

class SettingsActivity : ComponentActivity() {

    private val createDocument = registerForActivityResult(ActivityResultContracts.CreateDocument("application/json")) { uri ->
        uri?.let {
            contentResolver.openOutputStream(it)?.use { outputStream ->
                val json = JSONObject()
                val sharedPreferences = getSharedPreferences("goboard_settings", MODE_PRIVATE)
                json.put("keyboard_height", sharedPreferences.getInt("keyboard_height", 200))
                json.put("key_size", sharedPreferences.getFloat("key_size", 1.0f))
                json.put("keyboard_layout", sharedPreferences.getString("keyboard_layout", null))
                outputStream.write(json.toString(4).toByteArray())
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoboardTheme {
                val context = LocalContext.current
                val sharedPreferences = remember {
                    context.getSharedPreferences("goboard_settings", MODE_PRIVATE)
                }
                var keyboardHeight by remember {
                    mutableStateOf(sharedPreferences.getInt("keyboard_height", 200).toFloat())
                }
                var keySize by remember {
                    mutableStateOf(sharedPreferences.getFloat("key_size", 1.0f))
                }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Goboard Settings") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    }
                ) { padding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Keyboard Height: ${keyboardHeight.toInt()}dp")
                            Slider(
                                value = keyboardHeight,
                                onValueChange = {
                                    keyboardHeight = it
                                    with(sharedPreferences.edit()) {
                                        putInt("keyboard_height", it.toInt())
                                        apply()
                                    }
                                },
                                valueRange = 100f..400f
                            )
                            Text("Key Size: ${keySize}x")
                            Slider(
                                value = keySize,
                                onValueChange = {
                                    keySize = it
                                    with(sharedPreferences.edit()) {
                                        putFloat("key_size", it)
                                        apply()
                                    }
                                },
                                valueRange = 0.5f..1.5f
                            )
                            Button(onClick = {
                                val intent = Intent(context, KeyCustomizationActivity::class.java)
                                context.startActivity(intent)
                            }) {
                                Text("Customize Keys")
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(onClick = { exportSettings() }) {
                                    Text("Export Settings")
                                }
                                Button(onClick = { importSettings() }) {
                                    Text("Import Settings")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private val openDocument = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let {
            contentResolver.openInputStream(it)?.use { inputStream ->
                val json = JSONObject(String(inputStream.readBytes()))
                val sharedPreferences = getSharedPreferences("goboard_settings", MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putInt("keyboard_height", json.getInt("keyboard_height"))
                    putFloat("key_size", json.getDouble("key_size").toFloat())
                    putString("keyboard_layout", json.getString("keyboard_layout"))
                    apply()
                }
                recreate()
            }
        }
    }

    private fun importSettings() {
        openDocument.launch(arrayOf("application/json"))
    }

    private fun exportSettings() {
        createDocument.launch("goboard_settings.json")
    }
}
