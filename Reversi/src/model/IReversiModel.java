package model;

import model.player.Player;

public interface IReversiModel {

  /**
   * Moves the marble from the source cell to the destination cell.
   *
   * @param cell a cell that represents where the player would like to place their marble.
   * @param p    the player that would like this move to be executed.
   */
  void placeMarble(ICell cell, Player p);

  /**
   * Gets the current score of the game.
   *
   * @param p the current player.
   * @return the current score for the given player.
   */
  int getScore(Player p);

  /**
   * Gets the cell at a specified coordinate
   *
   * @param coordinate the coordinate of the cell you would like to get.
   * @return the desired cell.
   */
  ICell getCellAt(CubeCoordinate coordinate);

  /**
   * Gets the radius of the current board.
   *
   * @return the radius of the current board.
   */
  int getRadius();

  /**
   * A move a player can make on their turn that switches the turn to the other player.
   */
  void pass();

}
