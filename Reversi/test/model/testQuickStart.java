package model;

import org.junit.Test;

import java.util.Optional;

import model.player.Player;
import view.IView;
import view.ReversiTextualView;

public class testQuickStart {

  @Test
  public void testQuickStart() {
    HexagonReversiModel model = new HexagonReversiModel(4);
    CubeCoordinate coordinateOfFirstPlacement = new CubeCoordinate(1, -2, 1);
    HexagonCell cell = new HexagonCell(coordinateOfFirstPlacement, Optional.empty());
    Player player1 = new Player(MarbleColor.WHITE);
    StringBuilder actual = new StringBuilder();
    IView view = new ReversiTextualView(model, actual);
    view.render();
    System.out.println(actual);
    model.placeMarble(cell, player1);
    StringBuilder move = new StringBuilder();
    IView view2 = new ReversiTextualView(model, move);
    view2.render();
    System.out.println(move);
  }
}
