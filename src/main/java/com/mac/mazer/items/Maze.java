package com.mac.mazer.items;

import com.mac.mazer.items.characters.Character;

public class Maze extends MazeGenerator {

    public Maze() {
    }

    public Maze(Coordinates coordinates) {
        super(coordinates);
    }

    public int[][] getMaze() {
        return maze;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public boolean outsideMazeLimits(int x, int y) {
        return x < 0 || y < 0 || x >= coordinates.x() || y >= coordinates.y();
    }

    public boolean hasFloorAt(int x, int y) {
        return (maze[x][y] & 1) == 0;
    }

    public boolean hasWallAt(int x, int y) {
        return (maze[x][y] & 8) == 0;
    }

    public void display(Character... characters) {
        int width = coordinates.x();
        int height = coordinates.y();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                System.out.print((maze[col][row] & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");

            for (int col = 0; col < width; col++) {
                boolean isExit = col == width - 1 && row == height - 1;
                String wall = (maze[col][row] & 8) == 0 ? "|" : " ";
                if (isExit) {
                    System.out.print(wall + " END");
                } else {
                    String content = Character.getSpace(new Coordinates(col, row), characters);
                    System.out.print(wall + " " + content + " ");
                }
            }
            System.out.println("|");
        }

        for (int col = 0; col < width; col++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
}
