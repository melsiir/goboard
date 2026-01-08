package com.credit.calc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;
import android.animation.ValueAnimator;

public class MainActivity extends AppCompatActivity {
	
	EditText etAmount, etDiscount;
	TextView tvResult;
	Button btnCalculate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etAmount = findViewById(R.id.etAmount);
		etDiscount = findViewById(R.id.etDiscount);
		btnCalculate = findViewById(R.id.btnCalculate);
		tvResult = findViewById(R.id.tvResult);
		
		btnCalculate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				calculateNetAmount();
			}
		});
	}
	private double lastNetAmount = 0.0;
   private void animateNetAmount(TextView tv, double newValue) {

    if (newValue <= 0) {
        tv.setText("Net Amount: 0.00");
        return;
    }

    // 10% jump feels good visually
    double percentJump = 0.10;

    // Absolute minimum change so small numbers still animate
    double minDelta = 1.0;

    double delta = Math.max(newValue * percentJump, minDelta);
    double startValue = newValue - delta;

    if (startValue < 0) startValue = 0;

    ValueAnimator animator = ValueAnimator.ofFloat(
            (float) startValue,
            (float) newValue
    );

    animator.setDuration(300); // constant duration = consistent feel

    animator.addUpdateListener(a -> {
        float v = (float) a.getAnimatedValue();
        tv.setText("Net Amount: " +
                String.format(Locale.ENGLISH, "%.2f", v));
    });

    animator.start();
}

	private void calculateNetAmount() {
		String amountStr = etAmount.getText().toString().trim();
		String discountStr = etDiscount.getText().toString().trim();
		
		if (amountStr.isEmpty() || discountStr.isEmpty()) {
			Toast.makeText(this, "Please enter both values", Toast.LENGTH_SHORT).show();
			return;
		}
		
		double amount = Double.parseDouble(amountStr);
		double discountPercent = Double.parseDouble(discountStr);
		
		double discountValue = amount * (discountPercent / 100);
		double netAmount = amount - discountValue;
		
		//tvResult.setText("Net Amount: " + String.format(Locale.ENGLISH, "%.2f", netAmount));
        animateNetAmount(tvResult, netAmount);
	}
}