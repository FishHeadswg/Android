package com.android.assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity 
	implements View.OnClickListener {
	
	Button btn;
	ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button)findViewById(R.id.button);
		btn.setOnClickListener(this);
		btn.setText(R.string.gator);
		img = (ImageView)findViewById(R.id.nole);
	}

	@Override
	public void onClick(View v) {
		if (btn.getText() == getText(R.string.gator))
		{
			btn.setText(getText(R.string.nole));
			img.setImageResource(R.drawable.gator);
		}
		else
		{
			img.setImageResource(R.drawable.nole);
			btn.setText(getText(R.string.gator));
		}
	}
}
