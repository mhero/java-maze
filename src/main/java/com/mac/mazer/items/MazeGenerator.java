package com.mac.mazer.items;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MazeGenerator {
    protected Coordinates coordinates;
    protected int[][] maze;

    public MazeGenerator() {
    }

    public MazeGenerator(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.maze = new int[coordinates.x()][coordinates.y()];
        generateMaze(0, 0);
    }

    private void generateMaze(int cx, int cy) {
        List<Dir> dirs = Arrays.asList(Dir.values());
        Collections.shuffle(dirs);
        int width = coordinates.x();
        int height = coordinates.y();

        for (Dir dir : dirs) {
            int nx = cx + dir.dx;
            int ny = cy + dir.dy;
            if (nx >= 0 && nx < width && ny >= 0 && ny < height && maze[nx][ny] == 0) {
                maze[cx][cy] |= dir.bit;
                maze[nx][ny] |= dir.opposite.bit;
                generateMaze(nx, ny);
            }
        }
    }

    private enum Dir {
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);

        static {
            N.opposite = S; S.opposite = N;
            E.opposite = W; W.opposite = E;
        }

        final int bit;
        final int dx;
        final int dy;
        Dir opposite;

        Dir(int bit, int dx, int dy) {
            this.bit = bit;
            this.dx = dx;
            this.dy = dy;
        }
    }
}
