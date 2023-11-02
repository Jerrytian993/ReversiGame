package model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.player.Player;

public class testReversiModel {

  @Test
  public void testDirectionToNegative() {
    CubeCoordinate coordinate1 = new CubeCoordinate(-2, 1, 1);
    CubeCoordinate coordinate2 = new CubeCoordinate(-2, 0, 2);
    HexagonCell cell1 = new HexagonCell(coordinate1, Optional.empty());
    HexagonCell cell2 = new HexagonCell(coordinate2, Optional.empty());
    Assert.assertEquals(cell1.directionTo(cell2), new CubeCoordinate(0, -1, 1));
  }

  @Test
  public void testDirectionToPositive() {
    CubeCoordinate coordinate1 = new CubeCoordinate(-2, 1, 1);
    CubeCoordinate coordinate2 = new CubeCoordinate(-1, 0, 1);
    HexagonCell cell1 = new HexagonCell(coordinate1, Optional.empty());
    HexagonCell cell2 = new HexagonCell(coordinate2, Optional.empty());
    Assert.assertEquals(cell1.directionTo(cell2), new CubeCoordinate(1, -1, 0));
  }

  @Test
  public void testGetLegalMovesPlayerOneStart() {
    HexBoard board = new HexBoard(4);
    List<ICell> legalMovesExpected = new ArrayList<ICell>();
    legalMovesExpected.add(new HexagonCell(new CubeCoordinate(1, -2, 1), Optional.empty()));
    legalMovesExpected.add(new HexagonCell(new CubeCoordinate(-1, -1, 2), Optional.empty()));
    legalMovesExpected.add(new HexagonCell(new CubeCoordinate(2, -1, -1), Optional.empty()));
    legalMovesExpected.add(new HexagonCell(new CubeCoordinate(-2, 1, 1), Optional.empty()));
    legalMovesExpected.add(new HexagonCell(new CubeCoordinate(1, 1, -2), Optional.empty()));
    legalMovesExpected.add(new HexagonCell(new CubeCoordinate(-1, 2, -1), Optional.empty()));

    List<ICell> legalMovesActual = board.getLegalMoves(new Player(MarbleColor.WHITE));
    for (ICell cell : legalMovesExpected) {
      Assert.assertTrue(legalMovesActual.contains(cell));
    }
  }

  @Test
  public void testGetLegalMovesPlayerTwoStart() {
    HexBoard board = new HexBoard(4);
    List<ICell> legalMovesExpected = new ArrayList<ICell>();
    legalMovesExpected.add(new HexagonCell(new CubeCoordinate(1, -2, 1), Optional.empty()));
    legalMovesExpected.add(new HexagonCell(new CubeCoordinate(-1, -1, 2), Optional.empty()));
    legalMovesExpected.add(new HexagonCell(new CubeCoordinate(2, -1, -1), Optional.empty()));
    legalMovesExpected.add(new HexagonCell(new CubeCoordinate(-2, 1, 1), Optional.empty()));
    legalMovesExpected.add(new HexagonCell(new CubeCoordinate(1, 1, -2), Optional.empty()));
    legalMovesExpected.add(new HexagonCell(new CubeCoordinate(-1, 2, -1), Optional.empty()));

    List<ICell> legalMovesActual = board.getLegalMoves(new Player(MarbleColor.BLACK));
    for (ICell cell : legalMovesExpected) {
      Assert.assertTrue(legalMovesActual.contains(cell));
    }
  }

  @Test
  public void testGetAdjacentCoordinateDirections() {
    HexBoard board = new HexBoard(4);
    ICell cell = new HexagonCell(new CubeCoordinate(1, -2, 1), Optional.empty());
    List<ICell> adjacentCells = board.getAdjacentCells(board.findCoordinateForCell(cell));
    Player p = new Player(MarbleColor.WHITE);
    List<CubeCoordinate> expectedList = new ArrayList<>();
    CubeCoordinate expected = new CubeCoordinate(0, 1, -1);
    expectedList.add(expected);
    Assert.assertEquals(expectedList,
            board.getAdjacentCoordinateDirections(adjacentCells, p, cell));
  }

  @Test
  public void testExplorePaths() {
    HexBoard board = new HexBoard(4);
    ICell origin1 = new HexagonCell(new CubeCoordinate(1, -2, 1), Optional.empty());
    CubeCoordinate origin = new CubeCoordinate(1, -2, 1);
    Player p = new Player(MarbleColor.WHITE);
    List<CubeCoordinate> adjacentCoordDirections =
            board.getAdjacentCoordinateDirections(board.getAdjacentCells(origin), p, origin1);
    List<ICell> expectedList = new ArrayList<>();
    CubeCoordinate expectedCoord = new CubeCoordinate(1, -1, 0);
    ICell expectedCell = new HexagonCell(expectedCoord, Optional.of(new Marble(MarbleColor.BLACK)));
    expectedList.add(expectedCell);
    Assert.assertEquals(expectedList, board.explorePaths(origin, adjacentCoordDirections, p));
  }

  @Test
  public void testBasicMoveWhite() {
    HexagonReversiModel model = new HexagonReversiModel(4);
    Player player1 = new Player(MarbleColor.WHITE);
    CubeCoordinate coordinate = new CubeCoordinate(1, -2, 1);
    CubeCoordinate coordinateBlackCell = new CubeCoordinate(1, -1, 0);
    ICell cell = new HexagonCell(coordinate, Optional.empty());
    ICell blackCell = new HexagonCell(coordinateBlackCell,
            Optional.of(new Marble(MarbleColor.BLACK)));
    ICell flippedWhiteCell = new HexagonCell(coordinateBlackCell,
            Optional.of(new Marble(MarbleColor.WHITE)));
    ICell whiteCell = new HexagonCell(coordinate,
            Optional.of(new Marble(MarbleColor.WHITE)));
    Assert.assertEquals(blackCell,
            model.getCellAt(coordinateBlackCell));
    model.placeMarble(cell, player1);
    Assert.assertEquals(flippedWhiteCell,
            model.getCellAt(coordinateBlackCell));
    Assert.assertEquals(whiteCell,
            model.getCellAt(coordinate));
  }

  @Test
  public void testBasicMoveBlack() {
    HexagonReversiModel model = new HexagonReversiModel(4);
    Player player1 = new Player(MarbleColor.WHITE);
    Player player2 = new Player(MarbleColor.BLACK);
    Referee ref = new Referee(player1, player2);
    ref.switchTurn();
    CubeCoordinate placeOnCoordinate = new CubeCoordinate(1, -2, 1);
    CubeCoordinate coordinateWhiteCell = new CubeCoordinate(0, -1, 1);
    ICell placeCell = new HexagonCell(placeOnCoordinate, Optional.empty());
    ICell whiteCell = new HexagonCell(coordinateWhiteCell,
            Optional.of(new Marble(MarbleColor.WHITE)));
    ICell flippedBlackCell = new HexagonCell(coordinateWhiteCell,
            Optional.of(new Marble(MarbleColor.BLACK)));
    ICell blackCell = new HexagonCell(placeOnCoordinate,
            Optional.of(new Marble(MarbleColor.BLACK)));
    Assert.assertEquals(whiteCell,
            model.getCellAt(coordinateWhiteCell));
    model.placeMarble(placeCell, player2);
    Assert.assertEquals(flippedBlackCell,
            model.getCellAt(coordinateWhiteCell));
    Assert.assertEquals(blackCell,
            model.getCellAt(placeOnCoordinate));
  }

  @Test
  public void testSwitchTurn() {
    HexagonReversiModel model = new HexagonReversiModel(4);
    Player player1 = new Player(MarbleColor.WHITE);
    Player player2 = new Player(MarbleColor.BLACK);
    Referee ref = new Referee(player1, player2);
    Assert.assertEquals(ref.turn.getFirst(), player1);
    ref.switchTurn();
    Assert.assertEquals(ref.turn.getFirst(), player2);
    ref.switchTurn();
    Assert.assertEquals(ref.turn.getFirst(), player1);
    ref.switchTurn();
    Assert.assertEquals(ref.turn.getFirst(), player2);
  }

  @Test
  public void testGetScore() {
    HexagonReversiModel model = new HexagonReversiModel(4);
    Player player1 = new Player(MarbleColor.WHITE);
    Player player2 = new Player(MarbleColor.BLACK);
    Assert.assertEquals(3, model.getScore(player1));
    Assert.assertEquals(3, model.getScore(player2));
  }

  @Test
  public void testRender() {
    HexagonReversiModel model = new HexagonReversiModel(4);
  }
}
