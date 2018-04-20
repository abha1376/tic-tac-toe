package lecture26;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTT extends JFrame implements ActionListener {
	public static final int BOARD_SIZE = 3;

	public static enum gameStatus {
		Xwins, Zwins, Incomplete, Tie;
	}

	JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
	boolean crossTurn = true;

	TTT() {
		super.setTitle("TIC TAC TOE");
		super.setSize(800, 800);
		GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);
		Font font = new Font("Comic Sans", 1, 150);
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				JButton button = new JButton("");
				buttons[i][j] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
		}
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		makeMove(button);
		gameStatus gs = this.getGameStatus();
		if (gs == gameStatus.Incomplete) {
			return;
		}

		declareWinner(gs);
		int choice = JOptionPane.showConfirmDialog(this, "do you want to play another game");
		if (choice == JOptionPane.YES_OPTION) {
			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {
					buttons[i][j].setText("");
				}
			}
			crossTurn = true;
		} else {
			super.dispose();
		}

	}

	private void makeMove(JButton button) {
		String text = button.getText();
		if (text.length() > 0) {
			JOptionPane.showMessageDialog(this, "********Invalid Move!!!!!");
			return;
		}
		if (crossTurn) {
			button.setText("X");

		} else {
			button.setText("0");
		}
		crossTurn = !crossTurn;
	}

	private gameStatus getGameStatus() {
		String text1 = "", text2 = "";
		int row, col;
		// case 1
		row = 0;
		col = 0;
		while (row < BOARD_SIZE) {
			col = 0;
			while (col < BOARD_SIZE - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row][col + 1].getText();
				if (!text1.equals(text2) || text1.length() == 0 || text2.length() == 0) {
					break;
				}
				col++;
			}
			if (col == BOARD_SIZE - 1) {
				if (text1.equals("X")) {
					return gameStatus.Xwins;
				} else {
					return gameStatus.Zwins;
				}
			}
			row++;
		}
		col = 0;
		row = 0;
		while (col < BOARD_SIZE) {
			row = 0;
			while (row < BOARD_SIZE - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row + 1][col].getText();
				if (!text1.equals(text2) || text1.length() == 0 || text2.length() == 0) {
					break;
				}
				row++;
			}
			if (row == BOARD_SIZE - 1) {
				if (text1.equals("X")) {
					return gameStatus.Xwins;
				} else {
					return gameStatus.Zwins;
				}
			}
			col++;
		}
		// case3:diagnol
		row = 0;
		col = 0;
		while (row < BOARD_SIZE - 1) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row + 1][col + 1].getText();
			if (!text1.equals(text2) || text1.length() == 0 || text2.length() == 0) {
				break;
			}
			col++;
			row++;
		}
		if (row == BOARD_SIZE - 1) {
			if (text1.equals("X")) {
				return gameStatus.Xwins;
			} else {
				return gameStatus.Zwins;
			}
		}

		// case 4:
		row = BOARD_SIZE - 1;
		col = 0;
		while (row > 0) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row - 1][col + 1].getText();
			if (!text1.equals(text2) || text1.length() == 0 || text2.length() == 0) {
				break;
			}
			col++;
			row--;
		}
		if (row == 0) {
			if (text1.equals("X")) {
				return gameStatus.Xwins;
			} else {
				return gameStatus.Zwins;
			}
		}
		// case 5:
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				String text = buttons[i][j].getText();
				if (text.length() == 0) {
					return gameStatus.Incomplete;
				}
			}
		}
		return gameStatus.Tie;

	}

	private void declareWinner(gameStatus gs) {
		if (gs == gameStatus.Xwins) {
			JOptionPane.showMessageDialog(this, "X wins");
		} else if (gs == gameStatus.Zwins) {
			JOptionPane.showMessageDialog(this, "Z wins");
		} else {
			JOptionPane.showMessageDialog(this, "TIE :-(");
		}
	}
}
