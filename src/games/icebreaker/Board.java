package games.icebreaker;

import java.util.Arrays;

public class Board {
    private boolean[][] _board;
    private int _size;

    public Board(int size) {
        _size = size;
        _board = new boolean[2 * size - 1][2 * size - 1];
        resetBoard();
    }

    public boolean get(int x, int y) {
        if(outOfBound(x, y)) {
            System.out.println("Coordinates out of bound");
            return false;
        }
        return _board[y][x];
    }

    public void set(int x, int y, boolean value) {
        if(outOfBound(x, y)) {
            System.out.println("Coordinates out of bound");
            return;
        }
        _board[y][x] = value;
    }

    private boolean outOfBound(int x, int y){
        return x > 2 * _size - 2 || y > 2 * _size - 2 || x < 0 || y < 0 ||
                y > 4 + x || y < x - 4;
    }

    private void resetBoard() {
        for(int x = 0; x < 2 * _size - 1; x++) {
            for(int y = 0; y < 2 * _size - 1; y++) {
                if(!outOfBound(x, y)) {
                    _board[y][x] = true;
                }
            }
        }
        int[][] boats = {{0, 0}, {4, 0}, {8, 4}, {8, 8}, {4, 8}, {0, 4}};
        for(int[] boat : boats) {
            _board[boat[1]][boat[0]] = false;
        }
    }

    public void empty() {
        for(int x = 0; x < 2 * _size - 1; x++) {
            for(int y = 0; y < 2 * _size - 1; y++) {
                if(!outOfBound(x, y)) {
                    _board[y][x] = false;
                }
            }
        }
    }

    public int[][] getNeighbors(int x, int y) {
        return Arrays.stream(new int[][]{{0, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 0}, {-1, -1}}).map(o -> { return new int[] {x + o[0], y + o[1]}; }).toArray(int[][]::new);
    };
}