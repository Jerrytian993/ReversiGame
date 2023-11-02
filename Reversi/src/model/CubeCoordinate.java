package model;

import java.util.Objects;

/**
 * Represents a coordinate used to represent a hexagon on a grid.
 */
public class CubeCoordinate {
  public int q;
  public int r;
  public int s;

  /**
   * Constructs the CubeCoordinate.
   *
   * @param q the q direction (x-axis a cube).
   * @param r the r direction (y-axis a cube).
   * @param s the s direction (z-axis a cube).
   *
   */
  public CubeCoordinate(int q, int r, int s) {
    this.q = q;
    this.r = r;
    this.s = s;
  }

  /**
   * Gets the difference between two q coordinates.
   *
   * @param other the other CubeCoordinate
   * @return the difference between the two q coordinates (the direction).
   */
  public int diffQ(CubeCoordinate other) {
    return other.q - this.q;
  }

  /**
   * Gets the difference between two r coordinates.
   *
   * @param other the other CubeCoordinate
   * @return the difference between the two r coordinates (the direction).
   */
  public int diffR(CubeCoordinate other) {
    return other.r - this.r;
  }

  /**
   * Gets the difference between two s coordinates.
   *
   * @param other the other CubeCoordinate
   * @return the difference between the two s coordinates (the direction).
   */
  public int diffS(CubeCoordinate other) {
    return other.s - this.s;
  }

  @Override
  public String toString() {
    return "(" + q + ", " + r + ", " + s + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CubeCoordinate that = (CubeCoordinate) o;
    return q == that.q && r == that.r && s == that.s;
  }

  @Override
  public int hashCode() {
    return Objects.hash(q, r, s);
  }
}
