package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudokuGUI {
	
	public SudokuGUI(Sudoku sudoku) {
		SwingUtilities.invokeLater(() -> createWindow(sudoku, "Sudoku", 750, 750));
	}

	private void createWindow(Sudoku sudoku, String title, int width, int height) {
		//skapar fönster
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		JPanel panel = new JPanel();
		JPanel gridPanel = new JPanel();
		Font bigFont;
		
		//sudoku fönstret
		GridLayout sudokuGrid = new GridLayout(9, 9);
		JTextField[][] layout = new JTextField[9][9];
		for (int r = 0; 9 > r; r++) {
			for (int c = 0; 9 > c; c++) {
				JTextField field = new JTextField(1);
				bigFont = field.getFont().deriveFont(Font.PLAIN, 50f);
				field.setFont(bigFont);
				layout[r][c] = field;
				field.addKeyListener(new KeyAdapter() {
					
			         public void keyPressed(KeyEvent ke) {
			            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
			               field.setEditable(true);

			            } else {
			               field.setEditable(false);
			            }
			         }
			      });
			}
		}
		setColorBox(0, 0, layout);
		setColorBox(6, 0, layout);
		setColorBox(0, 6, layout);
		setColorBox(3, 3, layout);
		setColorBox(6, 6, layout);
		gridPanel.setLayout(sudokuGrid);
		for (int r = 0; 9 > r; r++) {
			for (int c = 0; 9 > c; c++) {
				gridPanel.add(layout[r][c]);
			}
		}
		
		//knappen solve
		JButton solve = new JButton("Solve");
		bigFont = solve.getFont().deriveFont(Font.PLAIN, 50f);
		solve.setFont(bigFont);
		solve.addActionListener(e -> {
			int[][] matris = new int[9][9];
			for (int r = 0; 9 > r; r++) {
				for (int c = 0; 9 > c; c++) {
					String value = layout[r][c].getText().replaceAll("[^\\d.]", "");
					if (value.equals("")) {
						value = "0";
					}
					matris[r][c] = Integer.parseInt(value);
				}
			}

			try {
				sudoku.setMatrix(matris);
			} catch (IllegalArgumentException n) {
				JOptionPane.showMessageDialog(null, "Non valid digits", "", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
				boolean solved = sudoku.solve();
				if (solved) {
					matris = sudoku.getMatrix();
					for (int r = 0; 9 > r; r++) { 
						for (int c = 0; 9 > c; c++) {
							layout[r][c].setText(String.valueOf(matris[r][c]));
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "No solution found", "", JOptionPane.INFORMATION_MESSAGE);
				}
			
		});
		panel.add(solve);
		
		//knappen clear
		JButton clear = new JButton("Clear");
		bigFont = clear.getFont().deriveFont(Font.PLAIN, 50f);
		clear.setFont(bigFont);
		clear.addActionListener(e -> {
			for (int r = 0; 9 > r; r++) {
				for (int c = 0; 9 > c; c++) {
					layout[r][c].setText("");
				}
			}
		});
		panel.add(clear);
		
		//lägger till alla skapade komponenter till fönstret
		pane.add(panel, BorderLayout.SOUTH);
		pane.add(gridPanel, BorderLayout.NORTH);
		
		frame.pack();
		frame.setSize(width, height);
		frame.setVisible(true);
		
		// Fyller fönstret med sudokuts bräde
		int[][] matris = sudoku.getMatrix();
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				if((matris[r][c]) != 0) {
					layout[r][c].setText(String.valueOf(matris[r][c]));
				}
				
			}
		}
		
	}
	//java
	private void setColorBox(int r, int c, JTextField[][] layout) {
		for (int i = 0; 3 > i; i++) {
			for (int n = 0; 3 > n; n++) {
				layout[r + i][c + n].setBackground(Color.ORANGE);
			}
		}
	}
	
}
