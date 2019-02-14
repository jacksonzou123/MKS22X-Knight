public class KnightBoard{
  private int[][] Board;
  private int rows;
  private int cols;

  public KnightBoard(int startingRows, int startingCols) {
    if (startingRows < 1 || startingCols < 1) {
      throw new IllegalArgumentException();
    }
    Board = new int[startingRows][startingCols];
    rows = startingRows;
    cols = startingCols;
  }

  public String toString() {
    boolean large = false;
    String f = "";
    if (rows * cols >= 10) {
      large = true;
    }
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (large && Board[i][j] < 10 && Board[i][j] > 0) {
          f += "_" + Board[i][j] + " ";
        }
        else {
          f += Board[i][j] + " ";
        }
      }
      f += "\n";
    }
    return f;
  }

  public boolean solve(int startingRow, int startingCol) {
    if (startingRow >= rows || startingRow < 0 || startingCol < 0 || startingCol >= cols) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (Board[i][j] != 0) {
          throw new IllegalStateException();
        }
      }
    }
    Board[startingRow][startingCol] = 1;
    return solveH(startingRow,startingCol, 2);
  }

  private boolean solveH(int row, int col, int level) {
    if (level > rows * cols) {
      return true;
    }
    if (addKnight(row,col,level)) {
      return solveH()
    }
  }

  private boolean addKnight(int row, int col, int level) {
    if (row == rows || row < 0 || col == cols || cols < 0) {
      return false;
    }
    if (Board[row][col] != 0) {
      return false;
    }
    Board[row][col] = level;
    return true;
  }

  private boolean removeKnight(int row, int col) {
    Board[row][col] = 0;
    return true;
  }
}
