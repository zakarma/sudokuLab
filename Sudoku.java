import java.awt.Color;
import java.io.FileNotFoundException;

import gameBoards.SudokuGUI;

public class Sudoku {

    public static void trySudokuGUI() {
        SudokuGUI gui = new SudokuGUI();
        int[][] board = new int [9][9];
        for(int i = 0; i < 81; i++) {
        	board[i/9][i%9] = (int)(Math.random()*10);
        	if(i == 40) {
        		gui.pause();
        	}
        	gui.draw(board);
        }
        gui.draw(board, new Color(232,232,232));
    }
    
    public static void main(String[] args) throws FileNotFoundException  {
        //trySudokuGUI();
    	SudokuBoard sb = new SudokuBoard();
    	sb.loadPuzzle("Sudoku2.txt");
    	sb.solvePuzzle();
    	if(sb.solvePuzzle()) System.out.println("Success");
    	System.out.println("Failure");
    	sb.drawPuzzle();
    }
}
