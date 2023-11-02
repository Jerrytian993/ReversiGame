package model.player;

import model.MarbleColor;

/**
 * Represents a player that is assigned a marble color.
 */
public class Player implements IPlayer {
  public MarbleColor marbleColor;

  /**
   * Constructs a player.
   *
   * @param marbleColor the marble color of the player.
   */
  public Player(MarbleColor marbleColor) {
    this.marbleColor = marbleColor;
  }
}
