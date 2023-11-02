package model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for CubeCoordinates:W
 */
public class testCubeCoordinate {

  @Test
  public void testDiffQ1() {
    CubeCoordinate c1 = new CubeCoordinate(0, 0, 0);
    CubeCoordinate c2 = new CubeCoordinate(1, 1, 1);
    int result = c1.diffQ(c2);
    Assert.assertEquals(1, result);
  }

  @Test
  public void testDiffQ2() {
    CubeCoordinate c1 = new CubeCoordinate(3, 3, 2);
    CubeCoordinate c2 = new CubeCoordinate(3, -2, 2);
    int result = c1.diffQ(c2);
    Assert.assertEquals(0, result);
  }

  @Test
  public void testDiffR1() {
    CubeCoordinate c1 = new CubeCoordinate(0, 0, 0);
    CubeCoordinate c2 = new CubeCoordinate(1, 1, 1);
    int result = c1.diffR(c2);
    Assert.assertEquals(1, result);
  }

  @Test
  public void testDiffR2() {
    CubeCoordinate c1 = new CubeCoordinate(3, -3, 1);
    CubeCoordinate c2 = new CubeCoordinate(3, -2, 2);
    int result = c1.diffR(c2);
    Assert.assertEquals(1, result);
  }

  // Tests for diffS method
  @Test
  public void testDiffS1() {
    CubeCoordinate c1 = new CubeCoordinate(0, 0, 0);
    CubeCoordinate c2 = new CubeCoordinate(1, 1, 1);
    int result = c1.diffS(c2);
    Assert.assertEquals(1, result);
  }

  @Test
  public void testDiffS2() {
    CubeCoordinate c1 = new CubeCoordinate(3, 4, 5);
    CubeCoordinate c2 = new CubeCoordinate(3, -2, 4);
    int result = c1.diffS(c2);
    Assert.assertEquals(-1, result);
  }
}
