package sudoku;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuTester {

	private Sudoku mySudo;


	
	@BeforeEach
	void setUp() throws Exception {
		int[][] board = {
		        {7, 0, 2, 0, 5, 0, 6, 0, 0},
		        {0, 0, 0, 0, 0, 3, 0, 0, 0},
		        {1, 0, 0, 0, 0, 9, 5, 0, 0},
		        {8, 0, 0, 0, 0, 0, 0, 9, 0},
		        {0, 4, 3, 0, 0, 0, 7, 5, 0},
		        {0, 9, 0, 0, 0, 0, 0, 0, 8},
		        {0, 0, 9, 7, 0, 0, 0, 0, 5},
		        {0, 0, 0, 2, 0, 0, 0, 0, 0},
		        {0, 0, 7, 0, 4, 0, 2, 0, 3} 
		      };
		mySudo = new Sudoku();
		mySudo.setMatrix(board);
	}

	@AfterEach
	void tearDown() throws Exception {
		mySudo = null;
		
	}
	
	
	@Test
	void testSetMatrixAndGetMatrix() {
		int[][] myMatrix = mySudo.getMatrix();
		int[][] myBoard = {  // Copied over matrix from "board"
		        {7, 0, 2, 0, 5, 0, 6, 0, 0},
		        {0, 0, 0, 0, 0, 3, 0, 0, 0},
		        {1, 0, 0, 0, 0, 9, 5, 0, 0},
		        {8, 0, 0, 0, 0, 0, 0, 9, 0},
		        {0, 4, 3, 0, 0, 0, 7, 5, 0},
		        {0, 9, 0, 0, 0, 0, 0, 0, 8},
		        {0, 0, 9, 7, 0, 0, 0, 0, 5},
		        {0, 0, 0, 2, 0, 0, 0, 0, 0},
		        {0, 0, 7, 0, 4, 0, 2, 0, 3} 
		      };
		
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				assertTrue(myMatrix[r][c] == myBoard[r][c]);
			}
		}
		
		int[][] myNegativeBoard = { 
		        {-1, 0, 2, 0, 5, 0, 6, 0, 0},
		        {0, 0, 0, 0, 0, 3, 0, 0, 0},
		        {1, 0, 0, 0, 0, 9, 5, 0, 0},
		        {8, 0, 0, 0, 0, 0, 0, 9, 0},
		        {0, 4, 3, 0, 0, 0, 7, 5, 0},
		        {0, 9, 0, 0, 0, 0, 0, 0, 8},
		        {0, 0, 9, 7, 0, 0, 0, 0, 5},
		        {0, 0, 0, 2, 0, 0, 0, 0, 0},
		        {0, 0, 7, 0, 4, 0, 2, 0, 3} 
		      };
		
		int[][] myLongBoard = {  // This matrix has 10 rows
		        {7, 0, 2, 0, 5, 0, 6, 0, 0},
		        {0, 0, 0, 0, 0, 3, 0, 0, 0},
		        {1, 0, 0, 0, 0, 9, 5, 0, 0},
		        {8, 0, 0, 0, 0, 0, 0, 9, 0},
		        {0, 4, 3, 0, 0, 0, 7, 5, 0},
		        {0, 9, 0, 0, 0, 0, 0, 0, 8},
		        {0, 0, 9, 7, 0, 0, 0, 0, 5},
		        {0, 0, 0, 2, 0, 0, 0, 0, 0},
		        {0, 0, 7, 0, 4, 0, 2, 0, 3},
		        {0, 0, 7, 0, 4, 0, 2, 3, 3} 
		      };
		
		assertThrows(IllegalArgumentException.class, () -> mySudo.setMatrix(myNegativeBoard));
		assertThrows(IllegalArgumentException.class, () -> mySudo.setMatrix(myLongBoard));
		
		
		
	}
	
	@Test
	void testAdd() {
		System.out.println("Brädet innan förändringar:");
		print2D(mySudo.getMatrix());
		mySudo.add(0, 1, 1);
		mySudo.add(0, 0, 0);
		mySudo.add(1, 1, 1);
		assertThrows(IllegalArgumentException.class, () -> mySudo.add(1, 1, -1)); // Ska inte kunna ändra till -1
		assertThrows(IllegalArgumentException.class, () -> mySudo.add(10, 1, 2)); // Rad 10 finns inte 
		assertThrows(IllegalArgumentException.class, () -> mySudo.add(4, -4, 2)); // Kolonn -4 finns inte

		
		
		System.out.println("(testAdd) Brädet efter addering:");
		print2D(mySudo.getMatrix());
	}
	
	@Test
	void testGet() {
		assertEquals(mySudo.get(0,0), 7);
		assertEquals(mySudo.get(0,1), 0);
		assertEquals(mySudo.get(1,1), 0);
		assertEquals(mySudo.get(7,8), 0);
		assertEquals(mySudo.get(8,8), 3);
		
		mySudo.add(7, 8, 4);
		assertEquals(mySudo.get(7,8), 4);
		
		
	}
	
	@Test
	void testClear() {
		mySudo.clear();
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				assertTrue(mySudo.get(r, c) == 0);
			}
		}
		
		mySudo.add(0, 0, 6);  // Kollar så att inte ränsningen rubbade insättning
		assertTrue(mySudo.get(0, 0) == 6);
		
	}

	@Test
	void testIsValidAndRemove() {
		assertTrue(mySudo.isValid()); // Kollar så att ett redan bekräftat valid sudoku bekräftas av metoden
		
		mySudo.add(1, 1, 2); // Kollar så att metoden upptäcker dubletter i rutorna
		assertFalse(mySudo.isValid());
		mySudo.remove(1, 1);
		
		
		mySudo.add(2, 2, 5); // Kollar så att metoden upptäcker dubletter i raderna
		assertFalse(mySudo.isValid());
		mySudo.remove(2, 2);
		
		mySudo.add(7, 7, 5); // Kollar så att metoden upptäcker dubletter i kolonnerna
		assertFalse(mySudo.isValid());
		mySudo.remove(7, 7);
		
		// Testar remove
		assertEquals(mySudo.get(3, 0), 8);
		mySudo.remove(3, 0);
		assertEquals(mySudo.get(3, 0), 0);

		
		
	}
	
	@Test
	void testSolve() {
		assertTrue(mySudo.solve());
		System.out.println("(testSolve) Brädet efter det är löst:");
		
		assertTrue(mySudo.isValid());
		print2D(mySudo.getMatrix()); // Här får man själv kolla så att det stämmer
		
		int[][] unsolvableBoard = { 		// Brädet är taget från internet och har inga lösningar
				{2, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 6, 0},
				{0, 0, 0, 0, 0, 1, 0, 0, 0},
				{5, 0, 2, 6, 0, 0, 4, 0, 7},
				{0, 0, 0, 0, 0, 4, 1, 0, 0},
				{0, 0, 0, 0, 9, 8, 0, 2, 3},
				{0, 0, 0, 0, 0, 3, 0, 8, 0},
				{0, 0, 5, 0, 1, 0, 0, 0, 0},
				{0, 0, 7, 0, 0, 0, 0, 0, 0},
		};
		mySudo.setMatrix(unsolvableBoard);
		assertFalse(mySudo.solve()); // Att exekvera detta tar rätt så lång tid eftersom det inte finns en lösning
		
	}
	
	public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat) {
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
        }
        System.out.println("");
    }
	
 
}


