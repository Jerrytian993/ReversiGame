package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import model.player.Player;

/**
 * Represent a hexagonal board to play the Reversi Game;
 */
public class HexBoard implements IBoard {
  // INVARIANT: value is at least 2
  private final int radius;
  final List<ICell> p1Cells;
  final List<ICell> p2Cells;
  public Map<CubeCoordinate, ICell> cellList;

  /**
   * Constructs a Hexagon Board
   *
   * @param radius the radius of the Hexagon board. Also is the side length.
   */
  public HexBoard(int radius) {
    if (radius < 3) {
      throw new IllegalArgumentException("The valid game starts with board radius at least 3");
    }

    this.radius = radius;
    this.p1Cells = new ArrayList<>();
    this.p2Cells = new ArrayList<>();
    this.cellList = generateBoard(radius);
    initializeMarbles(cellList, p1Cells, p2Cells);
  }

  // Initializes the 6 marbles for the start of the game
  private static void initializeMarbles(Map<CubeCoordinate, ICell> cellList, List<ICell> p1Cells,
                                        List<ICell> p2Cells) {
    List<CubeCoordinate> p1Coordinates = new ArrayList<>();

    p1Coordinates.add(new CubeCoordinate(0, -1, 1));
    p1Coordinates.add(new CubeCoordinate(1, 0, -1));
    p1Coordinates.add(new CubeCoordinate(-1, 1, 0));

    List<CubeCoordinate> p2Coordinates = new ArrayList<>();

    p2Coordinates.add(new CubeCoordinate(1, -1, 0));
    p2Coordinates.add(new CubeCoordinate(0, 1, -1));
    p2Coordinates.add(new CubeCoordinate(-1, 0, 1));

    for (CubeCoordinate c : p1Coordinates) {
      ICell cell = new HexagonCell(c, Optional.of(new Marble(MarbleColor.WHITE)));
      cellList.put(c, cell);
      p1Cells.add(cell);
    }

    for (CubeCoordinate c : p2Coordinates) {
      ICell cell = new HexagonCell(c, Optional.of(new Marble(MarbleColor.BLACK)));
      cellList.put(c, cell);
      p2Cells.add(cell);
    }
  }

  // Generate a complete board for the game.
  private static Map<CubeCoordinate, ICell> generateBoard(int radius) {
    List<CubeCoordinate> coordinates = generateCoordinates(radius);
    Map<CubeCoordinate, ICell> board = new HashMap<>();
    boolean swap = false;
    int columnNum = 0;
    int coordinateIndex = 0;
    for (int row = -radius + 1; row < radius; row++) {
      for (int column = columnNum; column < radius; column++) {
        board.put(coordinates.get(coordinateIndex),
                new HexagonCell(coordinates.get(coordinateIndex), Optional.empty()));
        coordinateIndex++;
      }
      if (row == 0) {
        swap = true;
      }
      if (swap) {
        columnNum++;
      } else {
        columnNum--;
      }
    }
    return board;
  }

  // Generates the coordinates to set up the board.
  private static List<CubeCoordinate> generateCoordinates(int radius) {
    List<CubeCoordinate> coordinates = new ArrayList<>();
    int columnNum = 0;
    for (int row = -radius + 1; row <= radius; row++) {
      for (int column = columnNum; column < radius; column++) {
        coordinates.add(new CubeCoordinate(column, row, -(column + row)));
      }
      columnNum--;
      if (row == 0) {
        break;
      }
    }

    int someNum = radius - 1;
    for (int row = 1; row < radius; row++) {
      for (int column = -radius + 1; column < someNum; column++) {
        coordinates.add(new CubeCoordinate(column, row, -(column + row)));
      }
      someNum--;
    }
    return coordinates;
  }

  /**
   * Retrieves a list of cells that's legal for the current player to move.
   *
   * @return a list of cells that are valid moves for the player.
   */
  @Override
  public List<ICell> getLegalMoves(Player p) {
    List<ICell> legalMoves = new ArrayList<>();
    MarbleColor playerColor = p.marbleColor;
    for (Map.Entry<CubeCoordinate, ICell> entry : cellList.entrySet()) {
      ICell currentCell = entry.getValue();

      // if the cell doesn't contain a marble
      if (!currentCell.containsMarble()) {
        List<ICell> adjacentCells = getAdjacentCells(entry.getKey());

        for (ICell adjacentCell : adjacentCells) {
          // if the adjacent cell contains a marble, and it's not the same marble as the players
          if (adjacentCell.containsMarble() && !adjacentCell.sameMarble(new Marble(playerColor))) {
            CubeCoordinate direction = currentCell.directionTo(adjacentCell);
            if (isUnbrokenChain(entry.getKey(), direction, p)) {
              legalMoves.add(currentCell);
              break;
            }
          }
        }
      }
    }
    return legalMoves;
  }

  // Determines if the chain from direction of the adjacent cell is unbroken of the opposite color
  // until it hits the marble of the same color as the current player
  private boolean isUnbrokenChain(CubeCoordinate origin, CubeCoordinate direction, Player p) {
    // Offset to put it on the adjacent cells coordinate
    CubeCoordinate crawlingCoord = new CubeCoordinate(origin.q + direction.q,
            origin.r + direction.r,
            origin.s + direction.s);
    while (Math.abs(crawlingCoord.q) < radius && Math.abs(crawlingCoord.r) < radius && Math.abs(crawlingCoord.s) < radius) {
      ICell cell = cellList.get(crawlingCoord);

      if (!cell.containsMarble()) {
        return false;
      }

      if (cell.sameMarble(new Marble(p.marbleColor))) {
        return true;
      }

      crawlingCoord = new CubeCoordinate(crawlingCoord.q + direction.q,
              crawlingCoord.r + direction.r,
              crawlingCoord.s + direction.s);
    }

    return false;
  }

  // Gets all the adjacent cells.
  List<ICell> getAdjacentCells(CubeCoordinate coordinate) {
    List<CubeCoordinate> adjacentCoordinates = getAdjacentCoordinates(coordinate);
    List<ICell> adjacentCells = new ArrayList<>();
    for (CubeCoordinate coords : adjacentCoordinates) {
      adjacentCells.add(cellList.getOrDefault(coords, new OutOfBoundsCell()));
    }
    return adjacentCells;
  }

  // Gets all the possible adjacent coordinates to the passed in coordinate
  private List<CubeCoordinate> getAdjacentCoordinates(CubeCoordinate coordinate) {
    int q = coordinate.q;
    int r = coordinate.r;
    int s = coordinate.s;
    List<CubeCoordinate> adjacentCoords = new ArrayList<>();

    int[][] dirChanges = {
            {1, -1, 0}, {1, 0, -1}, {0, 1, -1},
            {-1, 1, 0}, {-1, 0, 1}, {0, -1, 1}
    };

    for (int[] dir : dirChanges) {
      adjacentCoords.add(new CubeCoordinate(
              coordinate.q + dir[0],
              coordinate.r + dir[1],
              coordinate.s + dir[2]
      ));
    }
    return adjacentCoords;
  }

  /**
   * Places a colored marble at a specified cell based on the player who is currently moving.
   *
   * @param cell the cell the player would like to place a marble.
   * @param p    the player that is making the move.
   */
  @Override
  public void placeMarble(ICell cell, Player p) {
    List<ICell> adjacentCells = getAdjacentCells(findCoordinateForCell(cell));
    List<CubeCoordinate> directions = getAdjacentCoordinateDirections(adjacentCells, p, cell);
    List<ICell> cellsToFlip = explorePaths(findCoordinateForCell(cell), directions, p);
    cellsToFlip.add(cell);
    updateBoard(cellsToFlip, p);
  }


  // Updates the board by flipping the specified cells to the players marble.
  private void updateBoard(List<ICell> cellsToFlip, Player p) {
    for (ICell cell : cellsToFlip) {
      CubeCoordinate cellCoord = findCoordinateForCell(cell);
      cellList.put(cellCoord, new HexagonCell(cellCoord,
              Optional.of(new Marble(p.marbleColor))));

      if (p.marbleColor == MarbleColor.WHITE) {
        p1Cells.add(cell);
        p2Cells.remove(cell);
      } else {
        p2Cells.add(cell);
        p1Cells.remove(cell);
      }
    }
  }

  List<ICell> explorePaths(CubeCoordinate origin, List<CubeCoordinate> directions, Player p) {
    List<ICell> cellsToFlip = new ArrayList<>();

    for (CubeCoordinate direction : directions) {
      CubeCoordinate crawlingCoord = new CubeCoordinate(origin.q + direction.q,
              origin.r + direction.r,
              origin.s + direction.s);

      List<ICell> potentialFlips = new ArrayList<>();

      while (Math.abs(crawlingCoord.q) < radius && Math.abs(crawlingCoord.r) < radius
              && Math.abs(crawlingCoord.s) < radius) {

        ICell cellToAdd = cellList.get(crawlingCoord);

        // If the cell is empty or the same as the player's color, break out
        if (!cellToAdd.containsMarble() || cellToAdd.sameMarble(new Marble(p.marbleColor))) {
          break;
        }

        potentialFlips.add(cellToAdd);

        crawlingCoord = new CubeCoordinate(crawlingCoord.q + direction.q,
                crawlingCoord.r + direction.r,
                crawlingCoord.s + direction.s);
      }

      ICell lastCell = cellList.get(crawlingCoord);
      if (lastCell.sameMarble(new Marble(p.marbleColor))) {
        cellsToFlip.addAll(potentialFlips);
      }
    }

    return cellsToFlip;
  }


  // Collects the valid crawl directions for this cell.
  List<CubeCoordinate> getAdjacentCoordinateDirections(List<ICell> cells, Player p,
                                                       ICell origin) {
    return cells.stream()
            .filter(ICell::containsMarble)
            .filter((cell) -> !cell.sameMarble(new Marble(p.marbleColor)))
            .map(origin::directionTo)
            .collect(Collectors.toList());
  }

  // Finds the coordinates for the provided cell.
  CubeCoordinate findCoordinateForCell(ICell cell) throws IllegalArgumentException {
    for (Map.Entry<CubeCoordinate, ICell> entry : cellList.entrySet()) {
      if (entry.getValue().equals(cell)) {
        return entry.getKey();
      }
    }
    throw new IllegalArgumentException("Cell not found on the board");
  }

  /**
   * Gets the cell at a specified coordinate
   *
   * @param coordinate the coordinate of the cell you would like to get.
   * @return the desired cell.
   */
  public ICell getCellAt(CubeCoordinate coordinate) {
    return cellList.get(coordinate);
  }

  /**
   * Gets the current score of the game.
   *
   * @param p the current player.
   * @return the current score for the given player.
   */
  @Override
  public int getScore(Player p) {
    if (p.marbleColor.equals(MarbleColor.WHITE)) {
      return p1Cells.size();
    } else {
      return p2Cells.size();
    }
  }
}
