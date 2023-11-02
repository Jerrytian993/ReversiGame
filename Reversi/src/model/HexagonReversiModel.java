package model;

import java.util.ArrayDeque;
import java.util.List;

import model.player.Player;

/**
 * Represents a HexagonReversi game.
 */
public class HexagonReversiModel implements IReversiModel {

  private final int radius;
  // Represent the hexagonal board structure we use in the game
  HexBoard board;
  private final GameStatus status;
  private final Referee referee;
  private int p1Pass;
  private int p2Pass;

  /**
   * Constructs a HexagonReversiModel.
   *
   * @param radius the radius of the Hexagon board.
   */
  public HexagonReversiModel(int radius) {
    this.radius = radius;
    this.board = new HexBoard(radius);
    this.status = GameStatus.PLAYING;
    p1Pass = 0;
    p2Pass = 0;
    Player player1 = new Player(MarbleColor.WHITE);
    Player player2 = new Player(MarbleColor.BLACK);
    this.referee = new Referee(player1, player2);
  }

  /**
   * Moves the marble from the source cell to the destination cell.
   *
   * @param cell a cell that represents where the player would like to place their marble.
   * @param p    the player that would like this move to be executed.
   */
  @Override
  public void placeMarble(ICell cell, Player p) throws IllegalArgumentException {
    List<ICell> legalMoves = board.getLegalMoves(p);
    if (referee.isLegalMove(cell, legalMoves, p)) {
      board.placeMarble(cell, p);
      if (p.marbleColor.equals(MarbleColor.WHITE)) {
        p1Pass = 0;
      } else {
        p2Pass = 0;
      }
      referee.switchTurn();
    } else {
      throw new IllegalArgumentException("That was not a valid move");
    }
  }

  /**
   * Gets the current score of the game.
   *
   * @param p the current player.
   * @return the current score for the given player.
   */
  @Override
  public int getScore(Player p) {
    return board.getScore(p);
  }

  /**
   * Gets the cell at a specified coordinate.
   *
   * @param coordinate the coordinate of the cell you would like to get.
   * @return the desired cell.
   */
  @Override
  public ICell getCellAt(CubeCoordinate coordinate) {
    return board.getCellAt(coordinate);
  }

  /**
   * Gets the radius of the current board.
   *
   * @return the radius of the current board.
   */
  @Override
  public int getRadius() {
    return this.radius;
  }

  /**
   * A move a player can make on their turn that switches the turn to the other player.
   */
  @Override
  public void pass() {
    ArrayDeque<Player> turns = referee.getTurn();
    turns = referee.getTurn();
    if (turns.getFirst().equals(new Player(MarbleColor.WHITE))) {
      p1Pass++;
    } else {
      p2Pass++;
    }
    referee.switchTurn();
  }
}
