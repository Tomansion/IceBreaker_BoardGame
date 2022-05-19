package games.icebreaker.challenger;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Board {
    private boolean[][] _board;
    private int _size;

    public Board(int size) {
        _size = size;
        _board = new boolean[2 * size - 1][2 * size - 1];
        resetBoard();
    }

    public Board(Board board) {
        _size = board._size;
        _board = new boolean[2 * _size - 1][2 * _size - 1];
        for(int i = 0; i < 2 * _size - 1; i++)
            if (2 * _size - 1 >= 0) System.arraycopy(board._board[i], 0, _board[i], 0, 2 * _size - 1);
    }

    public boolean get(int x, int y) {
        if(outOfBound(x, y)) {
            System.out.println("Coordinates out of bound");
            return false;
        }
        return _board[y][x];
    }

    public boolean get(int[] pos) {
        return get(pos[0], pos[1]);
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
    private boolean outOfBound(int[] pos){
        return outOfBound(pos[0], pos[1]);
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

    // For current board state, returns the heuristics for both players boats
    public float getScore(int[][] allyBoats, int[][] enemyBoats) {
        float allyScore = 0;

        for(int x = 0; x < 3 * _size - 1; x++) {
            for(int y = 0; y < 3 * _size - 1; y++) {
                if(!outOfBound(x, y)) {
                    // Check if the contains ice
                    if(_board[y][x]) {
                        // We look for the closest boat to this cell
                        // We iterate on radius
                        for(int i = 1; i <= 3; i ++) {
                            int[][] neighbors = getNeighbors(x, y);
                            float allyFound = 0;
                            float enemyFound = 0;

                            for(int[] neighbor : neighbors) {
                                allyFound += Arrays.stream(allyBoats).anyMatch(b -> b[0] == neighbor[0] && b[1] == neighbor[1]) ? 1 : 0;
                                enemyFound += Arrays.stream(enemyBoats).anyMatch(b -> b[0] == neighbor[0] && b[1] == neighbor[1]) ? 1 : 0;
                            }

                            if (allyFound > 0 || enemyFound > 0) {
                                allyScore += i * allyFound / (2 * allyFound + enemyFound);
                                break;
                            } else if (i == 3) {
                                allyScore = 0;
                            }
                        }
                    }
                }
            }
        }
        return allyScore;
    }

    // Returns the offset of the neighbors of the given cell and radius
    public int[][] getOffset(int radius) {
        switch (radius) {
            case 1:
                return new int[][]{{0, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 0}, {-1, -1}};
            case 2:
                return new int[][]{{0, -2}, {1, -1}, {2, 0}, {2, 1}, {2, 2}, {1, 2}, {0, 2}, {-1, 1}, {-2, 0}, {-2, -1}, {-2, -2}, {-1, -2}};
            case 3:
                return new int[][]{{0, -3}, {1, -2}, {2, -1}, {3, 0}, {3, 1}, {3, 2}, {3, 3}, {2, 3}, {1, 3}, {0, 3}, {-1, 2}, {-2, 1}, {-3, 0}, {-3, -1}, {-3, -2}, {-3, -3}, {-2, -3}, {-1, -3}};
            default:
                return new int[][]{{0, 0}};

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
        return Arrays.stream(getOffset(1))
                .map(o -> { return new int[] {x + o[0], y + o[1]}; })
                .filter(a -> {return !outOfBound(a);})
                .toArray(int[][]::new);
    }

    public int[][] getNeighbors(int x, int y, int radius) {
        return Arrays.stream(getOffset(radius))
                .map(o -> { return new int[] {x + o[0], y + o[1]}; })
                .filter(a -> {return !outOfBound(a);})
                .toArray(int[][]::new);
    }
}