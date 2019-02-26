public class KnightBoard{
  private int[][] Board;
  private int rows;
  private int cols;
  private int[] moves;

  public KnightBoard(int startingRows, int startingCols) {
    if (startingRows < 1 || startingCols < 1) {
      throw new IllegalArgumentException();
    }
    Board = new int[startingRows][startingCols];
    rows = startingRows;
    cols = startingCols;
    moves = new int[] {-2,-1,-2,1,-1,2,1,2,2,1,2,-1,1,-2,-1,-2};
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
          f += " " + Board[i][j] + " ";
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
    if (level > rows * cols) {
      return true;
    }
    if (addKnight(row,col,level)) {
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
    if (row >= rows || row < 0 || col >= cols || col < 0) {
      return false;
    }
    Board[row][col] = 0;
    return true;
  }

  public int countSolutions(int startingRow, int startingCol) {
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
    return countH(startingRow,startingCol,1);
  }

  private int countH(int row, int col, int level) {
    int f = 0;
    if (level == rows * cols) {
      return 1;
    }
    addKnight(row, col, level);
    for (int i = 0; i < moves.length; i += 2) {
      if (row + moves[i] < rows && row + moves[i] >= 0 &&
          col + moves[i + 1] < cols && col + moves[i + 1] >= 0 &&
          Board[row + moves[i]][col + moves[i + 1]] == 0) {
        f += countH(row + moves[i], col + moves[i+1], level + 1);
      }

    }
    removeKnight(row, col);
    return f;
  }

}
