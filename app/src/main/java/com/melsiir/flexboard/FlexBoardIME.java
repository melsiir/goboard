package com.melsiir.flexboard;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.HapticFeedbackConstants;

public class FlexBoardIME extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

  private KeyboardView kv;
  private Keyboard keyboard;

  // @Override
  // public View onCreateInputView() {
  // kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view,
  // null);
  // keyboard = new Keyboard(this, R.xml.coding_keyboard);
  // kv.setKeyboard(keyboard);
  // kv.setOnKeyboardActionListener(this);
  // return kv;
  // }

  @Override
  public View onCreateInputView() {
    Context themedContext = new ContextThemeWrapper(
        this,
        R.style.AppTheme);
    LayoutInflater inflater = LayoutInflater.from(themedContext);
    kv = (KeyboardView) inflater.inflate(
        R.layout.keyboard_view,
        null);
    keyboard = new Keyboard(themedContext, R.xml.coding_keyboard);
    kv.setKeyboard(keyboard);
    kv.setOnKeyboardActionListener(this);
    return kv;
  }

  @Override
  public void onPress(int primaryCode) {
    // Provide haptic feedback on key press
    if (kv != null) {
      kv.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }
  }

  @Override
  public void onRelease(int primaryCode) {
    // Handle key release
  }

  @Override
  public void onKey(int primaryCode, int[] keyCodes) {
    InputConnection ic = getCurrentInputConnection();
    if (ic == null)
      return;

    switch (primaryCode) {
      case Keyboard.KEYCODE_DELETE:
        CharSequence selectedText = ic.getSelectedText(0);
        if (TextUtils.isEmpty(selectedText)) {
          ic.deleteSurroundingText(1, 0);
        } else {
          ic.commitText("", 1);
        }
        break;
      case Keyboard.KEYCODE_SHIFT:
        keyboard.setShifted(!keyboard.isShifted());
        kv.invalidateAllKeys();
        break;
      case Keyboard.KEYCODE_DONE:
        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
        break;
      default:
        char code = (char) primaryCode;
        if (code != 0) {
          ic.commitText(String.valueOf(code), 1);
        }
        break;
    }
  }

  @Override
  public void onText(CharSequence text) {
    // Handle text input
  }

  @Override
  public void swipeLeft() {
    // Handle swipe gestures if needed
  }

  @Override
  public void swipeRight() {
  }

  @Override
  public void swipeDown() {
  }

  @Override
  public void swipeUp() {
  }
}
