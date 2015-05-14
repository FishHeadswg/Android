/* Tic-Tac-Nole
 * Trevor Richardson
 * COP4656 Mobile Programming
 * Project 1
 * 06/30/2014
 * 
 * Tic-Tac-Toe game with an FSU theme. Human and CPU opponent capabilities.
 * Use options menu to switch between opponent types or reset game.
 * */

package com.android.tic_tac_nole;

import java.util.Random;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

	// settings
	private boolean vsComp = true; // On by default
	private boolean inPlay; // game in progress
	private boolean WinnerX; // X won
	private boolean WinnerO; // O won

	// cpu logic
	private Random rand = new Random();
	private int cpuX;
	private int cpuY;

	private int gameBoard[][]; // matrix for tracking board state
	private int turn; // track turns
	TextView turnResult; // display turns and display game results

	// buttons
	Button box0;
	Button box1;
	Button box2;
	Button box3;
	Button box4;
	Button box5;
	Button box6;
	Button box7;
	Button box8;
	Button playAgain; // set visible on game end

	// Sound effects
	MediaPlayer mp;

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);

		// Save settings
		savedInstanceState.putBoolean("vsComp", vsComp);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); // use custom title
															// bar
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.fsutitle);

		if (savedInstanceState != null) {
			vsComp = savedInstanceState.getBoolean("vsComp");
		}

		// init settings
		inPlay = true;
		WinnerX = false;
		WinnerO = false;
		gameBoard = new int[3][3];
		turn = 0;

		// init views
		box0 = (Button) findViewById(R.id.box0);
		box0.setOnClickListener(this);
		box1 = (Button) findViewById(R.id.box1);
		box1.setOnClickListener(this);
		box2 = (Button) findViewById(R.id.box2);
		box2.setOnClickListener(this);
		box3 = (Button) findViewById(R.id.box3);
		box3.setOnClickListener(this);
		box4 = (Button) findViewById(R.id.box4);
		box4.setOnClickListener(this);
		box5 = (Button) findViewById(R.id.box5);
		box5.setOnClickListener(this);
		box6 = (Button) findViewById(R.id.box6);
		box6.setOnClickListener(this);
		box7 = (Button) findViewById(R.id.box7);
		box7.setOnClickListener(this);
		box8 = (Button) findViewById(R.id.box8);
		box8.setOnClickListener(this);
		playAgain = (Button) findViewById(R.id.playAgain);
		playAgain.setOnClickListener(this);

		turnResult = (TextView) findViewById(R.id.turnResult);
		turnResult.setText("Turn: O"); // Player O always starts

	}

	// Processes moves
	@Override
	public void onClick(View v) {

		// Increment turn counter
		++turn;

		// Sets boxes, updates board array and turn display
		if (!WinnerX && !WinnerO && inPlay) {
			
			// play move fx
			if (v.getId() != R.id.playAgain)
			loadClip();
			
			switch (v.getId()) {
			case R.id.box0:
				box0.setEnabled(false);
				// Determines whether X or O moved and sets 1 or -1,
				// respectively
				if (vsComp) { // Player (O) must have clicked
					box0.setText("O");
					gameBoard[0][0] = -1;
				} else {
					if (turn % 2 == 0) {
						box0.setText("X");
						gameBoard[0][0] = 1;
						turnResult.setText("Turn: O");
					} else {
						box0.setText("O");
						gameBoard[0][0] = -1;
						turnResult.setText("Turn: X");
					}
				}
				break;
			case R.id.box1:
				box1.setEnabled(false);
				if (vsComp) {
					box1.setText("O");
					gameBoard[0][1] = -1;
				} else {
					if (turn % 2 == 0) {
						box1.setText("X");
						gameBoard[0][1] = 1;
						turnResult.setText("Turn: O");
					} else {
						box1.setText("O");
						gameBoard[0][1] = -1;
						turnResult.setText("Turn: X");
					}
				}
				break;
			case R.id.box2:
				box2.setEnabled(false);
				if (vsComp) {
					box2.setText("O");
					gameBoard[0][2] = -1;
				} else {
					if (turn % 2 == 0) {
						box2.setText("X");
						gameBoard[0][2] = 1;
						turnResult.setText("Turn: O");
					} else {
						box2.setText("O");
						gameBoard[0][2] = -1;
						turnResult.setText("Turn: X");
					}
				}
				break;
			case R.id.box3:
				box3.setEnabled(false);
				if (vsComp) {
					box3.setText("O");
					gameBoard[1][0] = -1;
				} else {
					if (turn % 2 == 0) {
						box3.setText("X");
						gameBoard[1][0] = 1;
						turnResult.setText("Turn: O");
					} else {
						box3.setText("O");
						gameBoard[1][0] = -1;
						turnResult.setText("Turn: X");
					}
				}
				break;
			case R.id.box4:
				box4.setEnabled(false);
				if (vsComp) {
					box4.setText("O");
					gameBoard[1][1] = -1;
				} else {
					if (turn % 2 == 0) {
						box4.setText("X");
						gameBoard[1][1] = 1;
						turnResult.setText("Turn: O");
					} else {
						box4.setText("O");
						gameBoard[1][1] = -1;
						turnResult.setText("Turn: X");
					}
				}
				break;
			case R.id.box5:
				box5.setEnabled(false);
				if (vsComp) {
					box5.setText("O");
					gameBoard[1][2] = -1;
				} else {
					if (turn % 2 == 0) {
						box5.setText("X");
						gameBoard[1][2] = 1;
						turnResult.setText("Turn: O");
					} else {
						box5.setText("O");
						gameBoard[1][2] = -1;
						turnResult.setText("Turn: X");
					}
				}
				break;
			case R.id.box6:
				box6.setEnabled(false);
				if (vsComp) {
					box6.setText("O");
					gameBoard[2][0] = -1;
				} else {
					if (turn % 2 == 0) {
						box6.setText("X");
						gameBoard[2][0] = 1;
						turnResult.setText("Turn: O");
					} else {
						box6.setText("O");
						gameBoard[2][0] = -1;
						turnResult.setText("Turn: X");
					}
				}
				break;
			case R.id.box7:
				box7.setEnabled(false);
				if (vsComp) {
					box7.setText("O");
					gameBoard[2][1] = -1;
				} else {
					if (turn % 2 == 0) {
						box7.setText("X");
						gameBoard[2][1] = 1;
						turnResult.setText("Turn: O");
					} else {
						box7.setText("O");
						gameBoard[2][1] = -1;
						turnResult.setText("Turn: X");
					}
				}
				break;
			case R.id.box8:
				box8.setEnabled(false);
				if (vsComp) {
					box8.setText("O");
					gameBoard[2][2] = -1;
				} else {
					if (turn % 2 == 0) {
						box8.setText("X");
						gameBoard[2][2] = 1;
						turnResult.setText("Turn: O");
					} else {
						box8.setText("O");
						gameBoard[2][2] = -1;
						turnResult.setText("Turn: X");
					}
				}
				break;
			default:
				throw new RuntimeException("What box did you click??");
			} // end switch

			// Check for win after each move
			winCheck();

			// CPU decides its move
			if (vsComp) {
				int x = 0;
				int y = 0;
				boolean searching = true;
				int searchLimit = 0;

				while (searching && searchLimit < 25) {
					boolean searchDone = false;
					++searchLimit;

					// Search columns for winning move
					for (int i = 0; i < 3; i++) {
						// Easy mode (it'll sometimes miss a win to block an O
						// win instead)
						// Absolute checks for -2 (to block O) or +2 (to win X)
						if (Math.abs(gameBoard[0][i] + gameBoard[1][i]
								+ gameBoard[2][i]) == 2) {
							for (int _x = 0; _x < 3; _x++) {
								if (gameBoard[_x][i] == 0) {
									cpuX = _x;
								}
							}
							cpuY = i;
							searchDone = true;
							break;
						}

					}

					// Search rows for winning move/block
					if (!searchDone) {
						for (int i = 0; i < 3; i++) {
							if (Math.abs(gameBoard[i][0] + gameBoard[i][1]
									+ gameBoard[i][2]) == 2) {
								for (int _y = 0; _y < 3; _y++) {
									if (gameBoard[i][_y] == 0) {
										cpuY = _y;
									}
								}
								cpuX = i;
								searchDone = true;
								break;
							}
						}
					}

					// Searh diagonal for winning move/block
					if (!searchDone) {
						if (Math.abs(gameBoard[0][0] + gameBoard[1][1]
								+ gameBoard[2][2]) == 2) {
							if (gameBoard[0][0] == 0) {
								cpuX = 0;
								cpuY = 0;
							} else if (gameBoard[1][1] == 0) {
								cpuX = 1;
								cpuY = 1;
							} else if (gameBoard[2][2] == 0) {
								cpuX = 2;
								cpuY = 2;
							}
							searchDone = true;
						} else if (Math.abs(gameBoard[2][0] + gameBoard[1][1]
								+ gameBoard[0][2]) == 2) {
							if (gameBoard[2][0] == 0) {
								cpuX = 2;
								cpuY = 0;
							} else if (gameBoard[1][1] == 0) {
								cpuX = 1;
								cpuY = 1;
							} else if (gameBoard[0][2] == 0) {
								cpuX = 0;
								cpuY = 2;
							}
							searchDone = true;
						}
					}

					// move found, done searching
					if (searchDone) {
						searching = false;
					}
					// move not found, pick a random free spot
					else {
						y = rand.nextInt(3);
						x = rand.nextInt(3);

						if (gameBoard[x][y] == 0) {
							cpuX = x;
							cpuY = y;
							searching = false;
						}
					}
				} // end LookingForSpot while

				// Set button for decided CPU move
				switch (cpuX) {
				case 0:
					switch (cpuY) {
					case 0:
						box0.setText("X");
						box0.setEnabled(false);
						gameBoard[cpuX][cpuY] = 1;
						turnResult.setText("Turn: O");
						break;
					case 1:
						box1.setText("X");
						box1.setEnabled(false);
						gameBoard[cpuX][cpuY] = 1;
						turnResult.setText("Turn: O");
						break;
					case 2:
						box2.setText("X");
						box2.setEnabled(false);
						gameBoard[cpuX][cpuY] = 1;
						turnResult.setText("Turn: O");
						break;
					}
					break;
				case 1:
					switch (cpuY) {
					case 0:
						box3.setText("X");
						box3.setEnabled(false);
						gameBoard[cpuX][cpuY] = 1;
						turnResult.setText("Turn: O");
						break;
					case 1:
						box4.setText("X");
						box4.setEnabled(false);
						gameBoard[cpuX][cpuY] = 1;
						turnResult.setText("Turn: O");
						break;
					case 2:
						box5.setText("X");
						box5.setEnabled(false);
						gameBoard[cpuX][cpuY] = 1;
						turnResult.setText("Turn: O");
						break;
					}
					break;
				case 2:
					switch (cpuY) {
					case 0:
						box6.setText("X");
						box6.setEnabled(false);
						gameBoard[cpuX][cpuY] = 1;
						turnResult.setText("Turn: O");
						break;
					case 1:
						box7.setText("X");
						box7.setEnabled(false);
						gameBoard[cpuX][cpuY] = 1;
						turnResult.setText("Turn: O");
						break;
					case 2:
						box8.setText("X");
						box8.setEnabled(false);
						gameBoard[cpuX][cpuY] = 1;
						turnResult.setText("Turn: O");
						break;
					}
					break;
				}

			} // end CPU move
			// check if cpu won
			winCheck();

		} // end if

		// separate from other buttons so it can be clicked when inPlay == false
		// and not trigger a move on reset
		if (v.getId() == R.id.playAgain)
			this.recreate();

	} // end onClick

	private void loadClip() {
		if (mp != null) {
			mp.stop();
			mp.release();
			mp = null;
		}
		mp = MediaPlayer.create(this, R.raw.move);
		mp.start();
	}

	// Checks if a win has occurs over row/col/diag
	private void winCheck() {

		// Checks if there are any moves still left
		int movesLeft = 9; // max moves in 3x3 TTT
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (gameBoard[i][j] != 0) {
					movesLeft--;
				}
			}
		}

		// Win over column?
		for (int j = 0; j < 3; ++j) {
			if (gameBoard[0][j] + gameBoard[1][j] + gameBoard[2][j] == -3) {
				WinnerO = true;
			} else if (gameBoard[0][j] + gameBoard[1][j] + gameBoard[2][j] == 3) {
				WinnerX = true;
			}
		}

		// Win over row?
		for (int i = 0; i < 3; ++i) {
			if (gameBoard[i][0] + gameBoard[i][1] + gameBoard[i][2] == -3) {
				WinnerO = true;

			} else if (gameBoard[i][0] + gameBoard[i][1] + gameBoard[i][2] == 3) {
				WinnerX = true;
			}
		}

		// Win over diagonal?
		if (gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2] == 3) {
			WinnerX = true;
		} else if (gameBoard[2][0] + gameBoard[1][1] + gameBoard[0][2] == 3) {
			WinnerX = true;
		} else if (gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2] == -3) {
			WinnerO = true;
		} else if (gameBoard[2][0] + gameBoard[1][1] + gameBoard[0][2] == -3) {
			WinnerO = true;
		}

		// Displays winner dialog
		if (WinnerX) {
			turnResult.setText("PLAYER X WINS!");
			playAgain.setVisibility(View.VISIBLE);
		} else if (WinnerO) {
			turnResult.setText("PLAYER O WINS!");
			playAgain.setVisibility(View.VISIBLE);
		} else if (movesLeft == 0) {
			inPlay = false;
			turnResult.setText("TIE GAME!");
			playAgain.setVisibility(View.VISIBLE);
		}
	} // end winCheck

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.option, menu);
		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.newgame:
			this.recreate(); // reset activity
			return (true);
		case R.id.comp:
			vsComp = true; // vs comp
			this.recreate();
			return (true);
		case R.id.human:
			vsComp = false; // vs human
			this.recreate();
			return (true);
		}
		return (super.onOptionsItemSelected(item));
	}
}
