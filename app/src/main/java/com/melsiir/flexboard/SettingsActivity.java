package com.melsiir.flexboard;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // for consistancy
        // WindowCompat.enableEdgeToEdge(getWindow());

        // Create a Material 3 themed layout programmatically
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(56, 56, 56, 56);
        layout.setBackgroundColor(ContextCompat.getColor(this, R.color.md_theme_surface));

        // Add title
        TextView title = new TextView(this);
        title.setText("Flexboard");
        title.setTextSize(24);
        title.setTextColor(ContextCompat.getColor(this, R.color.md_theme_primary));
        title.setPadding(0, 0, 0, 32);
        layout.addView(title);

        // Add button to open keyboard settings
        Button settingsButton = new Button(this);
        settingsButton.setText("Open Keyboard Settings");
        settingsButton.setBackgroundColor(ContextCompat.getColor(this, R.color.md_theme_primary));
        settingsButton.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onPrimary));
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
                startActivity(intent);
            }
        });
        layout.addView(settingsButton);

        // Add button to enable keyboard directly
        Button enableButton = new Button(this);
        enableButton.setText("Enable Flexboard Keyboard");
        enableButton.setBackgroundColor(ContextCompat.getColor(this, R.color.md_theme_secondaryContainer));
        enableButton.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onSecondaryContainer));
        enableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                imeManager.showInputMethodPicker();
            }
        });
        layout.addView(enableButton);
        
       // TextView paragraphType = new TextView(this);
         EditText testEditText = new EditText(this);
         testEditText.setHint("Test The Keyboard Here");
         layout.addView(testEditText);
        setContentView(layout);
    }
}
