package model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

/**
 * A testing class to test methods relating to HexBoards
 */
public class testHexBoard {

  @Test(expected = Exception.class)
  public void testExceptionThrownWhenGameSizeIsTooSmall() {
    IBoard board = new HexBoard(2);
  }

  @Test
  public void testCorrectNumberOfTilesConstructedInSmallGame() {
    HexBoard board = new HexBoard(3);
    Map<CubeCoordinate, ICell> cellBoard = board.cellList;
    Assert.assertEquals(19, cellBoard.size());
  }

  @Test
  public void testCorrectNumberOfTilesConstructedInMediumGame() {
    HexBoard board = new HexBoard(4);
    Map<CubeCoordinate, ICell> cellBoard = board.cellList;
    Assert.assertEquals(37, cellBoard.size());
  }

  @Test
  public void testCorrectNumberOfTilesConstructedInLargeGame() {
    HexBoard board = new HexBoard(5);
    Map<CubeCoordinate, ICell> cellBoard = board.cellList;
    Assert.assertEquals(61, cellBoard.size());
  }

  @Test
  public void testCellHasCorrectCoordinatesLargeGame() {
    HexBoard board = new HexBoard(5);
    Map<CubeCoordinate, ICell> cellBoard = board.cellList;
    CubeCoordinate edgeCoordinate = new CubeCoordinate(4, -1, -3);
    HexagonCell edgeCell = new HexagonCell(edgeCoordinate, Optional.empty());
    Assert.assertEquals(edgeCell, board.getCellAt(edgeCoordinate));
  }

  @Test
  public void testCellHasCorrectCoordinatesMediumGame() {
    HexBoard board = new HexBoard(4);
    Map<CubeCoordinate, ICell> cellBoard = board.cellList;
    CubeCoordinate zeroCoordiante = new CubeCoordinate(0, 0, 0);
    HexagonCell zeroCell = new HexagonCell(zeroCoordiante, Optional.empty());
    Assert.assertEquals(zeroCell, board.getCellAt(zeroCoordiante));
  }

  @Test
  public void testCellHasCorrectCoordinatesSmallGame() {
    HexBoard board = new HexBoard(3);
    Map<CubeCoordinate, ICell> cellBoard = board.cellList;
    CubeCoordinate edgeCoordinate = new CubeCoordinate(2, -1, -1);
    HexagonCell edgeCell = new HexagonCell(edgeCoordinate, Optional.empty());
    Assert.assertEquals(edgeCell, board.getCellAt(edgeCoordinate));
  }

  @Test
  public void testGetCellAtOutOfBoundsIsNull() {
    HexBoard board = new HexBoard(4);
    Map<CubeCoordinate, ICell> cellBoard = board.cellList;
    CubeCoordinate oob = new CubeCoordinate(4, -1, -3);
    Assert.assertNull(board.getCellAt(oob));
  }
}
