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
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        Board[i][j] = -1
      }
    }
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
          f += '_' + Board[i][j] + ' ';
        }
        else {
          f += Board[i][j] + ' ';
        }
      }
      f += "\n";
    }
    return f;
  }
}
