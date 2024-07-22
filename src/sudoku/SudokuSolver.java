package sudoku;

public interface SudokuSolver {
	/**
	 * Solves the sudoku with one of potentially many solutions
	 * 
	 *  @return whether the board was solved or not.
	 */

	boolean solve();

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	void add(int row, int col, int digit);

	/**
	 * Removes the value on the specified square
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IllegalArgumentException if row, col is outside the range [0..9]
	 */
	void remove(int row, int col);

	/**
	 * Returns the value on the specified square
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IllegalArgumentException if row, col is outside the range [0..9]
	 */
	int get(int row, int col);

	/**
	 * Checks that all filled in digits follows the sudoku rules.
	 * 
	 * @return whether the digits follows the sudoku rules.
	 */
	boolean isValid();

	/**
	 * Clears the sudoku board
	 */
	void clear();

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	void setMatrix(int[][] m);

	/**
	 * Retrieves the matrix from the sudoku
	 * 
	 * @return the int-matrix from the sudoku.
	 */
	int[][] getMatrix();
}