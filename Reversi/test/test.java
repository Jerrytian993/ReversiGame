import org.junit.Test;

import java.util.Map;

import model.CubeCoordinate;
import model.HexBoard;
import model.ICell;

public class test {
  @Test
  public void testGenerateCoordinates() {
    HexBoard board = new HexBoard(4);
//    List<CubeCoordinate> coordiantes = board.generateCoordinates();
    board.generateBoard(4);
    CubeCoordinate coordinate = new CubeCoordinate(-1, -1, 2);
    System.out.println(board.cellList.get(coordinate));
    System.out.println(board.cellList);


    for (Map.Entry<CubeCoordinate, ICell> entry : board.cellList.entrySet()) {
      System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
    }

//    System.out.println(coordiantes.size());
//    for (CubeCoordinate c : coordiantes) {
//      System.out.println(c.q + " " + c.r + " " + c.s);
//    }
  }
}
