class Solver {
	public static void main(String[] args) {
		int grid[][] = { { 5, 3, -1, -1, 7, -1, -1, -1, -1 }, { 6, -1, -1, 1, 9, 5, -1, -1, -1 },
				{ -1, 9, 8, -1, -1, -1, -1, 6, -1 }, { 8, -1, -1, -1, 6, -1, -1, -1, 3 }, { 4, -1, -1, 8, -1, 3, -1, -1, 1 },
				{ 7, -1, -1, -1, 2, -1, -1, -1, 6 }, { -1, 6, -1, -1, -1, -1, 2, 8, -1 }, { -1, -1, -1, 4, 1, 9, -1, -1, 5 },
				{ -1, -1, -1, -1, 8, -1, -1, 7, 9 } };
		grid = solve(grid);
		if (grid[0][0] == -1)
			System.out.println("No solution");
		else
			printArray(grid);
	}

	private static void printArray(int grid[][]) {
		for (int row[] : grid) {
			for (int col : row) {
				System.out.print(col + " ");
			}
			System.out.println("");
		}
	}

	private static int[][] solve(int grid[][]) {
		if (isCompleted(grid))
			return grid;
		Point open = openBlock(grid);
		for (int i = 1; i <= 9; i++) {
			if (isValid(grid, open, i)) {
				grid[open.row][open.col] = i;
				int newGrid[][] = solve(grid);
				if (newGrid[0][0] != -1)
					return newGrid;
				grid[open.row][open.col] = -1;
			}
		}
		return new int[][] { { -1 }, { -1 } };
	}

	private static boolean isValid(int grid[][], Point pos, int digit) {
		Point gridStart = startOfGrid(grid, pos);
		// check 9x9
		for (int row = gridStart.row; row < gridStart.row + 3; row++) {
			for (int col = gridStart.col; col < gridStart.col + 3; col++) {
				if (row == pos.row && col == pos.col)
					continue;
				if (grid[row][col] == digit)
					return false;
			}
		}

		// check row
		for (int col = 0; col < 9; col++) {
			if (col == pos.col)
				continue;
			if (grid[pos.row][col] == digit)
				return false;
		}

		// check col
		for (int row = 0; row < 9; row++) {
			if (row == pos.row)
				continue;
			if (grid[row][pos.col] == digit)
				return false;
		}
		return true;
	}

	private static Point startOfGrid(int grid[][], Point pos) {
		return new Point(pos.row - (pos.row % 3), pos.col - (pos.col % 3));
	}

	private static Point openBlock(int grid[][]) {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (grid[r][c] == -1)
					return new Point(r, c);
			}
		}
		return new Point(-1, -1);
	}

	private static boolean isCompleted(int grid[][]) {
		for (int row[] : grid) {
			for (int col : row) {
				if (col == -1)
					return false;
			}
		}
		return true;
	}
}

class Point {
	public int row, col;

	Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
}