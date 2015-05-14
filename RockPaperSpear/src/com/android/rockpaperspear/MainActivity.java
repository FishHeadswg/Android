/* Rock Paper Spear
 * Trevor Richardson
 * COP4656 Mobile Programming
 * 05/31/2014
 * 
 * Changelog: Added ad capabilities
 * See lines 37/171/188
 * 
 * */

package com.android.rockpaperspear;

import java.util.Random;
import java.lang.InterruptedException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.PopupWindow;

public class MainActivity extends Activity 
	implements View.OnClickListener {
	
	// keeps track of ad dialog
	boolean adShown;
	
	// Buttons
	ImageButton rock;
	ImageButton paper;
	ImageButton spear;
	Button reset;
	
	// Stats
	int cpuChoice;
	int wins;
	int draws;
	int losses;
	
	// Random CPU choice
	Random rand = new Random();
	
	// Sound effects
	MediaPlayer mp;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Init buttons/layout
		setContentView(R.layout.activity_main);
		rock = (ImageButton)findViewById(R.id.rock);
		rock.setOnClickListener(this);
		paper = (ImageButton)findViewById(R.id.paper);
		paper.setOnClickListener(this);
		spear = (ImageButton)findViewById(R.id.spear);
		spear.setOnClickListener(this);
		reset = (Button)findViewById(R.id.resetbtn);
		reset.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		
		// Various text messages
		TextView result = (TextView)findViewById(R.id.result);
		TextView cpu = (TextView)findViewById(R.id.cpu);
		TextView outcome = (TextView)findViewById(R.id.outcome);
		TextView winamt = (TextView)findViewById(R.id.winamt);
		TextView drawamt = (TextView)findViewById(R.id.drawamt);
		TextView lossamt = (TextView)findViewById(R.id.lossamt);
		
		// Hide results until first play
		result.setVisibility(0);
		cpu.setVisibility(0);
		outcome.setVisibility(0);
		
		// CPU randomly select rock/paper/spear
		cpuChoice = rand.nextInt(3);
		
		// Logic handling for each setup, records win/loss/draws
		switch(v.getId())
		{
		case R.id.rock:
			loadClip("rock"); // load/play sound effect
			switch(cpuChoice)
			{
			case 0:
				cpu.setText("CPU selected ROCK");
				outcome.setText("DRAW");
				outcome.setTextColor(Color.BLUE);
				++draws;
				break;
			case 1:
				cpu.setText("CPU selected PAPER");
				outcome.setText("YOU LOSE");
				outcome.setTextColor(Color.RED);
				++losses;;
				break;
			case 2:
				cpu.setText("CPU selected SPEAR");
				outcome.setText("YOU WIN");
				outcome.setTextColor(Color.parseColor("#00AA00"));
				++wins;;
				break;
			}
			break;
		case R.id.paper:
			loadClip("paper");
			switch(cpuChoice)
			{
			case 0:
				cpu.setText("CPU selected ROCK");
				outcome.setText("YOU WIN");
				outcome.setTextColor(Color.parseColor("#00AA00"));
				++wins;
				break;
			case 1:
				cpu.setText("CPU selected PAPER");
				outcome.setText("DRAW");
				outcome.setTextColor(Color.BLUE);
				++draws;
				break;
			case 2:
				cpu.setText("CPU selected SPEAR");
				outcome.setText("YOU LOSE");
				outcome.setTextColor(Color.RED);
				++losses;
				break;
			}
			break;
		case R.id.spear:
			loadClip("spear");
			switch(cpuChoice)
			{
			case 0:
				cpu.setText("CPU selected ROCK");
				outcome.setText("YOU LOSE");
				outcome.setTextColor(Color.RED);
				++losses;
				break;
			case 1:
				cpu.setText("CPU selected PAPER");
				outcome.setText("YOU WIN");
				outcome.setTextColor(Color.parseColor("#00AA00"));
				++wins;
				break;
			case 2:
				cpu.setText("CPU selected SPEAR");
				outcome.setText("DRAW");
				outcome.setTextColor(Color.BLUE);
				++draws;
				break;
			}
			break;
			
		// Reset stats
		case R.id.resetbtn:
			adShown = false; // Ad tracker
			wins = 0;
			draws = 0;
			losses = 0;
			result.setVisibility(1);
			cpu.setVisibility(1);
			outcome.setVisibility(1);
			break;
		default:
		throw new RuntimeException("What button did you click??");
		}
		
		// Update stats counter
		winamt.setText(String.valueOf(wins));
		drawamt.setText(String.valueOf(draws));
		lossamt.setText(String.valueOf(losses));
		
		if (wins == 3 && adShown == false)
		{
			startActivity(new Intent (this, FSUCSPopup.class));
			adShown = true;
		}
	}
	
	// Load and play sound effects for each button selection
	private void loadClip(String selection) {
		switch (selection) {
		case "rock":
			// prevents sound breaking from spam clicking
			if (mp != null) {
	            mp.stop();
	            mp.release();
	            mp = null;
			}
			mp= MediaPlayer.create( this, R.raw.rock);
			mp.start();
			break;
		case "paper":
			if (mp != null) {
	            mp.stop();
	            mp.release();
	            mp = null;
			}
			mp=MediaPlayer.create( this, R.raw.paper);
			mp.start();
			break;
		case "spear":
			if (mp != null) {
	            mp.stop();
	            mp.release();
	            mp = null;
			}
			mp=MediaPlayer.create( this, R.raw.spear);
			mp.start();
			break;
		default:
			throw new RuntimeException("Error playing sound.");
		}
	}
}
