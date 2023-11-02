package model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Tests for methods that are specific to HexagonCells.
 */
public class testHexagonCell {

  @Test
  public void testContainsMarble() {
    CubeCoordinate coord = new CubeCoordinate(0, 0, 0);
    HexagonCell cell = new HexagonCell(coord, Optional.of(new Marble(MarbleColor.WHITE)));
    Assert.assertTrue(cell.containsMarble());
  }

  @Test
  public void testContainsMarbleNoMarble() {
    CubeCoordinate coord = new CubeCoordinate(0, 0, 0);
    HexagonCell cell = new HexagonCell(coord, Optional.empty());
    Assert.assertFalse(cell.containsMarble());
  }

  @Test
  public void testSameMarbleTrue() {
    CubeCoordinate coord = new CubeCoordinate(0, 0, 0);
    HexagonCell cell = new HexagonCell(coord, Optional.of(new Marble(MarbleColor.WHITE)));
    Assert.assertTrue(cell.sameMarble(new Marble(MarbleColor.WHITE)));
  }

  @Test
  public void testSameMarbleFalse() {
    CubeCoordinate coord = new CubeCoordinate(0, 0, 0);
    HexagonCell cell = new HexagonCell(coord, Optional.of(new Marble(MarbleColor.WHITE)));
    Assert.assertFalse(cell.sameMarble(new Marble(MarbleColor.BLACK)));
  }

  @Test
  public void testDirectionTo() {
    CubeCoordinate coord = new CubeCoordinate(0, 0, 0);
    CubeCoordinate coord2 = new CubeCoordinate(1, 0, -1);
    HexagonCell cell = new HexagonCell(coord, Optional.of(new Marble(MarbleColor.WHITE)));
    HexagonCell cell2 = new HexagonCell(coord2, Optional.of(new Marble(MarbleColor.WHITE)));
    Assert.assertEquals(new CubeCoordinate(1, 0, -1), cell.directionTo(cell2));
  }
}
