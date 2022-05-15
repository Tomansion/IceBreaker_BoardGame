package games.icebreaker;

import java.util.Set;

public class Board {
    private int[][] _board;
    private int _size;

    public Board(int size) {
        _size = size;
        _board = new int[2 * size - 1][2 * size - 1];
        resetBoard();
    }

    public int get(int x, int y) {
        if(outOfBound(x, y)) {
            System.out.println("Coordinates out of bound");

            return -1;
        }
        return _board[y][x];
    }

    public void set(int x, int y, int value) {
        if(outOfBound(x, y)) {
            System.out.println("Coordinates out of bound");
            return;
        }
        _board[y][x] = value;
    }

    private boolean outOfBound(int x, int y){
        return x > 2 * _size - 2 || y > 2 * _size - 2 || x < 0 || y < 0 ||
                (x >= _size && ((y <= x - _size) || (y >= 3 * _size - x - 2)));
    }

    private void resetBoard() {
        for(int x = 0; x < 2 * _size - 1; x++) {
            for(int y = 0; y < 2 * _size - 1; y++) {
                if(!outOfBound(x, y)) {
                    _board[y][x] = 1;
                }
            }
        }
    }

    private int[][] getOffsets(int x, int y) {
        //
        //   Top (y < _size - 1) :
        //       [-1,-1]  [0,-1]
        //                 -
        //  [-1, 0]  [TARGET]  [1, 0]
        //          -
        //       [0, 1]  [1, 1]
        //
        //
        //   Mid (y == _size - 1) :
        //       [-1,-1]  [0,-1]
        //                -
        //  [-1, 0]  [TARGET]  [1, 0]
        //                -
        //       [-1, 1]  [0, 1]
        //
        //
        //   Bot (y > _size - 1):
        //       [0,-1]  [1,-1]
        //          -
        //  [-1, 0]  [TARGET]  [1, 0]
        //                -
        //       [-1, 1]  [0, 1]
        //

        if (outOfBound(x, y)) {
            System.out.println("Coordinates out of bound");
            return null;
        }

        if (y <  _size - 1) {
            return new int[][] {
                {-1, -1},
                {0, -1},
                {1, 0},
                {1, 1},
                {0, 1},
                {-1,0},
            };
        } else if (y == _size - 1) {
            return new int[][] {
                {-1, -1},
                {0, -1},
                {1, 0},
                {0, 1},
                {-1, 1},
                {-1, 0},
            };
        } else {
            return new int[][] {
                {0, -1},
                {1, -1},
                {1, 0},
                {0, 1},
                {-1, 1},
                {-1, 0},
            };
        }
    };

    public Set<int[]> getNeighbors(int x, int y) {
        Set<int[]> neighbors = new java.util.HashSet<int[]>();

        int[][] offsets = getOffsets(x, y);
        for(int[] offset : offsets) {
            int neighborX = x + offset[0];
            int neighborY = y + offset[1];

            if(!outOfBound(neighborX, neighborY)) {
                neighbors.add(new int[] {neighborX, neighborY});
            }
        }
        return neighbors;
    }
    // Possible moves
    // Return a set of coordinates (x, y) int
    // From a boat position and a player color
    // public
}
