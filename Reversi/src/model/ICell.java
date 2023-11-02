package model;

public interface ICell {

  /**
   * Determines if this cell contains a marble.
   *
   * @return true if this cell contains a marble.
   */
  boolean containsMarble();

  /**
   * Determines if this cells marble is the same as another cells marble.
   *
   * @parm marble the marble that is being compared.
   * @return true if this cell contains a marble of the same color as the given
   */
  boolean sameMarble(Marble marble);

  /**
   * Determines the direction to a specific cell.
   *
   * @param cell the cell you are trying to get the direction to.
   * @return the coordinates that give the direction to the opposite adjacent colored tile.
   */
  CubeCoordinate directionTo(ICell cell);
}
