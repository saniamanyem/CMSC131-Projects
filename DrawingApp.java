package app;

import java.util.Random;

public class DrawingApp {

	/**
	 * returns a String diagram of a rectangle made up of a given symbol with
	 * maxRows rows and maxCols columns. checks if the rows and columns passed in
	 * are greater than 1.
	 */
	public static String getRectangle(int maxRows, int maxCols, char symbol) {

		// checks that rows and cols are both more than 1
		if (maxRows < 1 || maxCols < 1) {
			return null;
		}

		// checks that all colors passed in are valid
		if (!isValidColor(symbol)) {
			return null;
		}

		String diagram = "";

		for (int rows = 0; rows < maxRows; rows++) {
			for (int cols = 0; cols < maxCols; cols++) {
				diagram += symbol;
			}
			// only adds a new line if the matrix isn't on the last line
			// to avoid the extra line at the bottom
			if (rows != maxRows - 1) {
				diagram += "\n";
			}
		}

		return diagram;
	}

	public static String getFlag(int size, char color1, char color2, char color3) {

		// as per specs, checks that size is greater than 3
		if (size < 3) {
			return null;
		}

		// checks that all colors passed in are valid
		if (!isValidColor(color1) || !isValidColor(color2) || !isValidColor(color3)) {
			return null;
		}

		String diagram = "";

		// set variables for rows and columns
		int numRows = size * 2;
		int numCols = size * 5;

		for (int rows = 0; rows < numRows; rows++) {

			int triangleWidth = 0;
			// adjusts triangle width depending
			// on if we are in the top of bottom triangle
			if (rows < numRows / 2) {
				triangleWidth = rows + 1;
			} else {
				triangleWidth = numRows - rows;
			}

			// checks where we are in the flag
			// middle or bottom or top triangle
			boolean firstOrLast = (rows == 0) || (rows == numRows - 1);
			boolean centerTop = (rows == numRows / 2 - 1);
			boolean centerBot = (rows == numRows / 2);

			char useChar;

			String row = "";

			// decides which character to use based on where we are
			if (firstOrLast || centerTop || centerBot) {
				useChar = color2;
			} else {
				useChar = color3;
			}

			// row for triangle
			for (int i = 0; i < triangleWidth; i++) {
				row += color1;
			}

			// fills other side of triangle based on where we are
			for (int i = triangleWidth; i < numCols; i++) {
				row += useChar;
			}

			// add to final diagram
			diagram += row;

			// only adds new line if we are not on the last line of the diagram
			if (rows < numRows - 1) {
				diagram += "\n";
			}
		}

		return diagram;

	}

	/**
	 * returns a String diagram of a rectangle with horizontal bars that correspond
	 * to the passed in bars value. the size of each bar is the number of rows
	 * divided by the number of bars. each bar is a different character.
	 */

	public static String getHorizontalBars(int maxRows, int maxCols, int bars, char color1, char color2, char color3) {

		// checks validity of bars, maxRows, and maxCols
		if (bars <= 0 || maxRows <= 0 || maxCols <= 0) {
			return null;
		}

		// checks that all colors passed in are valid
		if (!isValidColor(color1) || !isValidColor(color2) || !isValidColor(color3)) {
			return null;
		}

		// rows in each bar from maxRows / bars
		int rowsPerBar = maxRows / bars;

		// checks that there are more than 1
		// row in each bar
		if (rowsPerBar < 1) {
			return null;
		}

		String diagram = "";

		// loop through to find current color
		for (int i = 0; i < bars; i++) {
			char currentColor;

			// based on which bar we are on
			currentColor = pickColor(color1, color2, color3, i);

			// create each bar
			for (int r = 0; r < rowsPerBar; r++) {
				for (int c = 0; c < maxCols; c++) {
					diagram += currentColor;
				}
				// add newline unless it’s the very last line
				if (!(i == bars - 1 && r == rowsPerBar - 1)) {
					diagram += "\n";
				}
			}
		}

		return diagram;
	}

	/**
	 * returns a String diagram of a rectangle with vertical bars that correspond to
	 * the passed in bars value. the size of each bar is the number of columns
	 * divided by the number of bars. each bar is a different character.
	 */
	public static String getVerticalBars(int maxRows, int maxCols, int bars, char color1, char color2, char color3) {
		// checks validity of bars, maxRows, maxCols
		if (bars <= 0 || maxRows <= 0 || maxCols <= 0) {
			return null;
		}

		// checks validity of each color passed in
		if (!isValidColor(color1) || !isValidColor(color2) || !isValidColor(color3)) {
			return null;
		}

		// get number of columns per bar
		int colsPerBar = maxCols / bars;

		// checks that there is more than
		// one column in each bar
		if (colsPerBar < 1) {
			return null;
		}

		String diagram = "";

		for (int rows = 0; rows < maxRows; rows++) {
			// in each bar
			for (int i = 0; i < bars; i++) {
				// loop through columns
				for (int cols = 0; cols < colsPerBar; cols++) {
					char currentColor;

					// choose color based on which bar we're on
					currentColor = pickColor(color1, color2, color3, i);

					diagram += currentColor;

				}

			}

			// add new line unless we're at the last line
			if (rows != maxRows - 1) {
				diagram += "\n";
			}

		}
		return diagram;
	}

	/**
	 * returns a char value based on a randomly generated number from 0 - 5.
	 */
	public static char getRandomColor(Random random) {
		int rand = random.nextInt(6);

		if (rand == 0) {
			return 'R';
		} else if (rand == 1) {
			return 'G';
		} else if (rand == 2) {
			return 'B';
		} else if (rand == 3) {
			return 'Y';
		} else if (rand == 4) {
			return '*';
		} else if (rand == 5) {
			return '.';
		} else {
			return 'X';
		}
	}

	// returns true or false based on whether the passed
	// in char corresponds with a color character
	private static boolean isValidColor(char color) {
		if (color == 'R' || color == 'G' || color == 'B' 
			|| color == 'Y' || color == '.' || color == '*') {
			return true;
		}

		return false;
	}

	private static char pickColor(char color1, char color2, char color3, int index) {
		char currentColor;
		if (index % 3 == 0) {
			currentColor = color1;
		} else if (index % 3 == 1) {
			currentColor = color2;
		} else {
			currentColor = color3;
		}

		return currentColor;
	}
}