import java.util.*;
import java.io.*;

class RiceChessboard {

    public int evaluate(int[][] board) {
	int rows = board.length;
	if (rows == 0) {
	    return 0;
	}
	int columns = board[0].length;
	if (columns == 0) {
	    return 0;
	}
	int[][] maxes = new int[rows][columns]; //stores maximum number of grains that can have been collected at each square by any path
	int total = 0;
	for (int a = 0; a < rows; a++) {
	    total += board[a][0];
	    maxes[a][0] = total; //only one path to the leftmost column
	}
	total = 0;
	for (int b = 0; b < columns; b++) {
	    total += board[0][b];
	    maxes[0][b] = total; //only one path to anywhere in the first row
	}
	for (int row = 1; row < rows; row++) {
	    for (int column = 1; column < columns; column++) {
		maxes[row][column] = board[row][column] + Math.max(maxes[row-1][column], maxes[row][column-1]);
	    }
	}
	return maxes[rows-1][columns-1];
    }

    /**
     * Accepts input from standard input and writes result to standard
     * output.
     *
     * The first two lines of input will be single numbers that define
     * the number of rows and columns. Each subsequent line will
     * represent one row of the chessboard, with the number of grains
     * of rice in each cell separated by spaces.
     *
     * For example the input:
     *
     * 4
     * 4
     * 2 2 4 2
     * 0 3 0 1
     * 1 2 2 1
     * 4 1 2 2
     *
     * Should print to standard output:
     *
     * 15
     */
    public static void main(String args[]) throws IOException {
	// You should not need to modify this method
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	int nrows = Integer.parseInt(reader.readLine());
	int ncols = Integer.parseInt(reader.readLine());
        
	int[][] board = new int[nrows][];
	for (int row = 0 ; row < nrows ; row++) {
	    board[row] = new int[ncols];
	    String line = reader.readLine();
	    String[] split = line.split(" ");
	    for (int col = 0 ; col < ncols ; col++) {
		board[row][col] = Integer.parseInt(split[col]);
	    }
	}

	int result = new RiceChessboard().evaluate(board);
	System.out.println(result);
    }

}