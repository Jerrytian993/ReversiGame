# Reversi

## Overview

This codebase implements a two-player game, Hexagonal Reversi, where the board grid is composed of
hexagons. The project provides a model to represent the game board (HexBoard), enforces game rules
with classes like Referee, and offers the infrastructure for visualizations like the
ReversiTextualView and the main model HexagonReversiModel. There's groundwork for potential
extensions such as GUI views, controllers, and AI players. Moreover, our model interfaces are
designed with flexibility in mind. They can be seamlessly extended in the future if the need arises
to support additional functionalities, such as accommodating different board shapes or other game
dynamics.

## Quick Start

To get started with using the codebase, you can create a HexBoard class object and simulate player
moves:

> We have provided this in a test method called testQuickStart located in the testQuickStart file

```java
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
``` 

This will show you how we rendered the board and that the move function works as intended,
flipping a tile as expected.

## Key Components

### HexBoard

The HexBoard class is an important and central part of the model, controlling the board state,
and determining things that a board should determine. This includes things such as what legal moves
there are on the board, and updating itself when required, for example after a move.

## CubeCoordinate

Our chosen representation for coordinates was the q, r, s cube coordinate.

### ICell & HexagonCell

The ICell interface and its implementation, such as OutOfBoundsCell, HexagonCell, symbolize
individual hexagonal cells on the board. They hold details like the cell's current state (what the
cells coordinates are, and whether a marble is placed down).

### Player & IPlayer

The Player class is important for playing the game, representing either a human or potential AI
player. Players are differentiated using the MarbleColor attribute (BLACK or WHITE).

## Key Subcomponents

### HexBoard Methods:

generateBoard(): Initializes the board, aligning cells appropriately.

getLegalMoves(player): Determines all available moves to the player.

placeMarble(ICell, Player) : Executes a move and amends the board accordingly.

## HexagonCell Attributes:

Coordinate: This refers to our coordinate class and is the hexagon index of the cell on the board

Marble: Marble objects have color attribute and a cell can either have or not have a marble

## Referee Method:

Since the referee enforces the rules of the game...

isLegalMove(ICell, List<ICell>): determines if based on the passed in cell, if this move is legal
based on a provided list of legal moves for the current player.

## Source Organization

src/model: Hosts model classes (HexBoard, ICell, Player, etc.) and relevant subclasses and packages
for the game to function.

src/model/move: Interface and class represents moves that player can make. (For future use) 

src/model/player : Player package where IPlayer can be either human or AI, will be implemented more
specifically in the future. 

src/test: Encompasses tests for the model classes and views.

src/test/model: specific model tests.

src/view: Incorporates the ReversiTextualView class, which renders the game visually.

README.md: This document, offering a synopsis and guide for the codebase.
