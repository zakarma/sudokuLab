import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import gameBoards.SudokuGUI;

public class SudokuBoard extends Sudoku {
	// fields
	private SudokuGUI gui;
	private int[][] board;

	// constructor
	public SudokuBoard() {
		gui = new SudokuGUI();
		board = new int[9][9];
	}

	// methods
	public void loadPuzzle(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner s = new Scanner(file);
		int row = 0;
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.length() > 0) {
				int col = 0;
				for (int i = 0; i < line.length(); i++) {
					char c = line.charAt(i);
					if (c >= '0' && c <= '9') {
						board[row][col] = (int) (c - '0');
						gui.draw(board);
					}
					if (c != ' ') {
						col++;
					}
				}
				row++;
			}
		}
		s.close();

	}

	public boolean solvePuzzle() {
		gui.pause();
		return solvePuzzle(0, 0);
	}

	public boolean solvePuzzle(int row, int col) {
		gui.draw(board);

		if (row >= 9)
			return true;
		
		int nextRow = (row + (col + 1) / 9) % 9;
		int nextCol = (col + 1) % 9;

		if (board[row][col] != 0)
			return solvePuzzle(nextRow, nextCol);

		for (int number = 1; number <= 9; number++) {
            if (isValid(number, row, col)) {
                board[row][col] = number;
                gui.draw(board);
                if (solvePuzzle(nextRow, nextCol)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }

		return false;
	}

	public void drawPuzzle() {
		gui.draw(board, new Color(232, 232, 232));
	}

	public boolean isValid(int number, int row, int col) {
		for (int r = 0; r < 9; r++) {
            if (board[r][col] == number) {
                return false;
            }
        }
        
        for (int c = 0; c < 9; c++) {
            if (board[row][c] == number) {
                return false;
            }
        }
        
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[r][c] == number) {
                    return false;
                }
            }
        }

        return true;
	}
}
