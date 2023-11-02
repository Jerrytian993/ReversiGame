package model;

import java.util.ArrayDeque;
import java.util.List;

import model.player.Player;

/**
 * Represents a Referee that is responsible for keeping the rules of the game.
 */
public class Referee {
  Player player1;
  Player player2;
  ArrayDeque<Player> turn;

  /**
   * Constructs the referee.
   *
   * @param player1 the first player playing the game.
   * @param player2 the second player playing the game.
   */
  public Referee(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
    this.turn = new ArrayDeque<>();
    turn.add(player1);
    turn.add(player2);
  }

  // Switches the player turn if the player made a move or passes.
  void switchTurn() {
    Player currentPlayer = turn.remove();
    turn.add(currentPlayer);
  }

  // Determines if a move is legal based on the current board state and the passed in cell.
  boolean isLegalMove(ICell cell, List<ICell> legalMoves, Player p) {
    return legalMoves.contains(cell);
    // Need to include this check as well, something is bugged. && p == turn.getFirst()
  }

  // Gets the queue of the players who are playing.
  ArrayDeque<Player> getTurn() {
    return this.turn;
  }

}
