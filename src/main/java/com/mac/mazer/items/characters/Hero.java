package com.mac.mazer.items.characters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.mazer.items.Battle;
import com.mac.mazer.items.Coordinates;
import com.mac.mazer.items.Maze;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hero extends Character {

    private Direction direction;

    public Hero() {
    }

    public Hero(int power) {
        this.positions = new ArrayList<>();
        this.positions.add(new Coordinates(0, 0));
        this.power = power;
        setDirection(Direction.S);
    }

    @Override
    public boolean isCharacterHere(Coordinates coordinates) {
        return getCurrentCoordinates().equals(coordinates);
    }

    public Direction getDirection() {
        return direction;
    }

    public void turnLeft() {
        setDirection(direction.left);
    }

    public void turnRight() {
        setDirection(direction.right);
    }

    public void rotate180() {
        setDirection(direction.opposite);
    }

    public void displayCurrentStats() {
        System.out.println("hero power: " + power);
        System.out.println("hero position: " + getCurrentCoordinates());
    }

    public EnemiesHandler moveForward(Maze maze, EnemiesHandler enemies) {
        Coordinates pos = getCurrentCoordinates();
        int x = pos.x() + direction.dx;
        int y = pos.y() + direction.dy;
        if (isValidMove(x, y, maze)) {
            positions.add(new Coordinates(x, y));
            return new Battle().checkEnemiesInteraction(enemies, this);
        }
        System.out.println("Invalid move");
        return enemies;
    }

    public Coordinates getCurrentCoordinates() {
        return positions.get(positions.size() - 1);
    }

    private boolean isValidMove(int x, int y, Maze maze) {
        if (maze.outsideMazeLimits(x, y)) return false;
        if (direction == Direction.S && maze.hasFloorAt(x, y)) return false;
        if (direction == Direction.N && maze.hasFloorAt(x, y + 1)) return false;
        if (direction == Direction.E && maze.hasWallAt(x, y)) return false;
        return direction != Direction.W || !maze.hasWallAt(x + 1, y);
    }

    private void setDirection(Direction d) {
        this.direction = d;
        this.logo = d.logo;
    }

    public enum Direction {
        N("▲", 0, -1), S("▼", 0, 1), E("►", 1, 0), W("◄", -1, 0);

        static {
            N.left = W; N.right = E; N.opposite = S;
            S.left = E; S.right = W; S.opposite = N;
            E.left = N; E.right = S; E.opposite = W;
            W.left = S; W.right = N; W.opposite = E;
        }

        final String logo;
        Direction left;
        Direction right;
        Direction opposite;
        final int dx;
        final int dy;

        Direction(String logo, int dx, int dy) {
            this.logo = logo;
            this.dx = dx;
            this.dy = dy;
        }
    }
}
