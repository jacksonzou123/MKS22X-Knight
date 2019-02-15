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
        if (large && Board[i][j] < 10) {
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
    return solveH(startingRow,startingCol,1);
  }

  private boolean solveH(int row, int col, int level) {
    System.out.println(toString());
    if (level > rows * cols) {
      return true;
    }
    if (addKnight(row,col,level)) {
      //System.out.println(this);
      if (solveH(row-2,col-1,level+1) ||
          solveH(row-2,col+1,level+1) ||
          solveH(row-1,col+2,level+1) ||
          solveH(row+1,col+2,level+1) ||
          solveH(row+2,col+1,level+1) ||
          solveH(row+2,col-1,level+1) ||
          solveH(row+1,col-2,level+1) ||
          solveH(row-1,col-2,level+1) ) {
        return true;
      }
      else {
        removeKnight(row,col);
      }
    }
    return false;
  }

  private boolean addKnight(int row, int col, int level) {
    if (row >= rows || row < 0 || col >= cols || col < 0) {
      return false;
    }
    if (Board[row][col] == 0) {
      Board[row][col] = level;
      return true;
    }
    return false;
  }

  private boolean removeKnight(int row, int col) {
    Board[row][col] = 0;
    return true;
  }
}
