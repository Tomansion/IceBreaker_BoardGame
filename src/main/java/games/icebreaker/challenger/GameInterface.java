package games.icebreaker.challenger;

import java.util.Objects;
import java.util.Set;

public class GameInterface {
    static String redPlayer = "RED";
    static String blackPlayer = "BLACK";

    public static int SIZE = 5;

    public Player _red;
    public Player _black;
    private Board _board;
    private String _currentPlayer;

    public GameInterface() {
        _red = new Player(redPlayer, SIZE);
        _black = new Player(blackPlayer, SIZE);
        _board = new Board(SIZE);
        _currentPlayer = redPlayer;
    }

    public GameInterface(GameInterface game) {
        _red = new Player(game._red);
        _black = new Player(game._black);
        _board = new Board(game._board);
        _currentPlayer = game._currentPlayer;
    }

    public String getCurrentPlayer() {
        return _currentPlayer;
    }

    public boolean move(String move) {
        String[] moveArray = move.split("-");

        int[] origin = stringToCoord(moveArray[0]);
        int[] dest = stringToCoord(moveArray[1]);

        if (_board.get(dest[0], dest[1])) {
            if (_currentPlayer.equals(redPlayer))
                _red._score++;
            if (_currentPlayer.equals(blackPlayer))
                _black._score++;
            _board.set(dest[0], dest[1], false);
        }

        if (_currentPlayer.equals(redPlayer)) {
            for (int i = 0; i < 3; i++) {
                if (_red._boats[i][0] == origin[0] && _red._boats[i][1] == origin[1]) {
                    _red._boats[i] = dest;
                    break;
                }
            }
        } else {
            for (int i = 0; i < 3; i++) {
                if (_black._boats[i][0] == origin[0] && _black._boats[i][1] == origin[1]) {
                    _black._boats[i] = dest;
                    break;
                }
            }
        }

        _currentPlayer = (_currentPlayer.equals(redPlayer)) ? blackPlayer : redPlayer;
        return true;
    }

    /**
     * @param x x coordinate
     * @param y y coordinate
     * @return 'A1'
     */
    public String coordToString(int x, int y) {
        return (char) (y + 'A') + "" + (x + 1);
    }

    /**
     * @param move 'E1-F2'
     * @return 'E1-F2'
     */
    public String moveToOriginal(String move) {
        String[] moveArray = move.split("-");

        int[] origin = stringToCoord(moveArray[0]);
        int[] dest = stringToCoord(moveArray[1]);


        if (origin[1] > SIZE - 1) {
            origin[0] -= origin[1] - (SIZE - 1);
        }
        if (dest[1] > SIZE - 1) {
            dest[0] -= dest[1] - (SIZE - 1);
        }

        String ret = this.coordToString(origin[0], origin[1]) + "-" + this.coordToString(dest[0], dest[1]);
        return ret;
    }

    /**
     * @param move 'E1-F2'
     * @return 'E1-F2'
     */
    public String originalToNew(String move) {
        String[] moveArray = move.split("-");
        int[] origin = stringToCoord(moveArray[0]);
        int[] dest = stringToCoord(moveArray[1]);

        if (origin[1] > SIZE - 1) {
            origin[0] += origin[1] - (SIZE - 1);
        }
        if (dest[1] > SIZE - 1) {
            dest[0] += dest[1] - (SIZE - 1);
        }
        return this.coordToString(origin[0], origin[1]) + "-" + this.coordToString(dest[0], dest[1]);
    }

    /**
     * @param move 'A1'
     * @return [x, y]
     */
    public int[] stringToCoord(String move) {
        return new int[] { move.charAt(1) - '1', move.charAt(0) - 'A' };
    }

    int getScore(String player) {
        return player.equals(redPlayer) ? _red._score : _black._score;
    }

    public Set<String> possibleMoves(String player) {
        // Get player boats
        int[][] boats = player.equals(redPlayer) ? _red._boats : _black._boats;

        Set<String> moves = new java.util.HashSet<String>();

        for (int[] boat : boats) {
            Set<int[]> possibleMoves = this._getPossibleMoves(boat[0], boat[1]);
            for (int[] move : possibleMoves) {
                moves.add(coordToString(boat[0], boat[1]) + '-' + coordToString(move[0], move[1]));
            }
        }

        return moves;
    }

    // Possible moves
    // Return a set of coordinates from a boat position
    private Set<int[]> _getPossibleMoves(int x, int y) {
        Set<int[]> possibleMoves = new java.util.HashSet<int[]>();
        int[][] neighbors = _board.getNeighbors(x, y);

        for (int[] neighbor : neighbors) {
            // If the neighbor is iceberg, it is a possible move
            if (_board.get(neighbor[0], neighbor[1]))
                possibleMoves.add(neighbor);
        }

        if (possibleMoves.size() > 0)
            return possibleMoves;

        // There is no close iceberg
        // We need to find the closest iceberg

        int searchDistance = 1;
        final int maxSearchDistance = 9;
        Set<int[][]> possiblePath = new java.util.HashSet<int[][]>();
        Set<int[][]> validPath = new java.util.HashSet<int[][]>();
        // Possible path : set of coordinates lists
        // We explore a path and split it into possible moves

        // Init possiblePath with the neighbors
        for (int[] neighbor : neighbors) {
            possiblePath.add(new int[][] { neighbor });
        }

        while (validPath.size() == 0 && searchDistance < maxSearchDistance) {
            Set<int[][]> newPossiblePath = new java.util.HashSet<int[][]>();
            for (int[][] path : possiblePath) {

                int[] lastCoord = path[path.length - 1];
                int[][] newNeighbors = _board.getNeighbors(lastCoord[0], lastCoord[1]);
                for (int[] neighbor : newNeighbors) {
                    if (_board.get(neighbor[0], neighbor[1])) {
                        // If the neighbor is a iceberg, we found a possible move
                        validPath.add(path);
                    }

                    // Check if the neighbor the initial boat
                    if (neighbor[0] == x && neighbor[1] == y) {
                        continue;
                    }

                    // Check if there is a boat
                    if (this._coordInOnABoat(neighbor)) {
                        continue;
                    }

                    // Check if the neighbor was already in the path
                    if (this._coordInPath(neighbor, path)) {
                        continue;
                    }

                    // If the neighbor is not a iceberg, we add it to the new possible path
                    int[][] newPath = new int[path.length + 1][];
                    for (int i = 0; i < path.length; i++) {
                        newPath[i] = path[i];
                    }
                    newPath[path.length] = new int[] { neighbor[0], neighbor[1] };
                    newPossiblePath.add(newPath);
                }
            }
            possiblePath = newPossiblePath;
            searchDistance++;
        }

        // Get the first moves of the valid paths
        for (int[][] path : validPath) {
            // Check that the move havent been added before
            int[] firstMove = path[0];
            if (!this._coordInPath(firstMove, possibleMoves) && !this._coordInOnABoat(firstMove)) {
                possibleMoves.add(firstMove);
            }
        }
        return possibleMoves;
    }

    private boolean _coordInPath(int[] coord, int[][] path) {
        for (int[] pathCoord : path) {
            if (pathCoord[0] == coord[0] && pathCoord[1] == coord[1])
                return true;
        }
        return false;
    }

    private boolean _coordInOnABoat(int[] coord) {
        for (int[] boat : _red._boats) {
            if (boat[0] == coord[0] && boat[1] == coord[1])
                return true;
        }
        for (int[] boat : _black._boats) {
            if (boat[0] == coord[0] && boat[1] == coord[1])
                return true;
        }
        return false;
    }

    private boolean _coordInPath(int[] coord, Set<int[]> path) {
        return this._coordInPath(coord, path.toArray(new int[path.size()][]));
    }

    public int getCurrentAllyScore(String color) {
        return Objects.equals(color, "red") ? _red._score : _black._score;
    }

    public int getCurrentEnemyScore(String color) {
        return Objects.equals(color, "red") ? _black._score : _red._score;
    }

    boolean isFinished() {
        return _red._score == 23 || _black._score == 23;
    }

    float getCurrentScore(String color) {
        Set<String> moves = this.possibleMoves(color);
        float counter = 0;
        for (String move : moves) {
            String pos = move.split("-")[1];
            if (!_board.get(stringToCoord(pos))) {
                counter++;
            }
        }
        return -0.9f * counter + _board.getScore(Objects.equals(color, "RED") ? _red._boats : _black._boats,
                Objects.equals(color, "RED") ? _black._boats : _red._boats);
    }
}
