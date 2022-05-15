package games.icebreaker;

import java.util.Set;

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
                (x >= _size && ((y <= x - _size) || (y >= 3 * _size - x - 2)));
    }

    private void resetBoard() {
        for(int x = 0; x < 2 * _size - 1; x++) {
            for(int y = 0; y < 2 * _size - 1; y++) {
                if(!outOfBound(x, y)) {
                    _board[y][x] = true;
                }
            }
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
    // Return a set of coordinates from a boat position
    public Set<int[]> getPossibleMoves(int x, int y) {
        Set<int[]> possibleMoves = new java.util.HashSet<int[]>();
        Set<int[]> neighbors =  getNeighbors(x, y);

        for(int[] neighbor : neighbors) {
            // If the neighbor is iceberg, it is a possible move
            if(this.get(neighbor[0], neighbor[1])) possibleMoves.add(neighbor);
        }

        if(possibleMoves.size() > 0) return possibleMoves;

        // There is no close iceberg
        // We need to find the closest iceberg

        int searchDistance = 1;
        final int maxSearchDistance = 9;
        Set<int[][]> possiblePath = new java.util.HashSet<int[][]>();
        Set<int[][]> validPath = new java.util.HashSet<int[][]>();
        // Possible path : set of coordinates lists
        // We explore a path and split it into possible moves

        // Init possiblePath with the neighbors
        for(int[] neighbor : neighbors) {
            possiblePath.add(new int[][] {neighbor});
        }

        while(validPath.size() == 0 && searchDistance < maxSearchDistance) {
            Set<int[][]> newPossiblePath = new java.util.HashSet<int[][]>();
            for(int[][] path : possiblePath) {

                int[] lastCoord = path[path.length - 1];
                Set<int[]> newNeighbors = getNeighbors(lastCoord[0], lastCoord[1]);
                for(int[] neighbor : newNeighbors) {
                    // Check if the neighbor a boat
                    if (neighbor[0] == x && neighbor[1] == y) {
                        continue;
                    }
                    // TODO : Other boats

                    // Check if the neighbor was already in the path
                    if (this._coordInPath(neighbor, path)) {
                        continue;
                    }

                    if(this.get(neighbor[0], neighbor[1])) {
                        // If the neighbor is a iceberg, we found a possible move
                        validPath.add(path);
                    } else {
                        // If the neighbor is not a iceberg, we add it to the new possible path
                        int[][] newPath = new int[path.length + 1][];
                        for(int i = 0; i < path.length; i++) {
                            newPath[i] = path[i];
                        }
                        newPath[path.length] = new int[] {neighbor[0], neighbor[1]};
                        newPossiblePath.add(newPath);
                    }
                }
            }
            possiblePath = newPossiblePath;
            searchDistance++;
        }

        // Get the first moves of the valid paths
        for(int[][] path : validPath) {
            // Check that the move havent been added before
            int[] firstMove = path[0];
            if (!this._coordInPath(firstMove, possibleMoves)) {
                possibleMoves.add(firstMove);
            }
        }
        return possibleMoves;
    }

    private boolean _coordInPath(int[] coord, int[][] path) {
        for(int[] pathCoord : path) {
            if(pathCoord[0] == coord[0] && pathCoord[1] == coord[1]) return true;
        }
        return false;
    }
    private boolean _coordInPath(int[] coord,Set<int[]> path) {
       return this._coordInPath(coord, path.toArray(new int[path.size()][]));
    }
}
