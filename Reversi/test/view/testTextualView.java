package model;

import org.junit.Assert;
import org.junit.Test;

import view.ReversiTextualView;

/**
 * Create tests to test the ReversiTextualView class in the view package.
 */
public class testTextualView {

  StringBuilder board4 = new StringBuilder();
  StringBuilder board6 = new StringBuilder();

  StringBuilder movedBoard = new StringBuilder();


  // Test initial board render with radius 4
  @Test
  public void testInitialBoardRender4() {
    board4.append("    _ _ _ _\n");
    board4.append("   _ _ _ _ _\n");
    board4.append("  _ _ O X _ _\n");
    board4.append(" _ _ X _ O _ _\n");
    board4.append("  _ _ O X _ _\n");
    board4.append("   _ _ _ _ _\n");
    board4.append("    _ _ _ _\n");
    HexagonReversiModel model = new HexagonReversiModel(4);
    StringBuilder actual = new StringBuilder();
    ReversiTextualView view = new ReversiTextualView(model, actual);
    view.toString();
    Assert.assertEquals(view.toString(), board4.toString());
  }

  // Test initial board render with radius 6
  @Test
  public void testInitialBoardRenderR63() {
    board6.append("        _ _ _ _ _ _\n");
    board6.append("       _ _ _ _ _ _ _\n");
    board6.append("      _ _ _ _ _ _ _ _\n");
    board6.append("     _ _ _ _ _ _ _ _ _\n");
    board6.append("    _ _ _ _ O X _ _ _ _\n");
    board6.append("   _ _ _ _ X _ O _ _ _ _\n");
    board6.append("    _ _ _ _ O X _ _ _ _\n");
    board6.append("     _ _ _ _ _ _ _ _ _\n");
    board6.append("      _ _ _ _ _ _ _ _\n");
    board6.append("       _ _ _ _ _ _ _\n");
    board6.append("        _ _ _ _ _ _\n");
    HexagonReversiModel model = new HexagonReversiModel(6);
    StringBuilder actual = new StringBuilder();
    ReversiTextualView view = new ReversiTextualView(model, actual);
    view.toString();
    Assert.assertEquals(actual.toString(), board6.toString());
  }

  // Test initial board render with radius 6, marbles initialized
  @Test
  public void testInitialBoardRenderR62() {
    movedBoard.append("    _ _ _ _ _ _\n");
    movedBoard.append("   _ _ _ _ _ _ _\n");
    movedBoard.append("  _ _ _ _ X _ _ _\n");
    movedBoard.append(" _ _ _ _ X X _ _ _\n");
    movedBoard.append("_ _ _ _ O _ X _ _ _\n");
    movedBoard.append(" _ _ _ _ X O _ _ _\n");
    movedBoard.append("  _ _ _ _ _ _ _ _\n");
    movedBoard.append("   _ _ _ _ _ _ _\n");
    movedBoard.append("    _ _ _ _ _ _\n");
    HexagonReversiModel model = new HexagonReversiModel(6);
    StringBuilder actual = new StringBuilder();
    ReversiTextualView view = new ReversiTextualView(model, actual);
    view.toString();
    Assert.assertEquals(actual.toString(), board6.toString());
  }
}