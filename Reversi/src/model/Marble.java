package model;

import java.util.Objects;

/**
 * Represents a marble in the reversi game.
 */
public class Marble {
  MarbleColor color;

  /**
   * Constructs a marble.
   *
   * @param color a color enum either BLACK or WHITE that you would like this marble to be.
   */
  public Marble(MarbleColor color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return "Color: " + color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Marble marble = (Marble) o;
    return color == marble.color;
  }

  @Override
  public int hashCode() {
    return Objects.hash(color);
  }
}
