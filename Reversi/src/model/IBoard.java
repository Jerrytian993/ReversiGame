package model;

import java.util.List;

import model.player.Player;

/**
 * The interface of Board in which all different(shape, structure) kind of boards can implement.
 */
public interface IBoard {

  /**
   * Retrieves a list of cells that's legal for the current player to move.
   *
   * @param p the player who we are getting the legal moves for.
   * @return a list of cells that are valid moves for the player.
   */
  List<ICell> getLegalMoves(Player p);

  /**
   * Places a colored marble at a specified cell based on the player who is currently moving.
   *
   * @param cell the cell the player would like to place a marble.
   * @param p the player that is making the move.
   */
  void placeMarble(ICell cell, Player p);

  /**
   * Gets the current score of the game.
   *
   * @param p the current player.
   * @return the current score for the given player.
   */
  int getScore(Player p);
}