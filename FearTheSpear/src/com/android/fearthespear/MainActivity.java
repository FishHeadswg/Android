/* Fear The Spear
 * Trevor Richardson
 * COP4656 Mobile Programming
 * 06/11/2014
 * */

package com.android.fearthespear;

import java.io.IOException;
import java.lang.IllegalStateException;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;

public class MainActivity extends Activity implements View.OnClickListener {

	// ProgressBar
	private Handler progressHandler = new Handler();
	private ProgressBar progressb;
	private int progressStatus;

	int length;

	// Sound effects
	MediaPlayer mp;
	private int stopped = 0; // track between stopped/paused
	private String currBtn = "";
	private int currTime;
	private boolean isPlaying = false;

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);

		// Save state info for rotations
		savedInstanceState.putInt("progressStatusSave", progressStatus);
		savedInstanceState.putInt("stoppedSave", stopped);
		savedInstanceState.putInt("currTimeSave", mp.getCurrentPosition());
		savedInstanceState.putString("currBtnSave", currBtn);
		savedInstanceState.putBoolean("isPlayingSave", mp.isPlaying());
	}

	@Override
	public void onDestroy() {

		// release current mediaplayer after rotation
		if (mp != null) {
			mp.reset();
			mp.release();
		}
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// restore after rotation
		if (savedInstanceState != null) {
			progressStatus = savedInstanceState.getInt("progressStatusSave");
			stopped = savedInstanceState.getInt("stoppedSave");
			currTime = savedInstanceState.getInt("currTimeSave");
			currBtn = savedInstanceState.getString("currBtnSave");
			isPlaying = savedInstanceState.getBoolean("isPlayingSave");
		}

		if (currBtn != "") {
			loadClip(currBtn);
			mp.seekTo(currTime);
			// Remains paused if rotated while paused
			if (isPlaying)
				playClip();
		}

		// Init buttons/layout
		setContentView(R.layout.activity_main);
		Button btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(this);
		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(this);
		Button btn3 = (Button) findViewById(R.id.button3);
		btn3.setOnClickListener(this);
		Button btn4 = (Button) findViewById(R.id.button4);
		btn4.setOnClickListener(this);
		Button btn5 = (Button) findViewById(R.id.button5);
		btn5.setOnClickListener(this);
		Button btn6 = (Button) findViewById(R.id.button6);
		btn6.setOnClickListener(this);
		Button btn7 = (Button) findViewById(R.id.button7);
		btn7.setOnClickListener(this);
		Button play = (Button) findViewById(R.id.play);
		play.setOnClickListener(this);
		Button stop = (Button) findViewById(R.id.stop);
		stop.setOnClickListener(this);
		Button pause = (Button) findViewById(R.id.pause);
		pause.setOnClickListener(this);

		progressb = (ProgressBar) findViewById(R.id.progressBar);

		// Update progress bar
		new Thread(new Runnable() {
			public void run() {
				while (progressStatus < 100) {
					if (mp == null || stopped == 1) // stopped/not loaded
						progressStatus = 0;
					else
						progressStatus = songProgress();

					// Don't want to kill the cpu for a progress bar
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					// Update the progress bar
					progressHandler.post(new Runnable() {
						public void run() {
							progressb.setProgress(progressStatus);
						}
					});
				}
			}
		}).start();

	}

	// calculate song progress
	public int songProgress() {
		try {
			progressStatus = mp.getCurrentPosition() * 100 / mp.getDuration();
			return progressStatus;
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public void onClick(View v) {

		// header for current song
		TextView title = (TextView) findViewById(R.id.title);

		switch (v.getId()) {
		case R.id.button1:
			loadClip("btn1"); // load/play sound effect
			playClip();
			title.setText("Playing: " + getResources().getString(R.string.wc));
			currBtn = "btn1";
			break;
		case R.id.button2:
			loadClip("btn2"); // load/play sound effect
			playClip();
			title.setText("Playing: " + getResources().getString(R.string.fs));
			currBtn = "btn2";
			break;
		case R.id.button3:
			loadClip("btn3"); // load/play sound effect
			playClip();
			title.setText("Playing: " + getResources().getString(R.string.vs));
			currBtn = "btn3";
			break;
		case R.id.button4:
			loadClip("btn4"); // load/play sound effect
			playClip();
			title.setText("Playing: " + getResources().getString(R.string.gag));
			currBtn = "btn4";
			break;
		case R.id.button5:
			loadClip("btn5"); // load/play sound effect
			playClip();
			title.setText("Playing: "
					+ getResources().getString(R.string.cheer));
			currBtn = "btn5";
			break;
		case R.id.button6:
			loadClip("btn6"); // load/play sound effect
			playClip();
			title.setText("Playing: " + getResources().getString(R.string.fqf));
			currBtn = "btn6";
			break;
		case R.id.button7:
			loadClip("btn7"); // load/play sound effect
			playClip();
			title.setText("Playing: " + getResources().getString(R.string.su));
			currBtn = "btn7";
			break;
		case R.id.stop:
			stopClip();
			break;
		case R.id.play:
			playClip();
			break;
		case R.id.pause:
			pauseClip();
			break;
		default:
			throw new RuntimeException("What button did you click??");
		}

	}

	private void pauseClip() {
		if (mp != null && stopped == 0) {
			mp.pause();
		}
	}

	private void stopClip() {
		if (mp != null && stopped != 1) { // latter prevents crashing from
											// spamming stop
			mp.pause();
			mp.seekTo(0);
			mp.stop();
			stopped = 1; // stopped
		}
	}

	private void playClip() {
		if (mp != null) {
			prepareClip();
			mp.start();
			stopped = 0;
		}
	}
	
	private void prepareClip() {
		if (stopped == 1) { // prepare if stopped
			try {
				mp.prepare();
			} catch (IOException e) {
				e.printStackTrace();
			}
			catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
	}

	// Load and play sound effects for each button selection
	private void loadClip(String selection) {
		switch (selection) {
		case "btn1":
			// prevents sound breaking from spam clicking
			if (mp != null) {
				mp.stop();
				mp.release();
				mp = null;
			}
			mp = MediaPlayer.create(this, R.raw.war_chant);
			break;
		case "btn2":
			if (mp != null) {
				mp.stop();
				mp.release();
				mp = null;
			}
			mp = MediaPlayer.create(this, R.raw.fsu_fight_song);
			break;
		case "btn3":
			if (mp != null) {
				mp.stop();
				mp.release();
				mp = null;
			}
			mp = MediaPlayer.create(this, R.raw.victory_song);
			break;
		case "btn4":
			if (mp != null) {
				mp.stop();
				mp.release();
				mp = null;
			}
			mp = MediaPlayer.create(this, R.raw.gold_and_garnett);
			break;
		case "btn5":
			if (mp != null) {
				mp.stop();
				mp.release();
				mp = null;
			}
			mp = MediaPlayer.create(this, R.raw.fsu_cheer);
			break;
		case "btn6":
			if (mp != null) {
				mp.stop();
				mp.release();
				mp = null;
			}
			mp = MediaPlayer.create(this, R.raw.fourth_quarter_fanfare);
			break;
		case "btn7":
			if (mp != null) {
				mp.stop();
				mp.release();
				mp = null;
			}
			mp = MediaPlayer.create(this, R.raw.seminole_uprising);
			break;
		default:
			throw new RuntimeException("Error playing sound.");
		}
	}
}