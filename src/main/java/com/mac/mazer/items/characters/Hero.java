package com.mac.mazer.items.characters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.mazer.items.Coordinates;
import com.mac.mazer.items.maze.Maze;

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

    private void setDirection(Direction d) {
        this.direction = d;
        this.logo = d.logo;
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

    public Coordinates getCurrentCoordinates() {
        return positions.get(positions.size() - 1);
    }

    /**
     * Attempts to move forward. Returns a MoveResult describing outcome.
     * Does NOT touch EnemiesHandler or IOPort — that is the caller's concern.
     */
    public MoveResult tryMoveForward(Maze maze) {
        Coordinates pos = getCurrentCoordinates();
        int x = pos.x() + direction.dx;
        int y = pos.y() + direction.dy;
        if (!isValidMove(x, y, maze)) {
            return MoveResult.blocked();
        }
        positions.add(new Coordinates(x, y));
        return MoveResult.moved(getCurrentCoordinates());
    }

    public void applyPowerDelta(int newPower) {
        this.power = newPower;
    }

    private boolean isValidMove(int x, int y, Maze maze) {
        if (maze.outsideMazeLimits(x, y)) return false;
        if (direction == Direction.S && maze.hasFloorAt(x, y)) return false;
        if (direction == Direction.N && maze.hasFloorAt(x, y + 1)) return false;
        if (direction == Direction.E && maze.hasWallAt(x, y)) return false;
        return direction != Direction.W || !maze.hasWallAt(x + 1, y);
    }

    public enum Direction {
        N("▲", 0, -1), S("▼", 0, 1), E("►", 1, 0), W("◄", -1, 0);

        static {
            N.left = W;
            N.right = E;
            N.opposite = S;
            S.left = E;
            S.right = W;
            S.opposite = N;
            E.left = N;
            E.right = S;
            E.opposite = W;
            W.left = S;
            W.right = N;
            W.opposite = E;
        }

        final String logo;
        final int dx;
        final int dy;
        Direction left;
        Direction right;
        Direction opposite;

        Direction(String logo, int dx, int dy) {
            this.logo = logo;
            this.dx = dx;
            this.dy = dy;
        }
    }

    public record MoveResult(boolean moved, Coordinates newPosition) {
        public static MoveResult moved(Coordinates pos) {
            return new MoveResult(true, pos);
        }

        public static MoveResult blocked() {
            return new MoveResult(false, null);
        }
    }
}
