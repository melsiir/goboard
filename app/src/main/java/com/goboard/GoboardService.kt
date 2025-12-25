package com.goboard

import android.inputmethodservice.InputMethodService
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import com.goboard.ui.KeyboardScreen

class GoboardService : InputMethodService(), ViewModelStoreOwner, SavedStateRegistryOwner {

    private lateinit var keyboardLayoutManager: KeyboardLayoutManager

    override fun onCreate() {
        super.onCreate()
        savedStateRegistryController.performRestore(null)
        keyboardLayoutManager = KeyboardLayoutManager(this)
    }

    override fun onCreateInputView(): View {
        val view = ComposeView(this)
        val sharedPreferences = getSharedPreferences("goboard_settings", MODE_PRIVATE)
        val keyboardHeight = sharedPreferences.getInt("keyboard_height", 200)
        val keySize = sharedPreferences.getFloat("key_size", 1.0f)
        val keyboardLayout = keyboardLayoutManager.loadLayout()

        view.setContent {
            KeyboardScreen(
                keyboardLayout = keyboardLayout,
                height = keyboardHeight.dp,
                keySize = keySize,
                onKeyPress = { text ->
                    when(text) {
                        "Enter" -> sendDownUpKeyEvents(android.view.KeyEvent.KEYCODE_ENTER)
                        else -> currentInputConnection.commitText(text, 1)
                    }
                },
                onDelete = {
                    currentInputConnection.deleteSurroundingText(1, 0)
                }
            )
        }
        return view
    }

    // ViewModelStoreOwner implementation
    private val _viewModelStore = ViewModelStore()
    override val viewModelStore: ViewModelStore
        get() = _viewModelStore

    // SavedStateRegistryOwner implementation
    private val savedStateRegistryController = SavedStateRegistryController.create(this)
    override val savedStateRegistry: SavedStateRegistry
        get() = savedStateRegistryController.savedStateRegistry

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
    }
}
