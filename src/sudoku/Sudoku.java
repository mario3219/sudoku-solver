package sudoku;

import java.util.Arrays;

public class Sudoku implements SudokuSolver {

	private int[][] board;
	private final int GRID_SIZE = 9;
	private boolean solved = false;

	/**
	 * Creates a sudoku object with an empty board
	 */
	public Sudoku() {
		board = new int[GRID_SIZE][GRID_SIZE];
	}

	/**
	 * Solves the sudoku with one of potentially many solutions
	 * 
	 *  @return whether the board was solved or not.
	 */

	@Override
	public boolean solve() {
		solved = false;
		return solve(0, 0);
	}

	private boolean solve(int r, int c) {
		if (r == 9 || solved == true) {
			solved = true;
			return true;
		} else if (get(r, c) != 0) {
			if (c < 8) {
				return solve(r, c + 1);
			} else {
				return solve(r + 1, 0);
			}
		} else {
			for (int value = 1; value <= GRID_SIZE; value++) {
				add(r, c, value);
				if (isValid()) {
					if (c < 8) {
						if (solve(r, c + 1)) {
							return true;
						} else {
							remove(r, c);
						}
					} else {
						if (solve(r + 1, 0)) {
							return true;
						} else {
							remove(r, c);
						}
					}
				} else {
					remove(r, c);
				}
			}
			return false;
		}
	}

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	@Override
	public void add(int row, int col, int digit) {
		if (col > GRID_SIZE - 1 || row > GRID_SIZE - 1 || col < 0 || row < 0 || digit > GRID_SIZE || digit < 0) {
			throw new IllegalArgumentException();
		}
		board[row][col] = digit;

	}

	/**
	 * Removes the value on the specified square
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IllegalArgumentException if row, col is outside the range [0..9]
	 */
	@Override
	public void remove(int row, int col) {
		if (col > GRID_SIZE - 1 || row > GRID_SIZE - 1 || col < 0 || row < 0) {
			throw new IllegalArgumentException();
		}
		board[row][col] = 0;

	}

	/**
	 * Returns the value on the specified square
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IllegalArgumentException if row, col is outside the range [0..9]
	 */
	@Override
	public int get(int row, int col) {
		if (col > GRID_SIZE - 1 || row > GRID_SIZE - 1 || col < 0 || row < 0) {
			throw new IllegalArgumentException();
		}
		return board[row][col];
	}

	/**
	 * Checks that all filled in digits follows the sudoku rules.
	 * 
	 * @return whether the digits follows the sudoku rules.
	 */
	@Override
	public boolean isValid() {
		return areRowsValid() && areColsValid() && areBoxesValid();

	}


	private boolean areRowsValid() {

		boolean[] rowTracker = new boolean[GRID_SIZE + 1];
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (get(r, c) != 0 && !rowTracker[get(r, c)]) {
					rowTracker[get(r, c)] = true;
				} else if (get(r, c) != 0) {
					return false;
				}
			}
			Arrays.fill(rowTracker, false);
		}
		return true;
	}

	private boolean areColsValid() {

		boolean[] colTracker = new boolean[GRID_SIZE + 1];
		for (int c = 0; c < board[0].length; c++) {
			for (int r = 0; r < board.length; r++) {
				if (get(r, c) != 0 && !colTracker[get(r, c)]) {
					colTracker[get(r, c)] = true;
				} else if (get(r, c) != 0) {
					return false;
				}
			}
			Arrays.fill(colTracker, false);
		}
		return true;
	}

	private boolean areBoxesValid() {
		boolean[] boxTracker = new boolean[GRID_SIZE + 1];
		int localBoxRow = 0;
		int localBoxCol = 0;

		for (localBoxRow = 0; localBoxRow < 9; localBoxRow += 3) {
			for (localBoxCol = 0; localBoxCol < 9; localBoxCol += 3) {

				for (int lr = localBoxRow; lr < localBoxRow + 3; lr++) {
					for (int lc = localBoxCol; lc < localBoxCol + 3; lc++) {
						if (get(lr, lc) != 0 && !boxTracker[get(lr, lc)]) {
							boxTracker[get(lr, lc)] = true;
						} else if (get(lr, lc) != 0) {
							return false;
						}
					}
				}
				Arrays.fill(boxTracker, false);
			}
		}

		return true;
	}

	/**
	 * Clears the sudoku board
	 */
	@Override
	public void clear() {
		for (int[] row : board)
			Arrays.fill(row, 0);
	}

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	@Override
	public void setMatrix(int[][] m) {
		if (m.length != GRID_SIZE || m[0].length != GRID_SIZE) {
			throw new IllegalArgumentException();
		}
		for (int r = 0; r < GRID_SIZE; r++) {
			for (int c = 0; c < GRID_SIZE; c++) {
				if (m[r][c] < 0 || m[r][c] > 9) {
					throw new IllegalArgumentException();
				}
			}
		}
		board = m;

	}

	/**
	 * Retrieves the matrix from the sudoku
	 * 
	 * @return the int-matrix from the sudoku.
	 */
	@Override
	public int[][] getMatrix() {
		return board;
	}

}
