package com.gboard.goboard;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a Material 3 themed layout programmatically
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(56, 56, 56, 56);
        layout.setBackgroundColor(ContextCompat.getColor(this, R.color.md_theme_light_surface));

        // Add title
        TextView title = new TextView(this);
        title.setText("GoBoard Coding Keyboard");
        title.setTextSize(24);
        title.setTextColor(ContextCompat.getColor(this, R.color.md_theme_light_primary));
        title.setPadding(0, 0, 0, 32);
        layout.addView(title);

        // Add description
        TextView description = new TextView(this);
        description.setText("A keyboard optimized for coding with easy access to programming symbols and operators. Enable GoBoard Keyboard in your device settings to start using it.");
        description.setTextSize(16);
        description.setTextColor(ContextCompat.getColor(this, R.color.md_theme_light_onSurface));
        description.setPadding(0, 0, 0, 48);
        layout.addView(description);

        // Add button to open keyboard settings
        Button settingsButton = new Button(this);
        settingsButton.setText("Open Keyboard Settings");
        settingsButton.setBackgroundColor(ContextCompat.getColor(this, R.color.md_theme_light_primary));
        settingsButton.setTextColor(ContextCompat.getColor(this, R.color.md_theme_light_onPrimary));
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
        enableButton.setText("Enable GoBoard Keyboard");
        enableButton.setBackgroundColor(ContextCompat.getColor(this, R.color.md_theme_light_secondaryContainer));
        enableButton.setTextColor(ContextCompat.getColor(this, R.color.md_theme_light_onSecondaryContainer));
        enableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
                startActivity(intent);
            }
        });
        layout.addView(enableButton);

        setContentView(layout);
    }
}