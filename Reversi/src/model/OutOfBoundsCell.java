package model;

/**
 * Represents a cell that is out of bounds.
 */
public class OutOfBoundsCell implements ICell {

  /**
   * Determines if this cell contains a marble.
   *
   * @return true if this cell contains a marble.
   */
  @Override
  public boolean containsMarble() {
    return false;
  }

  /**
   * Determines if this cells marble is the same as another cells marble.
   *
   * @param marble
   * @return true if this cell contains a marble of the same color as the given
   * @parm marble the marble that is being compared.
   */
  @Override
  public boolean sameMarble(Marble marble) {
    return false;
  }

  /**
   * Determines the direction to a specific cell.
   *
   * @param cell the cell you are trying to get the direction to.
   * @return the coordinates that give the direction to the opposite adjacent colored tile.
   */
  @Override
  public CubeCoordinate directionTo(ICell cell) {
    return null;
  }
}
