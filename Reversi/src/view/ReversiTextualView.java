package view;

//some import and package info

import java.io.IOException;

import model.CubeCoordinate;
import model.HexagonReversiModel;
import model.ICell;
import model.Marble;
import model.MarbleColor;

/**
 * Represents a textual view of the reversi game.
 */
public class ReversiTextualView implements IView {
  private final HexagonReversiModel model;
  private final Appendable a;

  /**
   * Constructs a View for the Reversi Game with Appendable field with given value.
   */
  public ReversiTextualView(HexagonReversiModel model, Appendable a) {
    this.model = model;
    this.a = a;
  }

  /**
   * Constructs a View for the Reversi Game and Appendable value as new StringBuilder.
   */
  public ReversiTextualView(HexagonReversiModel model) {
    this.model = model;
    this.a = new StringBuilder();
  }

  //Get the appendable.
  private Appendable getAppendable() {
    return this.a;
  }

  /**
   * Renders the board and return the String text with use of String builder.
   *
   * @return the String Text for the board representation.
   */
  private String renderBoard() {

    //get the radius and outside loop time
    int radius = this.model.getRadius();
    int totalRows = 2 * radius - 1;

    StringBuilder boardStr = new StringBuilder();

    for (int rowIndex = 0; rowIndex < totalRows; rowIndex++) {

      // Calculate the leading spaces for the current row
      int spaces = 0;
      if (rowIndex < radius) {
        spaces = radius - 1 - rowIndex;
      } else {
        spaces = rowIndex - radius + 1;
      }

      //left spaces
      for (int i = 0; i < spaces; i++) {
        boardStr.append(" ");
      }


      int innerLoopNum = 0;
      //inner loop count
      if (rowIndex < radius) {
        innerLoopNum = radius + rowIndex;
      } else {
        innerLoopNum = radius * 3 - 2 - rowIndex;
      }


      int qValue;
      int rValue = rowIndex - radius + 1;
      int sValue;

      if (rowIndex < radius) {
        qValue = -rowIndex;
        sValue = -qValue - rValue;
      } else {
        qValue = -radius + 1;
        sValue = -qValue - rValue;
      }


      //print the middle cells
      for (int i = 0; i < innerLoopNum; i++) {
        CubeCoordinate coord = new CubeCoordinate(qValue + i, rValue, sValue - i);
        ICell cell = this.model.getCellAt(coord);
        if (cell != null) {
          if (cell.containsMarble()) {
            if (cell.sameMarble(new Marble(MarbleColor.WHITE))) {
              boardStr.append("O");
            } else {
              boardStr.append("X");
            }
          } else {
            boardStr.append("_");
          }
        } else {
          boardStr.append(("N"));
        }
        //empty space
        boardStr.append(" ");
      }
      //nextLine
      boardStr.append("\n");
    }
    return boardStr.toString();
  }

  /**
   * Returns the same String text the renderBoard function will return.
   */
  @Override
  public String toString() {
    return renderBoard();
  }

  /**
   * Try to render the model and append to the appendable field.
   */
  public void render() {
    try {
      this.a.append(this.renderBoard());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

