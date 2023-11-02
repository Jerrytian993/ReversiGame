package model;

import java.util.Optional;

/**
 * Represents a Hexagon cell used to build a board.
 */
public class HexagonCell implements ICell {
  private final CubeCoordinate coordinate;
  private final Optional<Marble> marble;

  /**
   * Constructs a Hexagon cell based on the provided coordinates and if it has a marble or not.
   *
   * @param coordinate a HexCoordinate that represents this cells position on the board.
   * @param marble     an optional describing if this cell contains a marble or not.
   */
  public HexagonCell(CubeCoordinate coordinate, Optional<Marble> marble) {
    this.coordinate = coordinate;
    this.marble = marble;
  }

  /**
   * Determines if this cell contains a marble.
   *
   * @return true if this cell contains a marble.
   */
  @Override
  public boolean containsMarble() {
    return marble.isPresent();
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
    return this.marble.equals(Optional.ofNullable(marble));
  }

  /**
   * Determines the direction to a specific cell.
   *
   * @param cell the cell you are trying to get the direction to.
   * @return the coordinates that give the direction to the opposite adjacent colored tile.
   */
  @Override
  public CubeCoordinate directionTo(ICell cell) {
    if (cell instanceof HexagonCell) {
      HexagonCell otherCell = (HexagonCell) cell;
      int dq = this.coordinate.diffQ(otherCell.coordinate);
      int dr = this.coordinate.diffR(otherCell.coordinate);
      int ds = this.coordinate.diffS(otherCell.coordinate);
      return new CubeCoordinate(dq, dr, ds);
    }
    throw new IllegalArgumentException("Unsupported cell type");
  }

  @Override
  public String toString() {
    return coordinate.q + "," + coordinate.r + "," + coordinate.s + marble + marble;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof HexagonCell)) {
      return false;
    }
    HexagonCell hexCell = (HexagonCell) o;
    return coordinate.equals(hexCell.coordinate) && marble.equals(hexCell.marble);
  }

  @Override
  public int hashCode() {
    int result = coordinate != null ? coordinate.hashCode() : 0;
    result = 31 * result + (marble != null ? marble.hashCode() : 0);
    return result;
  }
}
